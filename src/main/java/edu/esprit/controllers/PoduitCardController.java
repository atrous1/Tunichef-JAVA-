package edu.esprit.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.esprit.entities.Produit;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PoduitCardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label descriptionP;

    @FXML
    private ImageView imageP;

    @FXML
    private Label nomP;

    @FXML
    private Label prixP;

    private MyListener myListener;
    private Produit re;
    static Produit r = new Produit();
    private int id;

    public void setData(int id_produit, String nom_produit, String description_produit, String image_produit, double prix_produit, MyListener myListener) {

        this.id = id_produit;
        this.myListener = myListener;
        nomP.setText(nom_produit);
        prixP.setText(String.valueOf(prix_produit));
        descriptionP.setText(description_produit);
        String fullurl = "C:\\xampp\\htdocs\\produitimg\\" + image_produit;
        System.out.println("full url " + fullurl);
//        Image image = new Image(fullurl);
//        //Image image = new Image("C:\\Users\\manou\\Desktop\\TRIPPIE-co_voiturage\\205.jpg");
//        img.setImage(image);

        try {
            imageP.setImage(new Image(new FileInputStream(fullurl)));
        } catch (FileNotFoundException e) {
            System.err.println("Error loading image: " + e);
        }
    }

    @FXML
    void Click(MouseEvent event) {

    }

    @FXML
    void initialize() {
    }

    public void onClick() {
        myListener.onClick(re);
    }

    public void setMyListener(MyListener myListener) {
        this.myListener = myListener;
    }

    public interface MyListener {

        void onClick(Produit re);
    }

}
