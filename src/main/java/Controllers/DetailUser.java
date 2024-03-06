package Controllers;

import Entities.Role;
import Entities.User;
import Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField email_detail;

    @FXML
    private ImageView image_detail;

    @FXML
    private TextField nom_detail;

    @FXML
    private TextField numtel_detail;

    @FXML
    private TextField password_detail;

    @FXML
    private TextField prenom_detail;

    @FXML
    private ComboBox<String> role_detail;

    @FXML
    void Modifier(ActionEvent event) {
        try {
            int id = item_user.u.getId();

            User user = new User();
            user.setNom(nom_detail.getText());
            user.setPrenom(prenom_detail.getText());
            user.setNumtel(Integer.parseInt(numtel_detail.getText()));
            user.setEmail(email_detail.getText());
            user.setPassword(password_detail.getText());
            String role = role_detail.getValue();
            user.setRole(Role.valueOf(role));
            user.setImage(item_user.u.getImage());

            UserService userService = new UserService();

            userService.modifierUser(user, id);

            Parent page1 = FXMLLoader.load(getClass().getResource("/Affichage.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void Supprimer(ActionEvent event) {
        try {
            UserService userService = new UserService();

            userService.supprimerUser(item_user.u.getId());

            Parent page1 = FXMLLoader.load(getClass().getResource("/Affichage.fxml"));
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
        String imagePath = "C:\\xampp\\htdocs\\user_images\\" + item_user.u.getImage();
        System.out.println(imagePath);
        nom_detail.setText(String.valueOf(item_user.u.getNom()));
        prenom_detail.setText(String.valueOf(item_user.u.getPrenom()));
        ObservableList<String> list = FXCollections.observableArrayList("CLIENT", "ADMIN");
        role_detail.setItems(list);
        email_detail.setText(String.valueOf(item_user.u.getEmail()));
        password_detail.setText(String.valueOf(item_user.u.getPassword()));
        numtel_detail.setText(String.valueOf(item_user.u.getNumtel()));
        try {
            image_detail.setImage(new Image(new FileInputStream(imagePath)));
        } catch (FileNotFoundException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
    }


}
