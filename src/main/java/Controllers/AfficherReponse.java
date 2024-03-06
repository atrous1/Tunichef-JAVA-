package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Services.ServiceReclamation;
import Services.ServiceReponse;
import Entities.Reclamation;
import Entities.Reponse;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AfficherReponse implements Initializable {
    public Button Modbutt;
    @FXML
    private TableView<Reponse> reponse;
    @FXML
    private TableColumn<Reponse,String> Contenu;
    @FXML
    private TableColumn<Reponse, Date> dateRep;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ServiceReponse crudReponse = new ServiceReponse();
        ObservableList<Reponse> data = FXCollections.observableArrayList(crudReponse.getAll());
        System.out.println("///////");
        System.out.println(data);
        System.out.println("///////");
        Contenu.setCellValueFactory(new PropertyValueFactory<>("Contenu"));

        dateRep.setCellValueFactory(new PropertyValueFactory<>("dateRep"));

        // Custom cell factory for formatting date
        dateRep.setCellFactory(column -> {
            TableCell<Reponse, Date> cell = new TableCell<>() {
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
        reponse.setItems(data);

    }
    public void supprimerReponse(ActionEvent actionEvent) {
        int selectedIndex = reponse.getSelectionModel().getSelectedIndex();

        if (selectedIndex < 0 ) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucune reponse selectionnée!");
            alert.setContentText("Veuiller selectionner une réponse à supprimer");
            Optional<ButtonType> result = alert.showAndWait();
        } else{
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Look, a Confirmation Dialog");
                alert.setContentText("Are you ok with this?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    ServiceReponse ser = new ServiceReponse();
                    ser.supprimer(reponse.getSelectionModel().getSelectedItem().getIdRep());
                    ServiceReponse crudReponse = new ServiceReponse();
                    ObservableList<Reponse> data = FXCollections.observableArrayList(crudReponse.getAll());
                    System.out.println("///////");
                    System.out.println(data);
                    System.out.println("///////");
                    Contenu.setCellValueFactory(new PropertyValueFactory<>("Contenu"));

                    dateRep.setCellValueFactory(new PropertyValueFactory<>("date"));

                    reponse.setItems(data);
                    alert.setTitle("Reponse");
                    alert.setContentText("Reponse supprimée Avec succées");
                    alert.show();
                } else {

                }
                throw new SQLException("Sample SQLException");
            } catch (SQLException ex) {
                System.out.println(ex);
            }}
    }
    public void modifierRep(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifierRep.fxml"));
            Parent root = loader.load();
            ModifierRep dcc = loader.getController();
            dcc.setModifierRep(this); // Pass the current instance of AfficherReclamation
            Reponse selectedrep = reponse.getSelectionModel().getSelectedItem();
            dcc.selectedRep(selectedrep); // Pass the selected Reclamation object to modifierController
            System.out.println(selectedrep);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            Logger.getLogger(AfficherReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
