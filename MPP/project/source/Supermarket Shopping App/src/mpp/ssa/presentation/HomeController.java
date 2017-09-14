package mpp.ssa.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import mpp.ssa.bus.ProductBUS;
import mpp.ssa.bus.ProductCategoryBUS;
import mpp.ssa.bus.CustomerBUS;
import mpp.ssa.domain.Customer;
import mpp.ssa.domain.Product;
import mpp.ssa.domain.ProductCategory;
import mpp.ssa.domain.Standard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeController {
    @FXML
    Button LoginBtn, CheckLoginBtn, BackBtn;
    @FXML
    TextField UsernameField;
    @FXML
    PasswordField PasswordField;

    public static Scene prviousScene = Main.primaryStage.getScene();

    static String USERNAME, PASSWORD;
    public static String getUsername(){
        return USERNAME;
    }
    public static String getPassword(){
        return PASSWORD;
    }

    public static void setPrviousScene(){
        prviousScene = Main.primaryStage.getScene();
    }

    @FXML
    public void handleBackBtn(ActionEvent event){
        Main.primaryStage.setScene(prviousScene);
    }

    @FXML
    void handleCheckLogin(ActionEvent event){
        USERNAME = UsernameField.getText();
        PASSWORD = PasswordField.getText();

        if(USERNAME != null && !USERNAME.isEmpty() && PASSWORD != null && !PASSWORD.isEmpty()) {

            boolean loginResult = CustomerBUS.getCustomerBUS().login(USERNAME, PASSWORD);
            if(loginResult) {
                changeHeaderUser();
                labelUserName.setText(USERNAME);
                Main.getRoot().setTop(Header);
                Main.primaryStage.setScene(prviousScene);

                // init user data

                Customer customer = CustomerBUS.getCustomerBUS().getCustomerByUsername(USERNAME);
                customer.setUsername(USERNAME);
                customer.setLoginStatus(true);
                customer.setUserType(new Standard());

                Main.userData.setCustomer(customer);
            }
            else {
                // implement: notify to user
            }
        }
        else {
            // implement: notify to user
        }
    }

    @FXML
    public void handleHome(ActionEvent event) {
        Main.primaryStage.setScene(Main.HOME_SCENE);
    }

    // HEADER

    AnchorPane Header = new AnchorPane();

    @FXML
    Label labelUserName;

    public void createHeader(){
        try {
            Header = FXMLLoader.load(getClass().getResource("headerHome.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeHeaderUser(){
        try {
            Header = FXMLLoader.load(getClass().getResource("headerUser.fxml"));
            labelUserName = (Label) Header.lookup("#labelUserName");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleLogin(ActionEvent event) {
        prviousScene = Main.primaryStage.getScene();
        AnchorPane Login = new AnchorPane();
        try {
            Login = FXMLLoader.load(getClass().getResource("login.fxml"));
            Main.primaryStage.setScene(new Scene(Login,600,400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCart(ActionEvent event) {
        prviousScene = Main.primaryStage.getScene();

        ShoppingCartController cartScreenController = new ShoppingCartController();
        cartScreenController.createCartPane();
        cartScreenController.showLineItem();
        Main.primaryStage.setScene(new Scene(cartScreenController.CartPane,800,500));
    }

    // CATEGORY

    AnchorPane Category = new AnchorPane();

    @FXML
    ListView<CategoryItemCell> categoryListView;

    public void createCategory(){
        try {
            Category = FXMLLoader.load(getClass().getResource("categoryList.fxml"));
            categoryListView = new ListView<>();
            categoryListView = (ListView<CategoryItemCell>) Category.lookup("#listView");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCategory(){
        List<ProductCategory> productCategories = ProductCategoryBUS.getProductCategoryBUS().getAllProductCategories();
        if(productCategories != null && !productCategories.isEmpty()) {
            List<CategoryItemCell> list = new ArrayList<>();
            for(ProductCategory category : productCategories) {
                list.add(new CategoryItemCell(category));
            }

            ObservableList<CategoryItemCell> myObservableList = FXCollections.observableList(list);
            categoryListView.setItems(myObservableList);
            categoryListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int categoryId = categoryListView.getSelectionModel().getSelectedItems().get(0).CategoryId;
                    showProduct(categoryId);
                    Main.getRoot().setCenter(productListView);
                    Main.primaryStage.setScene(Main.HOME_SCENE);
                }
            });
        }
    }

    public static class CategoryItemCell extends HBox {
        Label name = new Label();
        String labelName;
        Integer CategoryId;

        CategoryItemCell(ProductCategory category) {
            super();

            CategoryId = category.getCategoryId();

            labelName = category.getCategoryName();
            name.setText(labelName);
            name.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(name, Priority.ALWAYS);

            this.getChildren().addAll(name);
        }
    }

    // PRODUCT

    AnchorPane ListPane = new AnchorPane();
    ObservableList<ProductItemCell> productItems;
    static ProductItemCell productItem;

    @FXML
    ListView<ProductItemCell> productListView;

    @FXML
    public void handleItemClick(MouseEvent mouseEvent) {
    }

    public void createListPane(){
        try {
            ListPane = FXMLLoader.load(getClass().getResource("productList.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showProduct(int categoryId){

        List<Product> products = new ArrayList<Product>();
        if(categoryId == 0) {
            products = ProductBUS.getProductBUS().getAllProducts();
        }
        else {
            products = ProductBUS.getProductBUS().getProductsByCategory(categoryId);
        }
        List<ProductItemCell> list = new ArrayList<>();
        if(products != null && !products.isEmpty()) {
            for (Product product : products) {
                list.add(new ProductItemCell(product));
            }
        }

            productListView = new ListView<ProductItemCell>();
            ObservableList<ProductItemCell> myObservableList = FXCollections.observableList(list);
            productListView.setItems(myObservableList);
            productListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2){
                        productItem = productListView.getSelectionModel().getSelectedItem();
                        ProductDetailController productDetailController = new ProductDetailController();
                        productDetailController.createTilePane();
                        productDetailController.labelName.setText(productItem.labelName);
                        productDetailController.labelCost.setText(productItem.labelCost);
                        Main.primaryStage.setScene(new Scene(productDetailController.anchorPane,700,400));
                    }
                }
            });
    }


    public static class ProductItemCell extends HBox {
        Label name = new Label();
        Label cost = new Label();
        int ProductId;
        String labelName;
        String labelCost;
        Double UnitCost;

        ProductItemCell(Product product) {
            super();
            ProductId = product.getProductId();
            labelName = product.getProductName();
            UnitCost = product.getUnitCost();
            labelCost = "$" + String.valueOf(product.getUnitCost());
            name.setText(labelName);
            name.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(name, Priority.ALWAYS);

            cost.setText(labelCost);
            //cost.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(cost, Priority.ALWAYS);

            this.getChildren().addAll(name, cost);
        }
    }
}