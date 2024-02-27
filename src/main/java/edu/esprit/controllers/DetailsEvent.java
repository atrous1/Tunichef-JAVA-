package edu.esprit.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import edu.esprit.entities.Evenement;
import edu.esprit.service.ServiceEvenement;
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
import javafx.stage.Stage;

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
    private ServiceEvenement rec = new ServiceEvenement();

    @FXML
    void modifier(ActionEvent event) {
        try {
            int id;
            id = ItemEvent.r.getEventId();
            String name = event_name.getText();
            String description = event_description.getText();

            Evenement evenement = new Evenement(name, ItemEvent.r.getEventDate(), description, null);

            ServiceEvenement rc = new ServiceEvenement();

            rc.modifier(evenement, id);

            Parent page1 = FXMLLoader.load(getClass().getResource("/Home.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println(e);
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
    }

}
