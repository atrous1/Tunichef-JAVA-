package org.example.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.Services.ServiceReclamation;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Button;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import org.example.entities.Reclamation;
import org.example.utils.DataSource;

public class AfficherReclamation implements Initializable {


    public Button modifier;

    public Button deleteId;
    @FXML
    private TableView<Reclamation> reclamation;
    @FXML
    private TableColumn<Reclamation,String> description;

    @FXML
    private TableColumn<Reclamation,Integer> avis;
    @FXML
    private TableColumn<Reclamation, Date> date;

    DataSource connexion = DataSource.getInstance();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            ServiceReclamation crud = new ServiceReclamation();
            ObservableList<Reclamation> data = FXCollections.observableArrayList(crud.getAll());
            System.out.println("///////");
            System.out.println(data);
            System.out.println("///////");
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            avis.setCellValueFactory(new PropertyValueFactory<>("avis"));
            date.setCellValueFactory(new PropertyValueFactory<>("dateRec"));

            // Custom cell factory for formatting date
            date.setCellFactory(column -> {
                TableCell<Reclamation, Date> cell = new TableCell<>() {
                    private final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                    @Override
                    protected void updateItem(Date item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(format.format(item));
                        }
                    }
                };
                return cell;
            });
            reclamation.setItems(data);

        } catch (SQLException ex) {
            System.out.println("here");
            Logger.getLogger(AfficherReclamation.class.getName()).log(Level.SEVERE, null, ex);


        }

    }




    public void delete(ActionEvent actionEvent) {
        int selectedIndex = reclamation.getSelectionModel().getSelectedIndex();

        if (selectedIndex < 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucune reclamation selectionnée!");
            alert.setContentText("Veuiller selectionner une réclamation à supprimer");
            alert.showAndWait();
        } else {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation Dialog");
            confirmationAlert.setHeaderText("Confirmer la suppression");
            confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer cette réclamation?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    ServiceReclamation ser = new ServiceReclamation();
                    ser.supprimer(reclamation.getSelectionModel().getSelectedItem().getIdRec());

                    // Rafraîchir la TableView avec les données mises à jour
                    ServiceReclamation crud = new ServiceReclamation();
                    ObservableList<Reclamation> data = FXCollections.observableArrayList(crud.getAll());
                    reclamation.setItems(data);

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Réclamation supprimée");
                    successAlert.setContentText("La réclamation a été supprimée avec succès");
                    successAlert.showAndWait();
                } catch (SQLException ex) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Erreur");
                    errorAlert.setContentText("Une erreur est survenue lors de la suppression de la réclamation");
                    errorAlert.showAndWait();
                    System.out.println(ex);
                }
            }
        }
    }


    public void modifierRec (ActionEvent actionEvent) throws IOException {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifierController.fxml"));
                Parent root = loader.load();
                modifierController dcc = loader.getController();
                dcc.setModifierController(this); // Pass the current instance of AfficherReclamation
                Reclamation selectedrec = reclamation.getSelectionModel().getSelectedItem();
                dcc.selectedRec(selectedrec); // Pass the selected Reclamation object to modifierController
                System.out.println(selectedrec);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException ex) {
                Logger.getLogger(AfficherReclamation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }






