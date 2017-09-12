package mpp.ssa.presentation;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Stage primaryStage;

    public static BorderPane root = new BorderPane();

    public static BorderPane getRoot(){
        return root;
    }

    public static AnchorPane PanelistView = new AnchorPane();

    public static AnchorPane getPanelistView(){ return PanelistView;}

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        try {
            root = FXMLLoader.load(getClass().getResource("home_screen.fxml"));
            primaryStage.setTitle("Shopping - MUM");
            primaryStage.setScene(new Scene(root, 1042, 635));
            //creatList();
            HeaderController headerController = new HeaderController();
            headerController.createHeader();
            root.setTop(headerController.Header);
            ListviewController listviewController = new ListviewController();
            listviewController.createListPane();
            listviewController.showProuct();
            root.setCenter(listviewController.listView);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
