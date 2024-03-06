package Controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Entities.Menu;
import Entities.Produit;
import Services.ServiceMenu;
import Services.ServiceProduit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PoduitCardController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label descriptionP;

    @FXML
    private ImageView imageP;

    @FXML
    private Label nomP;

    @FXML
    private Label prixP;

    private MyListener myListener;
    private Produit re;
    static Produit r = new Produit();
    private int id;

    public void setData(int id_produit, String nom_produit, String description_produit, String image_produit, double prix_produit, MyListener myListener) {

        this.id = id_produit;
        this.myListener = myListener;
        nomP.setText(nom_produit);
        prixP.setText(String.valueOf(prix_produit));
        descriptionP.setText(description_produit);
        String fullurl = "C:\\xampp\\htdocs\\produitimg\\" + image_produit;
        System.out.println("full url " + fullurl);
//        Image image = new Image(fullurl);
//        //Image image = new Image("C:\\Users\\manou\\Desktop\\TRIPPIE-co_voiturage\\205.jpg");
//        img.setImage(image);

        try {
            imageP.setImage(new Image(new FileInputStream(fullurl)));
        } catch (FileNotFoundException e) {
            System.err.println("Error loading image: " + e);
        }
    }

    @FXML
    void Click(MouseEvent event) throws SQLException {

        ServiceProduit rc = new ServiceProduit();
        List<Produit> L = new ArrayList<>();
        myListener.onClick(re);

        L = rc.rechP(id);
        r.setId_produit(L.get(0).getId_produit());
        r.setNom_produit(L.get(0).getNom_produit());
        r.setPrix_produit(L.get(0).getPrix_produit());
        r.setImage_produit(L.get(0).getImage_produit());
        r.setLike(L.get(0).getLike());
        r.setDislike(L.get(0).getDislike());
        r.setMenu(L.get(0).getMenu());

    }


    public void onClick() throws SQLException {
        myListener.onClick(re);
    }

    public void setMyListener(MyListener myListener) {
        this.myListener = myListener;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public interface MyListener {

        void onClick(Produit re) throws SQLException;
    }

}
