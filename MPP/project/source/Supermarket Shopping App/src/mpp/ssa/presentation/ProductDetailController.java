package mpp.ssa.presentation;

import eu.hansolo.enzo.notification.Notification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import mpp.ssa.bus.OrderBUS;
import mpp.ssa.domain.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailController extends HomeController {
    AnchorPane anchorPane = new AnchorPane();
    @FXML
    Label labelName;
    @FXML
    Label labelCost;
    @FXML
    private ComboBox comboBox;
    @FXML
    private Button btnBuy1Click;
    Customer customer = Main.userData.getCustomer();

    @FXML
    public void handleBackBtn2(ActionEvent event){
        Main.primaryStage.setScene(prviousScene);
    }

    public void createTilePane(){
        try {
            HomeController.setPrviousScene();
            anchorPane = FXMLLoader.load(getClass().getResource("productDetail.fxml"));
            labelName = new Label();
            labelName = (Label) anchorPane.lookup("#labelName");
            labelCost = new Label();
            labelCost = (Label) anchorPane.lookup("#labelCost");
            btnBuy1Click = new Button();
            btnBuy1Click = (Button) anchorPane.lookup("#btnBuy1Click");
            customer.getUserSettingList();
            if(!customer.isLoginStatus() || !ModifyController.Buy1click){
                btnBuy1Click.setVisible(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBuy1Click(ActionEvent event){
        CheckOut();
    }

    public void CheckOut() {
        Order order = new Order();
        order.setStatus("Ordered");
        order.setCustomer(customer);
        int quantity = Integer.parseInt(comboBox.getValue().toString());
        List<LineItem> lineItems = new ArrayList<LineItem>();
        lineItems.add(new LineItem(productItem.ProductId, productItem.labelName,
                quantity, productItem.UnitCost, quantity * productItem.UnitCost));
        order.setLineItemList(lineItems);
        order.setDiscountTotal(order.calculateDiscountTotal(customer.getUserType()));
        boolean retVal = OrderBUS.getOrderBUS().placeOrder(order);
        if(retVal) {
            customer.getOrderList().add(order);
            customer.setUserType(UserType.getUserType(customer.calculateTotalOrders()));
            Notification.Notifier.INSTANCE.notifySuccess("Success","Your Order is confirmed");
            Main.primaryStage.setScene(Main.HOME_SCENE);
        } else {
            // need to show message for client
            Notification.Notifier.INSTANCE.notifyError("Fail","There is some error, try again");
        }
    }

    @FXML
    public void handleAddToCart(ActionEvent event) {
        getShoppingCartInfo();
        Notification.Notifier.INSTANCE.notifySuccess("Success","Items had been add to your cart");
    }

    public void getShoppingCartInfo() {
        // get shopping cart data
        ShoppingCart shoppingCart = Main.userData.getCustomer().getShoppingCart();
        int quantity = Integer.parseInt(comboBox.getValue().toString());
        shoppingCart.addCartItem(new LineItem(productItem.ProductId, productItem.labelName,
                quantity, productItem.UnitCost, quantity * productItem.UnitCost));
    }
}