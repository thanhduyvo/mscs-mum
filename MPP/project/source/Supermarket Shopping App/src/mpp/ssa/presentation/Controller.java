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

public class Controller{
    @FXML
    private Button LoginBtn, CheckLoginBtn, BackBtn;
    @FXML
    private TextField UsernameField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private ListView listView;

    private final BorderPane root = Main.getRoot();
    private final AnchorPane PaneListview = Main.getPanelistView();
    private Scene prviousScene = Main.primaryStage.getScene();

    public static String USERNAME, PASSWORD;
    public static String getUsername(){
        return USERNAME;
    }
    public static String getPassword(){
        return PASSWORD;
    }
    public Controller() {

    }

    @FXML
    public void initialize(){
        Product a = new Product();
        a.setProductName("item1");
        a.setUnitCost(121);
        ObservableList<HBox> items = FXCollections.observableArrayList();
        items.add(showProduct(a));
//        AnchorPane ListView = new AnchorPane();
//        try {
//            ListView = FXMLLoader.load(getClass().getResource("list_view.fxml"));
//            Main.root.setCenter(ListView);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }


    @FXML
    void handleLogin(ActionEvent event){
        prviousScene = Main.primaryStage.getScene();
        AnchorPane Login = new AnchorPane();
        try {
            Login = FXMLLoader.load(getClass().getResource("login_screen.fxml"));
            Main.primaryStage.setScene(new Scene(Login,600,400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleBackBtn(ActionEvent event){
        Main.primaryStage.setScene(prviousScene);
    }

    @FXML
    void handleCheckLogin(ActionEvent event){
        USERNAME = UsernameField.getText().toString();
        PASSWORD = PasswordField.getText().toString();
    }




    public HBox showProduct(Product product){
        HBox hbox = new HBox();
        Label name = new Label(product.getProductName() + "-----------");
        Label cost = new Label("$" + String.valueOf(product.getUnitCost()));
        hbox.getChildren().addAll(name,cost);
        return hbox;
    }

}

