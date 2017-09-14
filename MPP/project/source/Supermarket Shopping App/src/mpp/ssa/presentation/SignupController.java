package mpp.ssa.presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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


    public void createSignupPane(){
        try {
            SignupPane = FXMLLoader.load(getClass().getResource("signup.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleConfirmBtn(){

    }
}