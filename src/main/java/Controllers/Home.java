package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Home {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button acc;

    @FXML
    void Acceuil (ActionEvent event) {
        try {

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
    void Profil(ActionEvent event) {
        try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/GestionProfil.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.err.println(ex);

    }


    }
    @FXML
    void logout(ActionEvent event) {
        try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Login.fxml"));
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

    }

}

