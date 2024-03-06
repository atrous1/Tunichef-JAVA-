package Controllers;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import Utils.DataSource;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import Services.*;
import Entities.Reclamation;

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
    private TextField Description;
    @FXML
    private DatePicker Date;

    @FXML
    private Button btn1;
    public static int id;


   /* @FXML
    void ajouterReclamation(ActionEvent event) {
        try {
            // Récupérer les valeurs des champs de saisie
            int id = 10;
            String description = Description.getText();
            int avis =(int) Avis.getValue();
            // Créer une nouvelle date pour la réclamation (utilise la date actuelle)
            Date dateRec = new Date();
            // Créer une nouvelle réclamation
            Reclamation nouvelleReclamation = new Reclamation(id, description, avis, dateRec);
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

    public int getIdUtilisateur() {
        return Login.idUser;
    }


    @FXML
    public void ajouterReclamation(javafx.event.ActionEvent actionEvent) {
        try {
            // Récupérer l'ID de l'utilisateur depuis le contrôleur "Login"
            int idUtilisateur = getIdUtilisateur();

            // Récupérer les autres valeurs des champs de saisie
            String description = Description.getText();
            int avis = (int) Avis.getValue();

            // Vérifier si la première lettre de la description est en majuscule
            if (!Character.isUpperCase(description.charAt(0))) {
                showErrorMessage("La première lettre de la description doit être en majuscule !");
                return;
            }

            // Vérifier si les champs sont vides
            if (description.trim().isEmpty() || avis == 0) {
                showErrorMessage("Veuillez remplir tous les champs !");
                return;
            }

            // Créer une nouvelle date pour la réclamation (utilise la date actuelle)
            Date dateRec = new Date();

            // Créer une nouvelle réclamation en utilisant l'ID de l'utilisateur
            Reclamation nouvelleReclamation = new Reclamation(idUtilisateur, description, avis, dateRec);

            // Ajouter la réclamation à la base de données
            serviceReclamation.ajouter(nouvelleReclamation);

            // Envoyer un SMS
            String num = "+216 20427036";
            serviceReclamation.sendSms(num, "Votre Réclamation a été envoyée, vous recevrez une réponse par mail.");

            // Envoyer un email au client

            // Afficher une confirmation à l'utilisateur
            showInformationMessage("Réclamation ajoutée avec succès !");
        } catch (Exception e) {
            // Gérer les exceptions
          //  showErrorMessage("Une erreur s'est produite lors de l'ajout de la réclamation.");
        }
    }

    // Méthode pour afficher un message d'erreur
    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Méthode pour afficher un message d'information
    private void showInformationMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}