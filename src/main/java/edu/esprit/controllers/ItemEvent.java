package edu.esprit.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import edu.esprit.entities.Evenement;
import edu.esprit.service.ServiceEvenement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemEvent {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label date_item;

    @FXML
    private Label description_item;

    @FXML
    private ImageView image_item;

    @FXML
    private Label name_item;

    private MyListener myListener;
    private Evenement re;
    static Evenement r = new Evenement();
    private int id;
    String fullurl = "C:\\Users\\mahran\\Desktop\\New folder\\aff.png";

    public void setData(int eventId, String eventName, Date eventDate, String descrption, MyListener myListener) {

        this.id = eventId;
        this.myListener = myListener;
        name_item.setText(eventName);
        date_item.setText(eventDate.toString());
        description_item.setText(descrption);

        try {
            image_item.setImage(new Image(new FileInputStream(fullurl)));
        } catch (FileNotFoundException e) {
            System.err.println("Error loading image: " + e);
        }
    }
    @FXML
    void click(MouseEvent event) {

        ServiceEvenement rc = new ServiceEvenement();
        List<Evenement> L = new ArrayList<>();
        myListener.onClick(re);
        //covt.setId_co(Integer.parseInt(id_co.getText()));
        L = rc.rechEvent(id);
        System.out.println(L);
        r.setEventId(L.get(0).getEventId());
        r.setEventName(L.get(0).getEventName());
        r.setEventDate(L.get(0).getEventDate());
        r.setDescription(L.get(0).getDescription());

    }

    @FXML
    void initialize() {
    }

    public void onClick() {
        myListener.onClick(re);
    }

    public void setMyListener(MyListener myListener) {
        this.myListener = myListener;
    }

    public interface MyListener {

        void onClick(Evenement re);
    }

}
