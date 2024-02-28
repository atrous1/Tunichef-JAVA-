package edu.esprit.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import edu.esprit.entities.Menu;
import edu.esprit.services.ServiceMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MenuCardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label categorie_menu;

    @FXML
    private ImageView image_menu;

    @FXML
    private Label nb_page;

    @FXML
    private Label origine_menu;

    private MyListener myListener;
    private Menu re;
    static Menu r = new Menu();
    private int id;

    @FXML
    void initialize() {
    }

    @FXML
    void Click(MouseEvent event) {

        ServiceMenu rc = new ServiceMenu();
        List<Menu> L = new ArrayList<>();
        myListener.onClick(re);

        L = rc.rechMenu(id);
        r.setId_menu(L.get(0).getId_menu());
        r.setNbr_page(L.get(0).getNbr_page());
        r.setCategorie(L.get(0).getCategorie());
        r.setOrigine(L.get(0).getOrigine());

    }

    public void setData(int id_menu, int nbr_page, String categorie, String origine, MyListener myListener) {

        this.id = id_menu;
        this.myListener = myListener;
        categorie_menu.setText(categorie);
        nb_page.setText(String.valueOf(nbr_page));
        origine_menu.setText(origine);

        try {
            image_menu.setImage(new Image(new FileInputStream("C:\\Users\\guerf\\Downloads\\whatsapp-icon.png")));
        } catch (FileNotFoundException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
    }

    public void onClick() {
        myListener.onClick(re);
    }

    public void setMyListener(MyListener myListener) {
        this.myListener = myListener;
    }

    public interface MyListener {

        void onClick(Menu re);
    }



}
