package edu.esprit.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.util.List;
import java.lang.String;
import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceProduit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class BackProduit implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Produit, String> caseP;

    @FXML
    private TableColumn<Produit, String> casedes;

    @FXML
    private TableColumn<Produit, String> caseimg;

    @FXML
    private TableColumn<Produit, Double> caseprix;

    @FXML
    private TableColumn<Menu, String> catMenu;
    @FXML
    private TextField suppidp;
    @FXML
    private TextField descP;

    @FXML
    private TextField imgP;

    @FXML
    private TextField nomP;

    @FXML
    private TextField prixP;
    @FXML
    private ComboBox<?> choixm;
    @FXML
    private TableView<Produit> tableProduit;

    @FXML
    void ajouterP(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/AjoutProduitController.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rafraichiTableView();
        initializTableView();
        FilllForm();
        tableProduit.setOnMouseClicked(event -> {
            Produit ProduitSelectionne = tableProduit.getSelectionModel().getSelectedItem();
            if (ProduitSelectionne != null) {
                remplirFormulaire(ProduitSelectionne);
            }

            // Remplir le champ suppidm avec l'ID du produit
            if (ProduitSelectionne != null) {
                suppidp.setText(String.valueOf(ProduitSelectionne.getId_produit()));
            }
        });}

    public void initializTableView () {
        ServiceProduit sproduit = new ServiceProduit();
        List<Produit> allProduit = sproduit.affichercat();

        ObservableList<Produit> displayedProduct = FXCollections.observableArrayList();
        displayedProduct.addAll(allProduit);
        tableProduit.setItems(displayedProduct);
    }

    @FXML
    private void rafraichiTableView () {
        ServiceProduit ServiceProduit = new ServiceProduit();
        List<Produit> productList = ServiceProduit.affichercat();
        ObservableList<Produit> Produits = FXCollections.observableArrayList(productList);

        caseP.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        casedes.setCellValueFactory(new PropertyValueFactory<>("description_produit"));
        caseimg.setCellValueFactory(new PropertyValueFactory<>("image_produit"));
        caseprix.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));
        catMenu.setCellValueFactory(new PropertyValueFactory<>("categorie"));


        tableProduit.setItems(Produits);
    }
    private void remplirFormulaire(Produit produit) {
        // Définir le nom du produit
        nomP.setText(produit.getNom_produit());

        // Définir la description du produit
        descP.setText(produit.getDescription_produit());

        // Définir l'image du produit
        imgP.setText(produit.getImage_produit());

        // Définir le prix du produit
        try {
            double prix = Double.parseDouble(String.valueOf(produit.getPrix_produit()));
            prixP.setText(String.format("%.2f", prix));
        } catch (NumberFormatException e) {
            // Gérer l'exception de conversion du prix
            System.err.println("Le prix du produit n'est pas un nombre valide.");
        }
    }



    @FXML
    void modifierP (ActionEvent event){
        String nomProduitValue = nomP.getText();
        String descProduitValue = descP.getText();
        String imgProduitValue = imgP.getText();
        Double prixProduitValue;

        // Vérifier si le champ prixP est vide
        String prixProduitText = prixP.getText().trim();
        if (prixProduitText.isEmpty()) {
            System.out.println("Veuillez saisir un prix valide");
            return;
        }

        // Convertir le prix en Double et gérer l'exception
        try {
            prixProduitValue = Double.parseDouble(prixProduitText);
        } catch (NumberFormatException e) {
            System.out.println("Le prix saisi est invalide. Veuillez saisir un nombre.");
            return;
        }

        Produit TableSelection = tableProduit.getSelectionModel().getSelectedItem();
        int idProduit = TableSelection.getId_produit();


        if (nomProduitValue.isEmpty() || descProduitValue.isEmpty() || imgProduitValue.isEmpty() || prixProduitValue == 0 ) {
            System.out.println("Veuillez remplir tous les champs");
            return;
        }
        // ArrayList<Produit> idproValuesList = new ArrayList<>();

        Produit newProduit = new Produit(nomProduitValue, descProduitValue, imgProduitValue,prixProduitValue);


        ServiceProduit ProduitServiceService = new ServiceProduit(); // Créez
        System.out.println(nomProduitValue);
        System.out.println(descProduitValue);
        System.out.println(descProduitValue);
        System.out.println(prixProduitValue);

        ProduitServiceService.modifier(newProduit, idProduit);
        rafraichiTableView();

    }

    @FXML
    void supprimerP (ActionEvent event) throws IOException {
        try {
            int ProduitId = Integer.parseInt(suppidp.getText());

            if (ProduitId <= 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setContentText("Veuillez saisir un identifiant de Produit valide.");
                alert.show();
                return; // Sortir de la méthode si l'ID de catégorie est invalide
            }

            // Appeler la méthode delete de votre service ou gestionnaire de données
            ServiceProduit ps = new ServiceProduit();
            ps.supprimerProduit(ProduitId);

            // Afficher une alerte de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Produit supprimée");
            alert.setContentText("Le Produit a été supprimée avec succès !");
            alert.show();

            // Appeler la méthode pour rafraîchir votre TableView
            rafraichiTableView();
        } catch (NumberFormatException e) {
            // Gérer les erreurs de conversion du texte en entier
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir un identifiant de Produit valide.");
            alert.show();
        }


    }



    private void FilllForm() {
        tableProduit.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Produit produitSelectionne = tableProduit.getSelectionModel().getSelectedItem();

                if (produitSelectionne != null) {
                    // Mettez à jour le formulaire avec les valeurs de la ligne sélectionnée
                    caseP.setText(produitSelectionne.getNom_produit());
                    casedes.setText(produitSelectionne.getDescription_produit());
                    caseimg.setText(produitSelectionne.getImage_produit());
                }
            }
        });
    }
}



