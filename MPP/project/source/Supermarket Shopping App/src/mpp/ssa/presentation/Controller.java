package mpp.ssa.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import mpp.ssa.domain.*;
import mpp.ssa.bus.CustomerBUS;

public class Controller{
    @FXML
    Button LoginBtn, CheckLoginBtn, BackBtn;
    @FXML
    TextField UsernameField;
    @FXML
    PasswordField PasswordField;
    @FXML
    ListView listView;

    final BorderPane root = Main.getRoot();
    final AnchorPane PaneListview = Main.getPanelistView();
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

    private CustomerBUS customerBUS;
    private CustomerBUS getCustomerBUS() {
        if(customerBUS == null) {
            customerBUS = new CustomerBUS();
        }

        return customerBUS;
    }

    @FXML
    void handleLogin(ActionEvent event){

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

            boolean loginResult = getCustomerBUS().login(USERNAME, PASSWORD);
            if(loginResult) {
                HeaderController headerController = new HeaderController();
                headerController.changeHeaderUser();
                headerController.labelUserName.setText(USERNAME);
                Main.getRoot().setTop(headerController.Header);
                Main.primaryStage.setScene(prviousScene);
            }
            else {
                // implement: notify to user
            }
        }
        else {
            // implement: notify to user
        }
    }
}