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
import mpp.ssa.domain.Order;
import mpp.ssa.domain.ShoppingCart;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderController extends HomeController {
    AnchorPane OrderPane = new AnchorPane();

    @FXML
    ListView<HBoxCell> listView;

    public void createOrderPane(){
        try {
            OrderPane = FXMLLoader.load(getClass().getResource("orderHistory.fxml"));
            listView = new ListView<>();
            listView = (ListView<HBoxCell>) OrderPane.lookup("#listView");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showOrderHistory() {

        List<HBoxCell> list = new ArrayList<>();
        List<Order> orders = Main.userData.getCustomer().getOrderList();
        for(Order item : orders) {
            list.add(new HBoxCell(item));
        }

        ObservableList<HBoxCell> myObservableList = FXCollections.observableList(list);
        listView.setItems(myObservableList);
    }

    public static String formatDate(Date date){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return df.format(date);
    }


    public static class HBoxCell extends HBox {
        Label OrderId = new Label();
        Label DateCreated = new Label();
        Label DateShipped = new Label();
        Label TotalCost = new Label();


        String labelCost;

        double totalCost;

        HBoxCell(Order lineOrder) {
            super();
            OrderId.setText(String.valueOf(lineOrder.getOrderId()));
            DateCreated.setText(formatDate(lineOrder.getDateCreated()));
            DateShipped.setText(formatDate(lineOrder.getDateShipped()));

            List<LineItem> lineItems = lineOrder.getLineItemList();
            for(LineItem item: lineItems){
                totalCost += item.getSubtotal();
            }
            labelCost = "$" + String.valueOf(totalCost);

            TotalCost.setText(labelCost);
            TotalCost.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(TotalCost, Priority.ALWAYS);


            OrderId.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(OrderId, Priority.ALWAYS);


            DateCreated.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(DateCreated, Priority.ALWAYS);

            DateShipped.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(DateShipped, Priority.ALWAYS);

            this.getChildren().addAll(OrderId, TotalCost, DateCreated, DateShipped);
        }
    }
}