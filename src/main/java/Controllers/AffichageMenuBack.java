package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Entities.Menu;
import Services.ServiceMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

import javafx.fxml.Initializable;

public class AffichageMenuBack implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Menu, String> casecat;

    @FXML
    private TableColumn<Menu, Integer> casenbrpage;

    @FXML
    private TableColumn<Menu, String> caseori;

    @FXML
    private TextField modcat;

    @FXML
    private Button modifierm;


    @FXML
    private TextField modnbrpage;

    @FXML
    private TextField modori;

    @FXML
    private TextField suppidm;

    @FXML
    private Button supprimerm;

    @FXML
    private TableView<Menu> tabelMenu;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        rafraichiTableView();
        initializTableView();
        FilllForm();
        tabelMenu.setOnMouseClicked(event -> {
            Menu menuSelectionne = tabelMenu.getSelectionModel().getSelectedItem();
            if (menuSelectionne != null) {
                remplirFormulaire(menuSelectionne);
            }

            // Remplir le champ suppidm avec l'ID du produit
            if (menuSelectionne != null) {
                suppidm.setText(String.valueOf(menuSelectionne.getId_menu()));
            }
        });}

        public void initializTableView () {
            ServiceMenu sproduit = new ServiceMenu();
            List<Menu> allMenu = sproduit.affichermenu();

            ObservableList<Menu> displayedProduct = FXCollections.observableArrayList();
            displayedProduct.addAll(allMenu);
            tabelMenu.setItems(displayedProduct);
        }

        @FXML
        private void rafraichiTableView () {
            ServiceMenu ServiceMenu = new ServiceMenu();
            List<Menu> productList = ServiceMenu.affichermenu();
            ObservableList<Menu> menus = FXCollections.observableArrayList(productList);

            casenbrpage.setCellValueFactory(new PropertyValueFactory<>("nbr_page"));
            casecat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            caseori.setCellValueFactory(new PropertyValueFactory<>("origine"));

            tabelMenu.setItems(menus);
        }
        private void remplirFormulaire (Menu Menu){
            modnbrpage.setText(String.valueOf(Menu.getNbr_page()));
            modcat.setText(Menu.getCategorie());
            modori.setText(Menu.getOrigine());
        }


        @FXML
        void modifierM (ActionEvent event){
            Integer nbrpademenuValue = Integer.parseInt(modnbrpage.getText());
            String categoriemenuValue = modcat.getText();
            String originemenuValue = modori.getText();

            Menu TableSelection = tabelMenu.getSelectionModel().getSelectedItem();
            int idmenu = TableSelection.getId_menu();


            if (nbrpademenuValue == 0 || categoriemenuValue.isEmpty() || originemenuValue.isEmpty() ) {
                System.out.println("Veuillez remplir tous les champs");
                return;
            }
           // ArrayList<Produit> idproValuesList = new ArrayList<>();

            Menu newmenu = new Menu(nbrpademenuValue, categoriemenuValue, originemenuValue);


            ServiceMenu menuServiceService = new ServiceMenu(); // Créez
            System.out.println(nbrpademenuValue);
            System.out.println(categoriemenuValue);
            System.out.println(categoriemenuValue);

            menuServiceService.modifiermenu(newmenu, idmenu);
            rafraichiTableView();

        }

        @FXML
        void supprimerM (ActionEvent event) throws IOException {
            try {
                int menuId = Integer.parseInt(suppidm.getText());

                if (menuId <= 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de saisie");
                    alert.setContentText("Veuillez saisir un identifiant de menu valide.");
                    alert.show();
                    return; // Sortir de la méthode si l'ID de catégorie est invalide
                }

                // Appeler la méthode delete de votre service ou gestionnaire de données
                ServiceMenu ps = new ServiceMenu();
                ps.supprimermenu(menuId);

                // Afficher une alerte de succès
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("menu supprimée");
                alert.setContentText("Le menu a été supprimée avec succès !");
                alert.show();

                // Appeler la méthode pour rafraîchir votre TableView
                rafraichiTableView();
            } catch (NumberFormatException e) {
                // Gérer les erreurs de conversion du texte en entier
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setContentText("Veuillez saisir un identifiant de menu valide.");
                alert.show();
            }


        }

        private void FilllForm () {
            // Ajoutez un événement de clic sur la TableView pour mettre à jour le formulaire avec les valeurs de la ligne sélectionnée
            tabelMenu.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1) { // Vérifiez si un clic unique a été effectué
                    // Obtenez la ligne sélectionnée
                    Menu PlatSelectionne = tabelMenu.getSelectionModel().getSelectedItem();
                    if (PlatSelectionne != null) {
                        // Mettez à jour le formulaire avec les valeurs de la ligne sélectionnée
                        casecat.setText(PlatSelectionne.getCategorie());
                        caseori.setText(PlatSelectionne.getOrigine());
                    }
                }
            });
        }

    }


