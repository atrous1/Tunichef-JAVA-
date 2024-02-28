package edu.esprit.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import edu.esprit.entities.Evenement;
import edu.esprit.entities.Promotion;
import edu.esprit.service.ServiceEvenement;
import edu.esprit.service.ServicePromotion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DetailsPromo {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField date_promo;

    @FXML
    private TextField promotion_name;

    @FXML
    private TextField promotion_prix;

    @FXML
    void modifier(ActionEvent event) throws IOException {
        int id = DetailsEvent.idPromo;
        String name = promotion_name.getText();
        Double prix = Double.valueOf(promotion_prix.getText());

        ServiceEvenement se = new ServiceEvenement();

        Evenement evenement = se.getOneById(DetailsEvent.idEvent);

        Promotion promotion = new Promotion(name, prix, DetailsEvent.date, evenement);

        ServicePromotion sp = new ServicePromotion();

        sp.modifier(promotion, id);

        Parent page1 = FXMLLoader.load(getClass().getResource("/DetailsEvent.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void supprimer(ActionEvent event) throws IOException {

        ServicePromotion rec = new ServicePromotion();

        rec.supprimer(DetailsEvent.idPromo);

        Parent page1 = FXMLLoader.load(getClass().getResource("/DetailsEvent.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/DetailsEvent.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        ServicePromotion sp = new ServicePromotion();
        Promotion promotion = sp.getOneById(DetailsEvent.idPromo);

        promotion_name.setText(promotion.getPromotionName());
        promotion_prix.setText(String.valueOf(promotion.getPrix_Promo()));
        date_promo.setText(String.valueOf(promotion.getExpirationDate()));
    }

}
