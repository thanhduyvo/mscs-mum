package mpp.ssa.presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import mpp.ssa.bus.CustomerBUS;
import mpp.ssa.domain.Customer;

import java.io.IOException;

public class SignupController extends HomeController {
    AnchorPane SignupPane = new AnchorPane();


    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtShippingAddress;

    @FXML
    private javafx.scene.control.PasswordField passPassword;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtCardNumber;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    private Customer customer = new Customer();


    public void createSignupPane(){
        try {
            SignupPane = FXMLLoader.load(getClass().getResource("signup.fxml"));
            setInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInfo(){
        txtAddress = (TextField) SignupPane.lookup("#txtAddress");
        txtCardNumber = (TextField) SignupPane.lookup("#txtCardNumber");
        txtEmail = (TextField) SignupPane.lookup("#txtEmail");
        txtName = (TextField) SignupPane.lookup("#txtName");
        txtPhone = (TextField) SignupPane.lookup("#txtPhone");
        txtShippingAddress = (TextField) SignupPane.lookup("#txtShippingAddress");
    }

    @FXML
    public void handleConfirmBtn(){
       saveCustomer();
       CustomerBUS.getCustomerBUS().register(customer);

    }

    public void saveCustomer(){
        customer.setAddress(txtAddress.getText());
        customer.setBankCardNo(txtCardNumber.getText());
        customer.setCustomerName(txtName.getText());
        customer.setPassword(passPassword.getText());
        customer.setEmail(txtEmail.getText());
        customer.setShippingAddress(txtShippingAddress.getText());
    }
}