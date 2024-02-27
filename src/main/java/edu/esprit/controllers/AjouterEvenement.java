package edu.esprit.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjouterEvenement {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker DateEvent;

    @FXML
    private TextField Description;

    @FXML
    private TextField NomEvent;

    @FXML
    void ajouter(ActionEvent event) throws SQLException, IOException {
        String nom_event = NomEvent.getText();
        LocalDate localDate = LocalDate.now();
        Date date_event = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String description = Description.getText();

        Evenement r = new Evenement(nom_event, date_event, description, null);

        ServiceEvenement rc = new ServiceEvenement();

        rc.ajouter(r);

        Parent page1 = FXMLLoader.load(getClass().getResource("/Home.fxml"));
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

    @FXML
    void initialize() {
    }

}
