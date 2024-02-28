package edu.esprit.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.google.protobuf.ByteString;
import edu.esprit.entities.Menu;
import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceMenu;
import edu.esprit.services.ServiceProduit;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class AffichageMenuFront {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane menu_grid;

    @FXML
    private AnchorPane menuform;
private ObservableList<Produit> cardListP = FXCollections.observableArrayList();

public ObservableList <Produit> menugetData(){
    ServiceProduit ServiceProduit = new ServiceProduit();
    List<Produit> cardListP = ServiceProduit.afficherProduits();
    Produit pro;



return cardListP;
}
    @FXML
    void initialize() {

    }

}
