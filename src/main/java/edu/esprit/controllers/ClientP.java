package edu.esprit.controllers;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import edu.esprit.entities.Evenement;
import edu.esprit.entities.Promotion;
import edu.esprit.service.ServiceEvenement;
import edu.esprit.service.ServicePromotion;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ClientP {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane grid;
    @FXML
    void retour(ActionEvent event) {

    }

    @FXML
    private ScrollPane scroPane;
    private List<Promotion> recDataList = FXCollections.observableArrayList();
    private ServicePromotion rec = new ServicePromotion();
    private ItemP.MyListener myListener;
    String fullurl = "C:\\Users\\mahran\\Desktop\\New folder\\food.jng";

    @FXML
    void initialize() {
        System.out.println("hello");
        recDataList.addAll(rec.getAll());
        System.out.println("load data");
        List<Promotion> list = rec.GetPromoEvent(ItemEvent.r.getEventId());

        int column = 0;
        int row = 3;
        for (int i = 0; i < recDataList.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/itemP.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemP item = fxmlLoader.getController();
                System.out.println("Promo details name " + recDataList.get(i).getPromotionName());
                item.setData(recDataList.get(i).getPromotionId(), recDataList.get(i).getPromotionName(), recDataList.get(i).getPrix_Promo(), recDataList.get(i).getExpirationDate(), myListener);
                //item.setData(covDataList.get(i).getDepart(), covDataList.get(i).getDestination(), covDataList.get(i).getDate_dep());

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            } catch (IOException e) {
                System.out.println("problem");
            }
        }

    }

    @FXML
    void retour(javafx.event.ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/ClientH.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



}


