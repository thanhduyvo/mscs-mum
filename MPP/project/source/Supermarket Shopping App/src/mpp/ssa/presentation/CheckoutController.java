package mpp.ssa.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CheckoutController extends HomeController {
    AnchorPane CheckoutPane = new AnchorPane();


    public void createCheckoutPane(){
        try {
            CheckoutPane = FXMLLoader.load(getClass().getResource("checkout.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
