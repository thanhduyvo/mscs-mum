package mpp.ssa.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mpp.ssa.UserData;

import java.io.IOException;

public class Main extends Application {
    public static Stage primaryStage;

    public static BorderPane root = new BorderPane();

    public static BorderPane getRoot(){
        return root;
    }

    public static AnchorPane PanelistView = new AnchorPane();

    public static AnchorPane getPanelistView(){ return PanelistView;}
    public static Scene HOME_SCENE;

    public static UserData userData = new UserData();

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        try {

            root = FXMLLoader.load(getClass().getResource("home.fxml"));
            primaryStage.setTitle("Shopping - MUM");
            primaryStage.setScene(new Scene(root, 1042, 635));
            HOME_SCENE = primaryStage.getScene();

            HomeController homeController = new HomeController();
            homeController.createHeader();
            root.setTop(homeController.Header);

            homeController.createCategory();
            homeController.showCategory();
            root.setLeft(homeController.Category);

            homeController.createListPane();
            homeController.showProduct("0");
            root.setCenter(homeController.productListView);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}