package mpp.ssa.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by Duong Truong on 9/12/2017.
 */
public class CheckoutScreenHomeController extends HomeController {
    AnchorPane CheckoutPane = new AnchorPane();



    public void createCheckoutPane(){
        try {
            CheckoutPane = FXMLLoader.load(getClass().getResource("checkout.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCheckout(){

    }

    @FXML
    public void handleBack(ActionEvent event) {
        Main.primaryStage.setScene(prviousScene);
    }



}
