package Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

import Entities.Role;
import Entities.User;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GestionProfil {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField emailacc;

    @FXML
    private TextField mdpacc;

    @FXML
    private TextField nomacc;

    @FXML
    private TextField numtelacc;

    @FXML
    private TextField prenomacc;
    @FXML
    private ImageView imgacc;
    public String url_image;
    private String path;
    File selectedFile;

    @FXML
    void logoutt(ActionEvent event) {
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
    void Modacc(ActionEvent event) {
        try {
            int id = item_user.u.getId();

            User user = new User();
            user.setNom(nomacc.getText());
            user.setPrenom(prenomacc.getText());
            user.setNumtel(Integer.parseInt(numtelacc.getText()));
            user.setEmail(emailacc.getText());
            user.setPassword(mdpacc.getText());
            user.setImage(item_user.u.getImage());

            UserService userService = new UserService();

            userService.modifierUser(user, id);

            Parent page1 = FXMLLoader.load(getClass().getResource("/GestionProfil.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void Supp(ActionEvent event) {
            try {
                UserService userService = new UserService();

                userService.supprimerUser(item_user.u.getId());

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
    void initialize() throws FileNotFoundException {

        UserService us = new UserService();
        User userr = us.rechercheUser(Login.idUser).get(0);
        nomacc.setText(userr.getNom());
        prenomacc.setText(userr.getPrenom());
        numtelacc.setText(String.valueOf(userr.getNumtel()));
        emailacc.setText(userr.getEmail());
        mdpacc.setText(userr.getPassword());
        String imagePath = "C:/xampp/htdocs/user_images/" + userr.getImage();
        imgacc.setImage(new Image(new FileInputStream(imagePath)));
        imgacc.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });

        imgacc.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    path = null;
                    for (File file : db.getFiles()) {
                        url_image = file.getName();
                        selectedFile = new File(file.getAbsolutePath());
                        System.out.println("Drag and drop file done and path=" + file.getAbsolutePath());//file.getAbsolutePath(:\"C:\Users\X\Desktop\ScreenShot.6.png"
                        imgacc.setImage(new Image("file:" + file.getAbsolutePath()));
                        File destinationFile = new File("C:\\xampp\\htdocs\\user_images\\" + file.getName());
                        try {
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


    }
    @FXML
    void imgacc_add (MouseEvent event) {

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

            //url_image = file.toURI().toString();
            System.out.println(selectedFile.toURI().toString());
            imgacc.setImage(image1);

            // Create a new file in the destination directory
            File destinationFile = new File("C:\\xampp\\htdocs\\user_images\\" + selectedFile.getName());
            // url_image = "C:\\xampp\\htdocs\\image_trippie_cov\\" + file.getName();
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
