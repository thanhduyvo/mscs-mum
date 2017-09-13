package mpp.ssa.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import mpp.ssa.domain.LineItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duong Truong on 9/12/2017.
 */
public class CheckoutScreenController extends Controller{
    AnchorPane CheckoutPane = new AnchorPane();



    public void createCheckoutPane(){
        try {
            CheckoutPane = FXMLLoader.load(getClass().getResource("checkout_screen.fxml"));
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
