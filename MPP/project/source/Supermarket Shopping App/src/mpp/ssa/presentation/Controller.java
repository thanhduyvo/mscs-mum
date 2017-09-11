package mpp.ssa.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Controller {
    @FXML
    private Button LoginBtn;

    @FXML
    void handleLogin(ActionEvent event){
        try {
            AnchorPane login = FXMLLoader.load(getClass().getResource("login_screen.fxml"));
            BorderPane pane = Main.getRoot();
            pane.setCenter(login);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

