package Controllers;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Entities.Menu;
import Entities.Produit;
import Services.ServiceMenu;
import Services.ServiceProduit;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AjoutMenu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ajoutermenu;

    @FXML
    private TextField categoriep;

    @FXML
    private ComboBox<Produit> idp;

    @FXML
    private TextField nbrpage;

    @FXML
    private TextField originep;
    private Connection cnx;

    @FXML
    void ajouteM(ActionEvent event) throws SQLException {
        ServiceMenu sp = new ServiceMenu();
        List<Produit> produits = fetchProduits();

        Menu menu1 = new Menu(Integer.parseInt(nbrpage.getText()), categoriep.getText(), originep.getText());

        sp.ajouterMenu(menu1);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("menu Ajoutée");
        alert.setContentText("menu Ajoutée !");
        alert.show();

    }

    private List<Produit> fetchProduits() {
        ServiceProduit sp = new ServiceProduit(); // Assuming you have an instance of the service
        return sp.afficherProduits();
    }



    @FXML
    void initialize() {
        List<Produit> produit = fetchProduits();
        idp.setItems(FXCollections.observableArrayList(produit)); // Now using List<Produit>
    }






}
