package edu.esprit.controllers;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import edu.esprit.entities.Menu;
import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceMenu;
import edu.esprit.services.ServiceProduit;
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
    private ComboBox<String> categoriep;

    @FXML
    private ComboBox<Produit> idp;

    @FXML
    private TextField nbrpage;

    @FXML
    private TextField originep;

    @FXML
    void ajouteM(ActionEvent event) {
        ServiceMenu sp = new ServiceMenu();
        Menu menu1 = new Menu(Integer.parseInt(nbrpage.getText()),
                (String) categoriep.getValue(),
                originep.getText(),
                (List<Produit>) idp.getValue() );

        sp.ajouterMenu(menu1);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("menu Ajoutée");
        alert.setContentText("menu Ajoutée !");
        alert.show();

    }

    @FXML
    void initialize() {


    }

}
