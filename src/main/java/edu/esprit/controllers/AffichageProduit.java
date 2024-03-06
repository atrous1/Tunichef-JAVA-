package edu.esprit.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.javafx.charts.Legend;
import edu.esprit.entities.Like;
import edu.esprit.entities.Menu;
import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceProduit;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class AffichageProduit implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField recherche;
    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Button like_dislike_button;

    private List<Produit> recDataList = FXCollections.observableArrayList();
    private ServiceProduit rec = new ServiceProduit();
    private PoduitCardController.MyListener myListener;
    static int idUser = 4;
    private String status;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("hello");
        recDataList.addAll(rec.rechProduit(MenuCardController.r.getId_menu()));
        System.out.println("load data");

        if (recDataList.size() > 0) {
            try {
                setChosenZone(recDataList.get(0));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            myListener = new PoduitCardController.MyListener() {
                @Override
                public void onClick(Produit re) throws SQLException {
                    System.out.println("mouse clicked");
                    setChosenZone(re);
                }
            };

            recherche.textProperty().addListener((observable, oldValue, newValue) -> {
                // Appelez votre méthode de recherche avec le nouveau texte
                rechercheProduit(newValue);
            });
        }

        int column = 0;
        int row = 3;
        for (int i = 0; i < recDataList.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ProduitCard.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PoduitCardController item = fxmlLoader.getController();

                item.setData(recDataList.get(i).getId_produit(), recDataList.get(i).getNom_produit(), recDataList.get(i).getDescription_produit(), recDataList.get(i).getImage_produit(), recDataList.get(i).getPrix_produit(), myListener);

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
        }
    }

    private void setChosenZone(Produit r) throws SQLException {

        int index_like = rec.rech_index_Like(idUser,PoduitCardController.r.getId_produit());
        if (index_like != -1)
        {
            Like like = rec.rech_Like(index_like).get(0);
            if (like.getStaus().equals("like"))
                like_dislike_button.setText("dislike");
            else
                like_dislike_button.setText("like");
        }
        else
            like_dislike_button.setText("like");

    }

    @FXML
    void ajout(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/AjoutProduitController.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void like_dislike(ActionEvent event) throws SQLException {
        ServiceProduit sp = new ServiceProduit();

        if(like_dislike_button.getText().equals("like"))
        {
            like_dislike_button.setText("dislike");

            PoduitCardController.r.setLike(PoduitCardController.r.getLike()+1);

            if(PoduitCardController.r.getDislike()!=0)
                PoduitCardController.r.setDislike(PoduitCardController.r.getDislike()-1);

            sp.modifier(PoduitCardController.r, PoduitCardController.r.getId_produit());

            status = "like";

        }else {
            like_dislike_button.setText("like");

            if (PoduitCardController.r.getLike()!=0)
                PoduitCardController.r.setLike(PoduitCardController.r.getLike()-1);

            PoduitCardController.r.setDislike(PoduitCardController.r.getDislike()+1);

            sp.modifier(PoduitCardController.r, PoduitCardController.r.getId_produit());

            status = "dislike";
        }

        if (sp.rech_index_Like(idUser, PoduitCardController.r.getId_produit()) != -1)
            sp.modifier_like_dislike(sp.rech_index_Like(idUser, PoduitCardController.r.getId_produit()), status);
        else
            sp.add_like_dislike_plat(idUser, PoduitCardController.r.getId_produit(), status);
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/AffichageMenuFront.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    private void rechercheProduit(String keyword) {
        // Utilisez le mot-clé pour rechercher des produits par leur nom
        List<Produit> produitsTrouves = rec.rechercherProduitsParNom(keyword);

        // Effacez le contenu actuel de la grille
        grid.getChildren().clear();

        // Parcourez les produits trouvés et ajoutez-les à la grille
        int column = 3;//
        int row = 1;
        for (Produit produit : produitsTrouves) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ProduitCard.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PoduitCardController item = fxmlLoader.getController();

                // Configurez l'élément de la carte de produit avec les détails du produit correspondant
                item.setData(produit.getId_produit(), produit.getNom_produit(), produit.getDescription_produit(), produit.getImage_produit(), produit.getPrix_produit(), myListener);

                // Ajoutez l'élément à la grille
                grid.add(anchorPane, column++, row);

                // Si le nombre de colonnes atteint la limite, passez à la ligne suivante
                if (column == 3) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                System.out.println("Problème lors de l'ajout d'un élément à la grille : " + e.getMessage());
            }
        }
    }

    @FXML
    void rechercheP(ActionEvent event) {
        // Récupérer le texte saisi par l'utilisateur dans le champ de recherche
        String nomProduitRecherche = recherche.getText().trim();

        // Appeler la méthode rechercherProduitsParNom avec le nom du produit en argument
        List<Produit> produitsTrouves = rec.rechercherProduitsParNom(nomProduitRecherche);

        // Clear the current grid content
        grid.getChildren().clear();

        // Ajouter les nouveaux produits trouvés à votre grille
        int column = 3;//
        int row = 0;
        for (int i = 0; i < produitsTrouves.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ProduitCard.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PoduitCardController item = fxmlLoader.getController();

                item.setData(produitsTrouves.get(i).getId_produit(), produitsTrouves.get(i).getNom_produit(), produitsTrouves.get(i).getDescription_produit(), produitsTrouves.get(i).getImage_produit(), produitsTrouves.get(i).getPrix_produit(), myListener);

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
                System.out.println("Problème lors de l'ajout d'un élément à la grille : " + e.getMessage());
            }
        }
    }

}
