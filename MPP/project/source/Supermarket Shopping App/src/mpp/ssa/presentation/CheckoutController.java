package mpp.ssa.presentation;

import eu.hansolo.enzo.notification.Notification;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import mpp.ssa.bus.OrderBUS;
import mpp.ssa.domain.Customer;
import mpp.ssa.domain.Order;
import mpp.ssa.domain.ShoppingCart;
import mpp.ssa.domain.UserType;

import java.io.IOException;

public class CheckoutController extends HomeController {
    AnchorPane CheckoutPane = new AnchorPane();
    @FXML
    private Label lbOriginCost;

    @FXML
    private Label lbTotalCost;

    @FXML
    private Label lbName;

    @FXML
    private TextField txtCardNumber;

    @FXML
    private Label lbDiscount;

    @FXML
    private TextField txtShppingAddress;

    @FXML
    private Label lbAddress;

    @FXML
    private Label lbPhone;

    @FXML
    private Label lbEmail;

    Customer customer = Main.userData.getCustomer();

    public void createCheckoutPane(){
        try {
            CheckoutPane = FXMLLoader.load(getClass().getResource("checkout.fxml"));
            setInformation();
            lbAddress.setText(customer.getAddress());
            lbEmail.setText(customer.getEmail());
            lbName.setText(customer.getCustomerName());;
            double originalCost = calculateOriginCost();
            lbOriginCost.setText("$" + String.valueOf(originalCost));
            double discountTotal = calculateDiscountTotal(originalCost);
            lbDiscount.setText("$" + String.valueOf(discountTotal));
            lbTotalCost.setText("$" + String.valueOf(originalCost - discountTotal));

            txtCardNumber.setText(customer.getBankCardNo());
            txtShppingAddress.setText(customer.getShippingAddress());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInformation(){
        lbAddress = (Label) CheckoutPane.lookup("#lbAddress");
        lbTotalCost = (Label) CheckoutPane.lookup("#lbTotalCost");
        lbOriginCost = (Label) CheckoutPane.lookup("#lbOriginCost");
        lbPhone = (Label) CheckoutPane.lookup("#lbPhone");
        lbName = (Label) CheckoutPane.lookup("#lbName");
        lbEmail = (Label) CheckoutPane.lookup("#lbEmail");
        lbDiscount = (Label) CheckoutPane.lookup("#lbDiscount");

        txtCardNumber = (TextField) CheckoutPane.lookup("#txtCardNumber");
        txtShppingAddress = (TextField) CheckoutPane.lookup("#txtShppingAddress");
    }

    public double calculateOriginCost(){
        ShoppingCart shoppingCart = customer.getShoppingCart();
        double result = shoppingCart.calculateTotalLineItems();
        return Math.round((result * 100)/100);
    }

    public double calculateDiscountTotal(double originalCost) {
        return customer.getUserType().calcDiscount(originalCost);
    }

    @FXML
    public void handleConfirm(){
        CheckOut();
    }

    public void CheckOut() {
        Order order = new Order();
        order.setStatus("Ordered");
        order.setBankCardNo(txtCardNumber.getText());
        order.setShippingAddress(txtShppingAddress.getText());
        order.setCustomer(customer);
        order.setLineItemList(customer.getShoppingCart().getLineItemList());
        order.setDiscountTotal(order.calculateDiscountTotal(customer.getUserType()));
        boolean retVal = OrderBUS.getOrderBUS().placeOrder(order);
        if(retVal) {
            customer.getOrderList().add(order);
            customer.setUserType(UserType.getUserType(customer.calculateTotalOrders()));
            Notification.Notifier.INSTANCE.notifySuccess("Success","Your Order is confirmed");
            Main.primaryStage.setScene(Main.HOME_SCENE);

            // renew shopping cart
            customer.setShoppingCart(new ShoppingCart());
        } else {
            // need to show message for client
            Notification.Notifier.INSTANCE.notifyError("Fail","There is some error, try again");
        }
    }
}