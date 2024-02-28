package edu.esprit.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import edu.esprit.entities.Menu;
import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceProduit;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class AffichageProduit {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    private List<Produit> recDataList = FXCollections.observableArrayList();
    private ServiceProduit rec = new ServiceProduit();
    private PoduitCardController.MyListener myListener;

    @FXML
    void initialize() {
        System.out.println("hello");
        recDataList.addAll(rec.rechProduit(MenuCardController.r.getId_menu()));
        System.out.println("load data");

        if (recDataList.size() > 0) {
            myListener = new PoduitCardController.MyListener() {
                @Override
                public void onClick(Produit re) {

                }
            };
        }

        int column = 0;
        int row = 3;
        for (int i = 0; i < recDataList.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ProduitCard.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PoduitCardController item = fxmlLoader.getController();

                item.setData(recDataList.get(i).getId_produit(), recDataList.get(i).getNom_produit(), recDataList.get(i).getDescription_produit(), recDataList.get(i).getImage_produit(), recDataList.get(i).getPrix_produit(), myListener);

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
    void ajout(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/AjoutProduitController.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/AffichageMenuFront.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
