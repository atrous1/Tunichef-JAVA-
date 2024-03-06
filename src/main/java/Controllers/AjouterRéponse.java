package Controllers;
import Entities.Reponse;
import Entities.Reclamation;
import com.mysql.cj.exceptions.CJException;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import Services.*;

import java.sql.SQLException;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import Controllers.modifierController;



import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import Entities.Reponse;

public class AjouterRéponse {
    private final ServiceReponse serviceReponse = new ServiceReponse();

    public TextField Contenu;
    public Button btn1M;

    public DatePicker Datereponse;

    @FXML
    public void ajouterreponse(ActionEvent event) {
        try {
        // Récupérer les valeurs des champs de saisie
        String contenu = Contenu.getText();
        // Vérifier si le contenu contient des chiffres
        if (contenu.matches(".*\\d.*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le contenu ne peut pas contenir de chiffres !");
            alert.showAndWait();
            return; // Arrêter le traitement si le contenu contient des chiffres
        }

        // Créer une nouvelle date pour la réclamation (utilise la date actuelle)
        Date dateRep = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateRep);
        int year = cal.get(Calendar.YEAR);
        if (year < 2015 || year > 2050) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La date de réponse doit être comprise entre 2015 et 2050 !");
            alert.showAndWait();
            return; // Arrêter le traitement si la date est invalide
        }

        // Créer une nouvelle réclamation
        Reclamation rec = new Reclamation();
        rec.setIdRec(this.selectedReclamationId); // Utilisez this.selectedReclamationId pour accéder à l'ID sélectionné
        Reponse nouvelleReponse = new Reponse(rec, contenu, dateRep);

        // Ajouter la réclamation à la base de données
        serviceReponse.ajouter(nouvelleReponse);

        // Envoyer un SMS
        String num = "+216 20427036";
        serviceReponse.sendSms(num, "Votre Réponse est envoyée.");

        // Afficher une confirmation à l'utilisateur
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Réponse ajoutée avec succès !");
        alert.showAndWait();

/*
            // Recharger la vue pour afficher les réclamations
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherReponse.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 500, 700);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
*/
        } catch (NumberFormatException e) {
            // En cas d'erreur de conversion de type, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir des valeurs numériques valides pour l'ID utilisateur et l'avis !");
            alert.showAndWait();
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private int selectedReclamationId;

    public void setReclamationId(int id) {
        this.selectedReclamationId = id;
    }
    private static FXMLLoader getScene() {
        return null;
    }
}
