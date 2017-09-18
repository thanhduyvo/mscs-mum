package mpp.ssa.presentation;

import eu.hansolo.enzo.notification.Notification;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import mpp.ssa.bus.CustomerBUS;
import mpp.ssa.domain.Customer;
import mpp.ssa.util.ValidationHelper;

import java.io.IOException;

public class SignupController extends HomeController {
    AnchorPane SignupPane = new AnchorPane();
    Boolean valid = true;

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
    @FXML
    private TextField txtCustomerName;

    private Customer customer = new Customer();


    public void createSignupPane(){
        try {
            SignupPane = FXMLLoader.load(getClass().getResource("signup.fxml"));
            setInfo(SignupPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML

    public void setInfo(AnchorPane Pane){
        txtAddress = (TextField) Pane.lookup("#txtAddress");
        txtCardNumber = (TextField) Pane.lookup("#txtCardNumber");
        txtEmail = (TextField) Pane.lookup("#txtEmail");
        txtName = (TextField) Pane.lookup("#txtName");
        txtPhone = (TextField) Pane.lookup("#txtPhone");
        txtCustomerName = (TextField) Pane.lookup("#txtCustomerName");
        txtShippingAddress = (TextField) Pane.lookup("#txtShippingAddress");
    }

    @FXML
    public void handleConfirmBtn(){
       saveCustomer();
       CustomerBUS.getCustomerBUS().register(customer);
       if(!valid){
           return;
       }
       else {
           Notification.Notifier.INSTANCE.notifySuccess("Success","Sign up successful");
           Main.primaryStage.setScene(Main.HOME_SCENE);
       }

    }

    public void saveCustomer(){
        customer.setAddress(txtAddress.getText());
        customer.setBankCardNo(txtCardNumber.getText());
        customer.setUsername(txtName.getText());
        customer.setPassword(passPassword.getText());
        customer.setCustomerName(txtCustomerName.getText());
        if(ValidationHelper.validateEmailAddress(txtEmail.getText())){
            customer.setEmail(txtEmail.getText());
            valid = true;
        }
        else {
            Notification.Notifier.INSTANCE.notifyWarning("Warning","Invalid email");
            valid = false;
        }

        customer.setShippingAddress(txtShippingAddress.getText());
    }
}