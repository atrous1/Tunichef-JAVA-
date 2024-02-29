package org.example.Controllers;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import org.example.Services.ServiceReclamation;
import org.example.entities.Reclamation;

import java.io.IOException;

public class ajouter {
    private final ServiceReclamation serviceReclamation = new ServiceReclamation();
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Slider Avis;
    @FXML
    private TextField idUser;
    @FXML
    private TextField Description;
    @FXML
    private DatePicker Date;

    @FXML
    private Button btn1;

   /* @FXML
    void ajouterReclamation(ActionEvent event) {
        try {
            // Récupérer les valeurs des champs de saisie
            int idUser = 10;
            String description = Description.getText();
            int avis =(int) Avis.getValue();
            // Créer une nouvelle date pour la réclamation (utilise la date actuelle)
            Date dateRec = new Date();
            // Créer une nouvelle réclamation
            Reclamation nouvelleReclamation = new Reclamation(idUser, description, avis, dateRec);
            // Ajouter la réclamation à la base de données
            serviceReclamation.ajouter(nouvelleReclamation);

            // Afficher une confirmation à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Réclamation ajoutée avec succès !");
            alert.showAndWait();

            // Recharger la vue pour afficher les réclamations
           /* FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherReclamation.fxml"));
            Parent root = loader.load();
            ajouter.getScene().setRoot(root);*/
      /*  } catch (NumberFormatException | SQLException e) {
            // En cas d'erreur de conversion de type, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir des valeurs numériques valides pour l'ID utilisateur et l'avis !");
            alert.showAndWait();
        }
    } */

    static FXMLLoader getScene() {
        return null;
    }


    @FXML
    void initialize() {

    }

    public void ajouterReclamation(javafx.event.ActionEvent actionEvent) {
        try {
            // Récupérer les valeurs des champs de saisie
            int idUser = 30;
            String description = Description.getText();
            int avis = (int) Avis.getValue();

            // Vérifier si la première lettre de la description est en majuscule
            if (!Character.isUpperCase(description.charAt(0))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("La première lettre de la description doit être en majuscule !");
                alert.showAndWait();
                return; // Arrêter le traitement si la première lettre n'est pas en majuscule
            }

            // Vérifier si les champs sont vides
            if (description.trim().isEmpty() || avis == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs !");
                alert.showAndWait();
                return; // Arrêter le traitement si un champ est vide
            }

            // Créer une nouvelle date pour la réclamation (utilise la date actuelle)
            Date dateRec = new Date();

            // Créer une nouvelle réclamation
            Reclamation nouvelleReclamation = new Reclamation(idUser, description, avis, dateRec);

            // Ajouter la réclamation à la base de données
            serviceReclamation.ajouter(nouvelleReclamation);

            // Afficher une confirmation à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Réclamation ajoutée avec succès !");
            alert.showAndWait();

            // Recharger la vue pour afficher les réclamations
        /* FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherReclamation.fxml"));
        Parent root = loader.load();
        ajouter.getScene().setRoot(root);*/
        } catch (NumberFormatException | SQLException e) {}
    }
}