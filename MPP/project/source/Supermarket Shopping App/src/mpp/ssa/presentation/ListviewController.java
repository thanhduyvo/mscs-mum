package mpp.ssa.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import mpp.ssa.domain.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duong Truong on 9/12/2017.
 */
public class ListviewController extends Controller{
    AnchorPane ListPane = new AnchorPane();
    ObservableList<HBoxCell> Obser;
    static HBoxCell item;

    @FXML
    ListView<HBoxCell> listView;


    @FXML
    public void handleItemClick(MouseEvent mouseEvent) {
        Obser = listView.getSelectionModel().getSelectedItems();
        item = Obser.get(0);
        System.out.print(item.labelName);
    }

    public void createListPane(){
        try {
            ListPane = FXMLLoader.load(getClass().getResource("list_view.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showProduct(){

        List<Product> products = getProductBUS().getAllProducts();
        if(products != null && !products.isEmpty()) {

            List<HBoxCell> list = new ArrayList<>();
            for(Product product : products) {
                list.add(new HBoxCell(product));
            }

            listView = new ListView<HBoxCell>();
            ObservableList<HBoxCell> myObservableList = FXCollections.observableList(list);
            listView.setItems(myObservableList);
            listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2){
                        item = listView.getSelectionModel().getSelectedItem();
                        ProductDetailController productDetailController = new ProductDetailController();
                        productDetailController.createTilePane();
                        productDetailController.labelName.setText(item.labelName);
                        productDetailController.labelCost.setText(item.labelCost);
                        Main.primaryStage.setScene(new Scene(productDetailController.anchorPane,1000,700));
                    }
                }
            });
        }
    }

    public static class HBoxCell extends HBox {
        Label name = new Label();
        Label cost = new Label();
        String labelName;
        String labelCost;

        HBoxCell(Product product) {
            super();
            labelName = product.getProductName();
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
