package edu.esprit.controllers;

import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceProduit;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class cardproduct implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addC;

    @FXML
    private AnchorPane cadPro;

    @FXML
    private ImageView imgPro;

    @FXML
    private Label nomPro;

    @FXML
    private Label prixPro;

    @FXML
    private Spinner<?> spinPro;
    private Produit proData;
    private Image image;

    private void setData(Produit proData){
        this.proData = proData ;
        nomPro.setText(proData.getNom_produit());
        prixPro.setText(String.valueOf(proData.getPrix_produit()));
        String path ="File:" + proData.getImage_produit();
        image =new Image(path,199,109,false,true);
        imgPro.setImage(image);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


