package edu.esprit.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import edu.esprit.entities.Evenement;
import edu.esprit.service.ServiceEvenement;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ClientH {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label eventNam;

    @FXML
    private GridPane grid;

    @FXML
    private ImageView imag;

    @FXML
    private ScrollPane scrolPane;

    private List<Evenement> recDataList = FXCollections.observableArrayList();
    private ServiceEvenement rec = new ServiceEvenement();
    private ItemEvent.MyListener myListener;
    String fullurl = "C:\\Users\\mahran\\Desktop\\New folder\\aff.png";

    @FXML
    void Affichepromo(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/ClientP.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    private void setChosenRec(Evenement r) {

        System.out.println(ItemEvent.r.getEventName());
        eventNam.setText(ItemEvent.r.getEventName());
        System.out.println(eventNam.getText());

        try {
            imag.setImage(new Image(new FileInputStream(fullurl)));
        } catch (FileNotFoundException e) {
            System.err.println("Error loading image: " + e);
        }

    }

    @FXML
    void initialize() {
        System.out.println("hello");
        recDataList.addAll(rec.getAll());
        System.out.println("load data");
        if (recDataList.size() > 0) {
            setChosenRec(recDataList.get(0));
            myListener = new ItemEvent.MyListener() {

                @Override
                public void onClick(Evenement re) {
                    System.out.println("mouse clicked");
                    setChosenRec(re);
                }
            };
        }

        int column = 0;
        int row = 3;
        for (int i = 0; i < recDataList.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/itemEvent.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemEvent item = fxmlLoader.getController();
                System.out.println("Event details name " + recDataList.get(i).getEventName());
                item.setData(recDataList.get(i).getEventId(), recDataList.get(i).getEventName(), recDataList.get(i).getEventDate(), recDataList.get(i).getDescription(), myListener);
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
}


