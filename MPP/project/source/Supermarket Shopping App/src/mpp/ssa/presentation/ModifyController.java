package mpp.ssa.presentation;

import eu.hansolo.enzo.notification.Notification;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import mpp.ssa.domain.Customer;

import java.io.IOException;

public class ModifyController extends SignupController {
    AnchorPane ModifyPane = new AnchorPane();
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


    public void createModifyPane(){
        try {
            ModifyPane = FXMLLoader.load(getClass().getResource("modifyUser.fxml"));
            setInfo(ModifyPane);
            getInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void handleConfirmBtn(){
       saveCustomer();
       //update customer

       if(!valid){
           return;
       }
       else {
           Notification.Notifier.INSTANCE.notifySuccess("Success","Modified successful");
           Main.primaryStage.setScene(Main.HOME_SCENE);
       }

    }


    public void setInfo(AnchorPane Pane){
        txtAddress = (TextField) Pane.lookup("#txtAddress");
        txtCardNumber = (TextField) Pane.lookup("#txtCardNumber");
        txtEmail = (TextField) Pane.lookup("#txtEmail");
        txtName = (TextField) Pane.lookup("#txtName");
        txtPhone = (TextField) Pane.lookup("#txtPhone");
        txtCustomerName = (TextField) Pane.lookup("#txtCustomerName");
        txtShippingAddress = (TextField) Pane.lookup("#txtShippingAddress");
    }

    public void getInfo(){
        customer = Main.userData.getCustomer();
        txtName.setText(customer.getUsername());
        txtCustomerName.setText(customer.getCustomerName());
        txtAddress.setText(customer.getAddress());
        txtEmail.setText(customer.getEmail());
        txtCardNumber.setText(customer.getBankCardNo());
        txtShippingAddress.setText(customer.getShippingAddress());
    }

}