package org.example.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.example.Services.ServiceReclamation;
import org.example.Services.ServiceReponse;
import org.example.entities.Reclamation;

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
import org.example.Services.ServiceReclamation;
import org.example.Controllers.modifierController;



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
import org.example.Services.ServiceReclamation;
import org.example.entities.Reponse;

public class AjouterRéponse {
    private final ServiceReponse serviceReponse = new ServiceReponse();

    public TextField Contenu;
    public Button btn1M;

    public DatePicker Datereponse;

    public void ajouterreponse(ActionEvent actionEvent) {
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
            Reclamation rec = new Reclamation();
            rec.setIdRec(21);
            // Créer une nouvelle date pour la réclamation (utilise la date actuelle)
              Date dateRep  = new Date();
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
            Reponse nouvelleReponse = new Reponse(rec,contenu, dateRep );
           // Reclamation nouvelleReclamation = new Reclamation(idUser, description, avis, dateRec);
            String num="+216 20427036";
            // Ajouter la réclamation à la base de données
            serviceReponse.ajouter(nouvelleReponse);
            serviceReponse.sendSms(num, "Votre Reponse est envoyé .");
            String client="atrous.yassine@esprit.tn";

            // Ajouter la réclamation à la base de données
            serviceReponse.ajouter(nouvelleReponse);

            // Afficher une confirmation à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Réponse ajoutée avec succès !");
            alert.showAndWait();

            // Recharger la vue pour afficher les réclamations
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherReponse.fxml"));
            Parent root = loader.load();
            ajouter.getScene().setRoot(root);
        } catch (NumberFormatException e) {
            // En cas d'erreur de conversion de type, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir des valeurs numériques valides pour l'ID utilisateur et l'avis !");
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static FXMLLoader getScene() {
        return null;
    }
}
