package mpp.ssa.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import mpp.ssa.domain.Customer;
import mpp.ssa.domain.LineItem;
import mpp.ssa.domain.ShoppingCart;

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
            setInfor();
            lbAddress.setText(customer.getAddress());
            lbEmail.setText(customer.getEmail());
            lbName.setText(customer.getCustomerName());
            lbOriginCost.setText("$" + String.valueOf(caculateOriginCost()));
            lbTotalCost.setText("$" + String.valueOf(caculateTotalCost(1)));

            txtCardNumber.setText(customer.getBankCardNo());
            txtShppingAddress.setText(customer.getShippingAddress());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setInfor(){
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

    public double caculateOriginCost(){
        double result =0.0;
        ShoppingCart shoppingCart = customer.getShoppingCart();
        for(LineItem item: shoppingCart.getLineItemList()){
            result += item.getSubtotal();
        }
        return Math.round((result * 100)/100);
    }

    public double caculateTotalCost(double discount){
        return caculateOriginCost() * discount;
    }

    @FXML
    public void handleConfirm(){

    }
}