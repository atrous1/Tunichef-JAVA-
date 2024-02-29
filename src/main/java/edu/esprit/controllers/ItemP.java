package edu.esprit.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import edu.esprit.entities.Evenement;
import edu.esprit.entities.Promotion;
import edu.esprit.service.ServicePromotion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemP {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Prix_item;

    @FXML
    private Label dat_item;

    @FXML
    private ImageView imag_item;

    @FXML
    private Label nam_item;
    private ItemP.MyListener myListener;
    private Promotion Pe;
    static Promotion r = new Promotion();
    private int id;
    String fullurl = "C:\\Users\\mahran\\Desktop\\New folder\\food.png";

    public void setData(int promotionId, String promotionName , double prix_Promo, Date expirationDate, ItemP.MyListener myListener) {

        this.id = promotionId;
        this.myListener = myListener;
        nam_item.setText(promotionName);
        dat_item.setText(expirationDate.toString());
        Prix_item.setText(String.valueOf(prix_Promo));

        try {
            imag_item.setImage(new Image(new FileInputStream(fullurl)));
        } catch (FileNotFoundException e) {
            System.err.println("Error loading image: " + e);
        }
    }

    @FXML
    void click(MouseEvent event) {
        ServicePromotion rc = new ServicePromotion();
        List<Promotion> L = new ArrayList<>();
        myListener.onClick(Pe);
        //covt.setId_co(Integer.parseInt(id_co.getText()));
        L = rc.rechPromo(id);
        System.out.println(L);
        r.setPromotionId(L.get(0).getPromotionId());
        r.setPromotionName(L.get(0).getPromotionName());
        r.setPrix_Promo(L.get(0).getPrix_Promo());
        r.setExpirationDate(L.get(0).getExpirationDate());

    }

    @FXML
    void initialize() {


    }
    public void onClick() {
        myListener.onClick(Pe);
    }

    public void setMyListener(ItemP.MyListener myListener) {
        this.myListener = myListener;
    }

    public interface MyListener {

        void onClick(Promotion Pe);
    }

}
