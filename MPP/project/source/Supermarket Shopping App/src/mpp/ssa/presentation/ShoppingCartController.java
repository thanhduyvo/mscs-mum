package mpp.ssa.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

public class ShoppingCartController extends HomeController {
    AnchorPane CartPane = new AnchorPane();

    @FXML
    ListView<HBoxCell> listView;


    public void createCartPane(){
        try {
            CartPane = FXMLLoader.load(getClass().getResource("shoppingCart.fxml"));
            listView = new ListView<>();
            listView = (ListView<HBoxCell>) CartPane.lookup("#listView");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLineItem(){
        LineItem a = new LineItem();
        a.setProductName("item A");
        a.setQuantity(3);
        a.setUnitCost(124);

        LineItem b = new LineItem();
        b.setProductName("item B");
        b.setQuantity(2);
        b.setUnitCost(442);

        List<HBoxCell> list = new ArrayList<>();
        list.add(new HBoxCell(a));
        list.add(new HBoxCell(b));

        ObservableList<HBoxCell> myObservableList = FXCollections.observableList(list);
        listView.setItems(myObservableList);
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // click on item
            }
        });
    }

    @FXML
    public void handleBack(ActionEvent event) {
        Main.primaryStage.setScene(prviousScene);
    }

    @FXML
    public void handleCheckout(ActionEvent event){
        prviousScene = Main.primaryStage.getScene();
        CheckoutController checkoutScreenController = new CheckoutController();
        checkoutScreenController.createCheckoutPane();
        checkoutScreenController.showCheckout();
        Main.primaryStage.setScene(new Scene(checkoutScreenController.CheckoutPane,800,500));

    }


    public static class HBoxCell extends HBox {
        Label name = new Label();
        Label quantity = new Label();
        Label cost = new Label();
        Button delect = new Button();
        String labelName;
        String labelCost;
        String labelQuantity;

        HBoxCell(LineItem lineItem) {
            super();
            labelName = lineItem.getProductName();
            labelCost = "$" + String.valueOf(lineItem.getQuantity() * lineItem.getUnitCost());
            labelQuantity = String.valueOf(lineItem.getQuantity());

            quantity.setText(labelQuantity);
            quantity.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(quantity, Priority.ALWAYS);

            name.setText(labelName);
            name.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(name, Priority.ALWAYS);

            cost.setText(labelCost);
            cost.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(cost, Priority.ALWAYS);

            delect.setText("Remove");
            delect.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(delect, Priority.ALWAYS);
            delect.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("remove: " + labelName);
                }
            });

            this.getChildren().addAll(name, quantity, cost, delect);
        }
    }

}
