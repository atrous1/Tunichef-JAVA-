package Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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


        if(password.equals(c_password))
            su.modifier_password(id,password);
        else
            System.out.println("verifier votre mdp");
    }

    @FXML
    void initialize() {
        assert new_pass != null : "fx:id=\"new_pass\" was not injected: check your FXML file 'changementMDP.fxml'.";
        assert retype_pass != null : "fx:id=\"retype_pass\" was not injected: check your FXML file 'changementMDP.fxml'.";

    }

}
