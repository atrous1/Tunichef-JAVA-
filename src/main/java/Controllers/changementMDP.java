package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class changementMDP {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField new_pass;

    @FXML
    private TextField retype_pass;

    @FXML
    void change_pass(ActionEvent event) throws SQLException {

        UserService su = new UserService();
        int id = su.rechercheUserByEmail(OTP_Controller.emailaddress).getId();

        String password = new_pass.getText();
        String c_password = retype_pass.getText();


        if(password.equals(c_password)) {
            su.modifier_password(id, password);
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
        else
            Notifications.create()
                    .darkStyle()
                    .title("Verfiez le mot de passe")
                    .position(Pos.BOTTOM_RIGHT) // Modifier la position ici
                    .hideAfter(Duration.seconds(20))
                    .show();

    }

    @FXML
    void initialize() {
        assert new_pass != null : "fx:id=\"new_pass\" was not injected: check your FXML file 'changementMDP.fxml'.";
        assert retype_pass != null : "fx:id=\"retype_pass\" was not injected: check your FXML file 'changementMDP.fxml'.";

    }

}
