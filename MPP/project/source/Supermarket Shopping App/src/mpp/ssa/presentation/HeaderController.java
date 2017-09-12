package mpp.ssa.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by Duong Truong on 9/12/2017.
 */
public class HeaderController extends Controller{
    AnchorPane Header = new AnchorPane();
    @FXML
    Label labelUserName;

    public void createHeader(){
        try {
            Header = FXMLLoader.load(getClass().getResource("header_home.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeHeaderUser(){
        try {
            Header = FXMLLoader.load(getClass().getResource("header_user.fxml"));
            labelUserName = (Label) Header.lookup("#labelUserName");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleLogin(ActionEvent event) {
        prviousScene = Main.primaryStage.getScene();
        AnchorPane Login = new AnchorPane();
        try {
            Login = FXMLLoader.load(getClass().getResource("login_screen.fxml"));
            Main.primaryStage.setScene(new Scene(Login,600,400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
