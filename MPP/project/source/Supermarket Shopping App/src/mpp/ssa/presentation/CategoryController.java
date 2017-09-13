package mpp.ssa.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import mpp.ssa.bus.CustomerBUS;
import mpp.ssa.bus.ProductBUS;
import mpp.ssa.domain.Product;
import mpp.ssa.domain.ProductCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duong Truong on 9/12/2017.
 */
public class CategoryController extends Controller{
    AnchorPane Category = new AnchorPane();
    ObservableList<HBoxCell> Obser;
    static HBoxCell item;

    @FXML
    ListView<HBoxCell> listView;

    public void createCategory(){
        try {
            Category = FXMLLoader.load(getClass().getResource("category_list.fxml"));
            listView = new ListView<>();
            listView = (ListView<HBoxCell>) Category.lookup("#listView");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCategory(){
        List<ProductCategory> productCategories = getProductCategoryBUS().getAllProductCategories();
        if(productCategories != null && !productCategories.isEmpty()) {
            List<HBoxCell> list = new ArrayList<>();
            for(ProductCategory category : productCategories) {
                list.add(new HBoxCell(category));
            }

            ObservableList<HBoxCell> myObservableList = FXCollections.observableList(list);
            listView.setItems(myObservableList);
            listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println(listView.getSelectionModel().getSelectedItems().get(0).CategoryId);
                }
            });
        }
    }

    public static class HBoxCell extends HBox {
        Label name = new Label();
        String labelName;
        Integer CategoryId;

        HBoxCell(ProductCategory category) {
            super();

            CategoryId = category.getCategoryId();

            labelName = category.getCategoryName();
            name.setText(labelName);
            name.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(name, Priority.ALWAYS);

            this.getChildren().addAll(name);
        }
    }

    public void handleItemClick(MouseEvent mouseEvent) {


    }
}