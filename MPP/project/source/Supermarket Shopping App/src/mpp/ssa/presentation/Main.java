package mpp.ssa.presentation;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static BorderPane root = new BorderPane();

    public static BorderPane getRoot(){
        return root;
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("home_screen.fxml"));
        primaryStage.setTitle("Shopping - MUM");
        primaryStage.setScene(new Scene(root, 1042, 635));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
