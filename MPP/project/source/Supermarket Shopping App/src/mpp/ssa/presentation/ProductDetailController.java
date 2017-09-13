package mpp.ssa.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class ProductDetailController extends HomeController {
    AnchorPane anchorPane = new AnchorPane();
    @FXML
    Label labelName;
    @FXML
    Label labelCost;
    @FXML
    private ComboBox comboBox;

    @FXML
    public void handleBackBtn2(ActionEvent event){
        Main.primaryStage.setScene(prviousScene);
    }

    public void createTilePane(){
        try {
            HomeController.setPrviousScene();
            anchorPane = FXMLLoader.load(getClass().getResource("productDetail.fxml"));
            labelName = new Label();
            labelName = (Label) anchorPane.lookup("#labelName");
            labelCost = new Label();
            labelCost = (Label) anchorPane.lookup("#labelCost");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddToCart(ActionEvent event) {
        System.out.println(comboBox.getValue().toString());
    }
}
