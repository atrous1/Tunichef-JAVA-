package org.example.Controllers;

import com.sun.javafx.charts.Legend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.Services.ServiceReclamation;
import javafx.scene.layout.BorderPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import javafx.scene.control.TextField;

import java.awt.*;
//import java.awt.TextField;
import java.io.File;
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
import javafx.scene.control.*;
import org.example.entities.Reclamation;

public class AfficherReclamation implements Initializable {

    private Reclamation addedReclamation;
    public Button modifier;
    @FXML
    private Button boutonPDF;
    public Button deleteId;
    public Button pdf;
    @FXML
    private TableView<Reclamation> reclamation;
    @FXML
    private TableColumn<Reclamation,String> description;

    @FXML
    private TableColumn<Reclamation,Integer> avis;
    @FXML
    private TableColumn<Reclamation, Date> date;
    // AJOUTER RECHERCHE
    @FXML
    private TextField searchField;

    @FXML
    private void handleGenererPDF(ActionEvent event) {
        genererPDF();
    }


    private void genererPDF() {
        Reclamation selectedReclamation = reclamation.getSelectionModel().getSelectedItem(); // Récupérer la réclamation sélectionnée
        if (selectedReclamation != null) {
            try {
                PDDocument document = new PDDocument();
                PDPage page = new PDPage();
                document.addPage(page);

                PDPageContentStream contentStream = new PDPageContentStream(document, page);

                PDType0Font font = PDType0Font.load(document, getClass().getResourceAsStream("/fonts/CairoPlay-VariableFont_slntwght.ttf"));

                float margin = 0;

                PDImageXObject borderImage = PDImageXObject.createFromFile("C:\\Users\\USER\\IdeaProjects\\gestionReclamation\\src\\main\\resources\\Assets\\CadrePDf.jpg", document);
                contentStream.drawImage(borderImage, margin, margin, page.getMediaBox().getWidth() - 2 * margin, page.getMediaBox().getHeight() - 2 * margin);

                float titleFontSize = 25;
                contentStream.setNonStrokingColor(Color.BLACK);
                contentStream.setFont(font, titleFontSize);
                float titleX = (page.getMediaBox().getWidth() - font.getStringWidth("Détails de Réclamation") / 1000 * titleFontSize) / 2 + 40;
                float titleY = page.getMediaBox().getHeight() - 3 * (margin + 50);
                contentStream.setNonStrokingColor(new Color(0, 0, 139));
                writeText(contentStream, "Détails de réclamation", titleX, titleY, font, titleFontSize);

                float normalFontSize = 14;
                contentStream.setFont(font, normalFontSize);

                float infoX = (margin + 30) * 3;
                float infoY = titleY - normalFontSize * 6;
                float infoSpacing = normalFontSize * 2;

                contentStream.setNonStrokingColor(Color.BLACK);

                // Création du tableau
                float tableTopY = infoY - normalFontSize;
                float tableBottomY = margin + 30; // Hauteur de la table

                float column1X = infoX; // Position X de la première colonne
                float column2X = column1X + 200; // Position X de la deuxième colonne
                float column3X = column2X + 200; // Position X de la troisième colonne

                // En-têtes de colonne
                writeText(contentStream, "Date: " + formatDate(selectedReclamation.getDateRec()), column1X, tableTopY, font, normalFontSize);
                writeText(contentStream, "Description: " +selectedReclamation.getDescription(), column2X, tableTopY, font, normalFontSize);
                writeText(contentStream, "Avis: " + String.valueOf(selectedReclamation.getAvis()), column3X, tableTopY, font, normalFontSize);

                // Contenu de la ligne
                contentStream.close(); // Fermer le flux de contenu

                // Utiliser le nom de la réclamation pour nommer le fichier PDF
                File file = new File(selectedReclamation.getDescription() + ".pdf");
                document.save(file);
                document.close();

                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }
    private void writeText(PDPageContentStream contentStream, String text, float x, float y, PDType0Font font, float fontSize) throws IOException {
        String[] lines = text.split("\n");
        float leading = 1.5f * fontSize; // Ajustez l'espacement des lignes selon la taille de la police

        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset(x, y);

        for (String line : lines) {
            contentStream.showText(line);
            contentStream.newLineAtOffset(0, -leading);
        }

        contentStream.endText();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            addedReclamation = new Reclamation(); // Initialisation appropriée de votre application

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
            searchField.setOnAction(event -> {
                filterReclamations(searchField.getText(), data);
            });

            boutonPDF.setOnAction(event -> genererPDF());

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
                reclamation.getItems().remove(selectedIndex);
                ServiceReclamation serviceReclamation = new ServiceReclamation();
                serviceReclamation.supprimer(selectedIndex);
            }
        }

    }
    @FXML
    private void filterReclamations(String keyword, ObservableList<Reclamation> originalList) {
        ObservableList<Reclamation> filteredList = FXCollections.observableArrayList();
        for (Reclamation rec : originalList) {
            if (rec.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(rec);
            }
        }
        reclamation.setItems(filteredList);
    }
@FXML
    public void filterReclamations(ActionEvent actionEvent) {
    }
    @FXML
    private void sortReclamationsByAvis(ActionEvent event) {
        ObservableList<Reclamation> sortedList = FXCollections.observableArrayList(reclamation.getItems());
        sortedList.sort((rec1, rec2) -> Integer.compare(rec2.getAvis(), rec1.getAvis())); // inversion de l'ordre de tri
        reclamation.setItems(sortedList);
    }
}
