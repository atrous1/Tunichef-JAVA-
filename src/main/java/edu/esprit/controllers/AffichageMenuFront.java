package edu.esprit.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import edu.esprit.entities.Menu;
import edu.esprit.services.ServiceMenu;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class AffichageMenuFront {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private GridPane grid;

    @FXML
    private ImageView image;

    @FXML
    private Label categorie;

    @FXML
    private ScrollPane scroll;

    private List<Menu> recDataList = FXCollections.observableArrayList();
    private ServiceMenu rec = new ServiceMenu();
    private MenuCardController.MyListener myListener;

    @FXML
    void details(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/AffichageProduit.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void initialize() {
        System.out.println("hello");
        recDataList.addAll(rec.affichermenu());
        System.out.println("load data");
        if (recDataList.size() > 0) {
            setChosenRec(recDataList.get(0));
            myListener = new MenuCardController.MyListener() {

                @Override
                public void onClick(Menu re) {
                    System.out.println("mouse clicked");
                    setChosenRec(re);
                }
            };
        }

        int column = 0;
        int row = 3;
        for (int i = 0; i < recDataList.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cardproduct.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                MenuCardController item = fxmlLoader.getController();

                item.setData(recDataList.get(i).getId_menu(), recDataList.get(i).getNbr_page(), recDataList.get(i).getCategorie(), recDataList.get(i).getOrigine(), myListener);

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

    private void setChosenRec(Menu r) {

        categorie.setText(MenuCardController.r.getCategorie());

        try {
            image.setImage(new Image(new FileInputStream("C:\\Users\\houss\\OneDrive\\Bureau\\menu2.jpg")));
        } catch (FileNotFoundException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }

    }

}
