package mpp.ssa.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;


import java.io.IOException;

/**
 * Created by Duong Truong on 9/12/2017.
 */
public class ProductDetailController extends Controller{
    AnchorPane anchorPane = new AnchorPane();
    @FXML
    Label labelName;

    public void createTilePane(){
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("product_detail.fxml"));
            labelName = new Label();
            labelName = (Label) anchorPane.lookup("#labelName");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
