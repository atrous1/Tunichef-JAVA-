package Controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Entities.User;
import Services.UserService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class AffichageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView det_user_img;

    @FXML
    private GridPane grid;

    @FXML
    private Label nom_details;

    @FXML
    private Label prenom_details;

    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField Chercher;

    private List<User> UserDataList = FXCollections.observableArrayList();
    private UserService userService = new UserService();
    private item_user.MyListener myListener;





    @FXML
    void out(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/Login.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException EX) {
            System.err.println(EX);

        }
    }

    @FXML
    void details(ActionEvent event) {

        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/detail_user.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);

        }

    }
    @FXML
    void search() {
        String searchTerm = Chercher.getText().trim().toLowerCase();
        List<User> searchResults = new ArrayList<>();

        for (User user : UserDataList) {
            if (user.getNom().toLowerCase().contains(searchTerm) || user.getPrenom().toLowerCase().contains(searchTerm)) {
                searchResults.add(user);
            }
        }

        displaySearchResults(searchResults);
    }

    private void displaySearchResults(List<User> searchResults) {
        // Clear the existing grid
        grid.getChildren().clear();

        int column = 0;
        int row = 3;

        for (User user : searchResults) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/item_user.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                item_user item = fxmlLoader.getController();
                item.setData(user.getId(), user.getNom(), user.getPrenom(), user.getRole(), user.getNumtel(),
                        user.getEmail(), user.getPassword(), user.getImage(), myListener);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            } catch (IOException e) {
                System.out.println("Problem loading user details");
            }
        }
    }

    @FXML
    void initialize() {
        System.out.println("hello");
        UserDataList.addAll(userService.afficherUser());
        System.out.println("load data");
        if (UserDataList.size() > 0) {
            setChosenUser(UserDataList.get(0));
            myListener = new item_user.MyListener() {

                @Override
                public void onClick(User user) {
                    System.out.println("mouse clicked");
                    setChosenUser(user);
                }
            };
        }

        int column = 0;
        int row = 3;
        for (int i = 0; i < UserDataList.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/item_user.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                item_user item = fxmlLoader.getController();
                System.out.println("user details name " + UserDataList.get(i).getNom() + " url : C:/xampp/htdocs/user_images/" + UserDataList.get(i).getImage());
                item.setData(UserDataList.get(i).getId(),UserDataList.get(i).getNom(), UserDataList.get(i).getPrenom(), UserDataList.get(i).getRole(), UserDataList.get(i).getNumtel(), UserDataList.get(i).getEmail(), UserDataList.get(i).getPassword(), UserDataList.get(i).getImage(), myListener);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            } catch (IOException e) {
                System.out.println("problem");
            }
            Chercher.textProperty().addListener((observable, oldValue, newValue) -> search());

        }

    }

    private void setChosenUser(User u) {

        System.out.println(item_user.u.getNom());
        System.out.println(item_user.u.getPrenom());
        nom_details.setText(item_user.u.getNom());
        prenom_details.setText(item_user.u.getPrenom());
        String imagePath = "C:/xampp/htdocs/user_images/" + item_user.u.getImage();
        try {
            det_user_img.setImage(new Image(new FileInputStream(imagePath)));
        } catch (FileNotFoundException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }

    }

}