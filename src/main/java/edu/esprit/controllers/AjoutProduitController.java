package edu.esprit.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import edu.esprit.entities.Menu;
import edu.esprit.services.ServiceMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceProduit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class AjoutProduitController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField descriptionProduit;

    @FXML
    private ImageView imageproduit;

    @FXML
    private TextField nomproduit;

    @FXML
    private TextField prixproduit;

    @FXML
    private Button ajouterproduit;

    public String url_image;
    private String path;
    File selectedFile;

    @FXML
    private ChoiceBox<Menu> choixM;
    private String nomProduit;


    @FXML
    void ajouterp(ActionEvent event) throws SQLException, IOException {
        ServiceProduit sp = new ServiceProduit();
        ServiceMenu sm = new ServiceMenu();
        int id = MenuCardController.r.getId_menu();
        Menu menu = sm.rechMenu(id).get(0);

        String nomProduit = nomproduit.getText();
        String descriptionProduitText = descriptionProduit.getText();
        String prixProduitText = prixproduit.getText();

        // Vérifier si le nom commence par une majuscule et ne contient pas de chiffre
        if (!nomProduit.matches("^[A-Z][a-zA-Z]*$") || nomProduit.matches(".*\\d.*")) {
            displayErrorAlert("Erreur de saisie", "Le nom du produit doit commencer par une majuscule et ne doit pas contenir de chiffre.");
            return; // Sortir de la méthode si la condition n'est pas respectée
        }

        // Vérifier si le prix est un nombre
        if (!prixProduitText.matches("\\d+")) {
            displayErrorAlert("Erreur de saisie", "Le prix du produit doit contenir uniquement des chiffres.");
            return;
        }

        // Vérifier si la description contient uniquement des lettres
        if (!descriptionProduitText.matches("[a-zA-Z]+")) {
            displayErrorAlert("Erreur de saisie", "La description du produit doit contenir uniquement des lettres.");
            return;
        }

        Produit produit1 = new Produit(nomProduit, descriptionProduitText, url_image, Integer.parseInt(prixProduitText), menu);
        sp.ajouterProduit(produit1);

        displayInfoAlert("Produit ajouté", "Le produit a été ajouté avec succès !");

        Parent page1 = FXMLLoader.load(getClass().getResource("/AffichageProduit.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void displayErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    private void displayInfoAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }


    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setContentText(message);
        alert.show();
    }

    private void afficherMessageInformation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Produit ajouté");
        alert.setContentText(message);
        alert.show();
    }


    @FXML
    void initialize() {
        // Récupérer les catégories de la table Menu
        ServiceMenu serviceMenu = new ServiceMenu();
        ObservableList<Menu> menus = FXCollections.observableArrayList(serviceMenu.affichermenu());

        // Ajouter les catégories au ChoiceBox
        choixM.setItems(menus);

        // Définir un gestionnaire d'événements pour le ChoiceBox
        choixM.setOnAction(event -> {
            Menu selectedMenu = choixM.getValue();
            if (selectedMenu != null) {
                int idMenu = selectedMenu.getId_menu();
                // Utiliser idMenu lors de l'ajout du produit
                System.out.println("Catégorie sélectionnée : " + selectedMenu.getCategorie() );
            }
        });
        imageproduit.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });

        imageproduit.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    path = null;
                    for (File file : db.getFiles()) {
                        url_image = file.getName();
                        selectedFile = new File(file.getAbsolutePath());
                        System.out.println("Drag and drop file done and path=" + file.getAbsolutePath());//file.getAbsolutePath(😕"C:\Users\X\Desktop\ScreenShot.6.png"
                        imageproduit.setImage(new Image("file:" + file.getAbsolutePath()));
                        File destinationFile = new File("C:\\xampp\\htdocs\\produitimg\\" + file.getName());
                        try {
                            // Copy the selected file to the destination file
                            Files.copy(file.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            System.err.println(e);
                        }
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        imageproduit.setImage(new Image("file:C:\\Users\\houss\\OneDrive\\Bureau\\drag-drop.gif"));

    }
    @FXML
    void image_addp (MouseEvent event) {

        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", ".jpg", ".png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            // Load the selected image into the image view
            Image image1 = new Image(selectedFile.toURI().toString());

            //url_image = selectedFile.toURI().toString();
            System.out.println(selectedFile.toURI().toString());
            imageproduit.setImage(image1);

            // Create a new file in the destination directory
            File destinationFile = new File("C:\\xampp\\htdocs\\produitimg\\" + selectedFile.getName());
            url_image = selectedFile.getName();

            try {
                // Copy the selected file to the destination file
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.err.println(e);
            }

        }

    }

}




