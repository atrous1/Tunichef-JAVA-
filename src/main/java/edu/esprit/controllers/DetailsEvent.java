package edu.esprit.controllers;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;


public class DetailsEvent {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField event_date;

    @FXML
    private TextField event_description;

    @FXML
    private TextField event_name;

    @FXML
    private ImageView image;
    @FXML
    private Label errue;
    @FXML
    private Label erru;

    @FXML
    private ListView<Promotion> liste_promo;

    private ServiceEvenement rec = new ServiceEvenement();

    static int idEvent = ItemEvent.r.getEventId();
    static int idPromo;
    static Date date;

    @FXML
    void modifier(ActionEvent event) throws SQLException, IOException {
        int id;
        id = ItemEvent.r.getEventId();
        String name = event_name.getText();
        String description = event_description.getText();
        if (name.isEmpty()) {
            errue.setText("Nom de l'événement manquant");
            errue.setVisible(true);
        } else if (description.isEmpty()) {
            erru.setText("Description de l'événement manquante");
            erru.setVisible(true);
        } else {
            Evenement evenement = new Evenement(name, ItemEvent.r.getEventDate(), description, null);

            ServiceEvenement rc = new ServiceEvenement();

            rc.modifier(evenement, id);

            Parent page1 = FXMLLoader.load(getClass().getResource("/Home.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


        }
    }

    @FXML
    void supprimer(ActionEvent event) {

        try {
            ServiceEvenement rec = new ServiceEvenement();

            rec.supprimer(ItemEvent.r.getEventId());

            Parent page1 = FXMLLoader.load(getClass().getResource("/Home.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    @FXML
    void initialize() {
        String fullurl = "C:\\Users\\mahran\\Desktop\\New folder\\aff.png";
        System.out.println(fullurl);

        event_name.setText(ItemEvent.r.getEventName());
        event_date.setText(ItemEvent.r.getEventDate().toString());
        event_description.setText(ItemEvent.r.getDescription());

        try {
            image.setImage(new Image(new FileInputStream(fullurl)));
        } catch (FileNotFoundException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }

        ListView list2 = liste_promo;
        Promotion r = new Promotion();
        ServicePromotion rec = new ServicePromotion();
        List<Promotion> list = rec.GetPromoEvent(ItemEvent.r.getEventId());
        for (int i = 0; i < list.size(); i++) {
            Promotion promotion = list.get(i);
            list2.getItems().add(promotion);
        }
    }

    @FXML
    void details_promo(ActionEvent event) throws IOException {

        Promotion r = liste_promo.getSelectionModel().getSelectedItem(); // use getSelectedItem() to get the selected item, not getSelectedItems()*

        idPromo = r.getPromotionId();

        date = r.getExpirationDate();

        Parent page1 = FXMLLoader.load(getClass().getResource("/DetailsPromo.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void ajouter_promo(ActionEvent event) throws IOException {

        Parent page1 = FXMLLoader.load(getClass().getResource("/AjouterPromo.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Home.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
