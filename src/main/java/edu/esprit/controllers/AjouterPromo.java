package edu.esprit.controllers;

import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

public class AjouterPromo {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker DatePromo;

    @FXML
    private TextField NomPromo;

    @FXML
    private TextField PrixPromo;
    @FXML
    private Label errP;

    @FXML
    private Label errPP;

    @FXML
    void ajouter(ActionEvent event) throws IOException, SQLException {
        String nomPromo = NomPromo.getText();
        double prixPromo = Double.parseDouble(PrixPromo.getText());
        LocalDate localDate = DatePromo.getValue();
        Date date_promo = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String promotionText = PrixPromo.getText();
        if (nomPromo.isEmpty()) {
            errP.setText("Nom de promotion manquant");
            errP.setVisible(true);
        } else {

            Evenement evenement = new Evenement();
            ServiceEvenement se = new ServiceEvenement();
            evenement = se.rechEvent(ItemEvent.r.getEventId()).get(0);
            Promotion r = new Promotion(nomPromo, prixPromo, date_promo, evenement);

            ServicePromotion rc = new ServicePromotion();

            rc.ajouter(r);

            Parent page1 = FXMLLoader.load(getClass().getResource("/DetailsEvent.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }


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

    }

}
