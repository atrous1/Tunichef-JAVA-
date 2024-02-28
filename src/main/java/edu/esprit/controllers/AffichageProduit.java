package edu.esprit.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceProduit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import javafx.fxml.Initializable;

public class AffichageProduit implements Initializable {

    @FXML
    private TextField moddes;

    @FXML
    private Button modifierp;

    @FXML
    private TextField modnom;

    @FXML
    private TextField modprix;

    @FXML
    private TextField suppid;
    @FXML
    private TextField modimg;

    @FXML
    private Button supprimerp;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Produit, String> casedes;

    @FXML
    private TableColumn<Produit, String> caseimg;

    @FXML
    private TableColumn<Produit, String> casenom;

    @FXML
    private TableColumn<Produit, Integer> caseprix;



    @FXML
    private TableView<Produit> tabelProduit;
      //  private ObservableList<Produit> listplats = FXCollections.observableArrayList();



    public void initialize(URL url, ResourceBundle resourceBundle) {
        rafraichirTableView();
        initializeTableView();
        FillForm();
        tabelProduit.setOnMouseClicked(event -> {
            Produit produitSelectionne = tabelProduit.getSelectionModel().getSelectedItem();
            if (produitSelectionne != null) {
                remplirFormulaire(produitSelectionne);
            }

            // Remplir le champ suppid avec l'ID du produit
            if (produitSelectionne != null) {
                suppid.setText(String.valueOf(produitSelectionne.getId_produit()));
            }
        });


    }

    public void initializeTableView() {
        ServiceProduit sproduit = new ServiceProduit();
        List<Produit> allProduct = sproduit.afficherProduits();

        ObservableList<Produit> displayedProduct = FXCollections.observableArrayList();
        displayedProduct.addAll(allProduct);
        tabelProduit.setItems(displayedProduct);
    }

    @FXML
    private void rafraichirTableView() {
        ServiceProduit ServiceProduit1 = new ServiceProduit();
        List<Produit> productList = ServiceProduit1.afficherProduits();
        ObservableList<Produit> produits = FXCollections.observableArrayList(productList);

        casenom.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        casedes.setCellValueFactory(new PropertyValueFactory<>("description_produit"));
        caseimg.setCellValueFactory(new PropertyValueFactory<>("image_produit"));
        caseprix.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));

        tabelProduit.setItems(produits);
    }
    private void remplirFormulaire(Produit produit) {
        modnom.setText(produit.getNom_produit());
        moddes.setText(produit.getDescription_produit());
        modimg.setText(produit.getImage_produit());
        modprix.setText(String.valueOf(produit.getPrix_produit()));
    }


    @FXML
    void modifierPro(ActionEvent event) {
        String nomproduitValue = modnom.getText();
        String descriptionproduitValue = moddes.getText();
        float prixproduitValue = Float.parseFloat(modprix.getText());
        String imageproduitValue = modimg.getText();

        Produit TableSelection = tabelProduit.getSelectionModel().getSelectedItem();
        int idProduit = TableSelection.getId_produit();



        if (nomproduitValue.isEmpty() || descriptionproduitValue.isEmpty()|| imageproduitValue.isEmpty() || prixproduitValue==0 ) {
            System.out.println("Veuillez remplir tous les champs");
            return;
        }
        // Création d'une instance de Plat avec les nouvelles valeurs
        Produit newproduit = new Produit(idProduit, nomproduitValue, descriptionproduitValue,imageproduitValue, prixproduitValue);

        ServiceProduit produitServiceService = new ServiceProduit(); // Créez
        System.out.println(nomproduitValue);
        System.out.println(descriptionproduitValue);
        System.out.println(imageproduitValue);
        System.out.println(prixproduitValue);

        produitServiceService.modifier(newproduit,idProduit);
        rafraichirTableView();

    }

    @FXML
    void supprimerP(ActionEvent event) throws IOException {
        try {
            int produitId = Integer.parseInt(suppid.getText());

            if (produitId <= 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setContentText("Veuillez saisir un identifiant de produit valide.");
                alert.show();
                return; // Sortir de la méthode si l'ID de catégorie est invalide
            }

            // Appeler la méthode delete de votre service ou gestionnaire de données
            ServiceProduit ps = new ServiceProduit();
            ps.supprimerProduit(produitId);

            // Afficher une alerte de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("produit supprimée");
            alert.setContentText("Le produit a été supprimée avec succès !");
            alert.show();

            // Appeler la méthode pour rafraîchir votre TableView
            rafraichirTableView();
        } catch (NumberFormatException e) {
            // Gérer les erreurs de conversion du texte en entier
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir un identifiant de produit valide.");
            alert.show();
        }


    }

    private void FillForm(){
        // Ajoutez un événement de clic sur la TableView pour mettre à jour le formulaire avec les valeurs de la ligne sélectionnée
        tabelProduit.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // Vérifiez si un clic unique a été effectué
                // Obtenez la ligne sélectionnée
                Produit PlatSelectionne = tabelProduit.getSelectionModel().getSelectedItem();
                if (PlatSelectionne != null) {
                    // Mettez à jour le formulaire avec les valeurs de la ligne sélectionnée
                    casenom.setText(PlatSelectionne.getNom_produit());
                    casedes.setText(PlatSelectionne.getDescription_produit());
                    caseprix.setText(String.valueOf(PlatSelectionne.getPrix_produit()));
                    caseimg.setText(String.valueOf(PlatSelectionne.getImage_produit()));
                }
            }
        });}

}


