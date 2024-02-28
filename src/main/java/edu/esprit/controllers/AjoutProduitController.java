package edu.esprit.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceProduit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
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
    void ajouterp(ActionEvent event) {
        ServiceProduit sp = new ServiceProduit();
        Produit produit1 =  new Produit(nomproduit.getText(), descriptionProduit.getText() ,url_image, Integer.parseInt(prixproduit.getText()));
            sp.ajouterProduit(produit1);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("produit Ajoutée");
        alert.setContentText("produit Ajoutée !");
        alert.show();

    }

    @FXML
    void initialize() {

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




