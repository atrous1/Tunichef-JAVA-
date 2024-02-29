package Controllers;


import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.input.KeyEvent;

import Utils.DataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login implements Initializable  {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField email_login;

    @FXML
    private Button login;

    @FXML
    private PasswordField mdp_login;
    @FXML
    private ToggleButton toggleButton;
    @FXML
    private Label ShownPassword;
    public static int idUser;

    @FXML
    void passwordFieldKeyTyped(KeyEvent event) {
        ShownPassword.textProperty().bind(Bindings.concat(mdp_login.getText()));

    }

    @FXML
    void toggleButton(ActionEvent event) {
        if(toggleButton.isSelected()){
            ShownPassword.setVisible(true);
            ShownPassword.textProperty().bind(Bindings.concat(mdp_login.getText()));
            toggleButton.setText("Hide");
        }else {
            ShownPassword.setVisible(false);
            toggleButton.setText("Show");
        }
        }

        @FXML
        void onLoginButtonClick(MouseEvent event) throws SQLException {
            String email = email_login.getText();
            String password = mdp_login.getText();

            Connection connection = DataSource.getInstance().getCnx();  // Get connection

                String query = "SELECT * FROM user WHERE email = ? AND password = ?";  // Query
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();  // Execute query

                if (resultSet.next()) {  // If user exists
                    String role = resultSet.getString("role");  // Get role from database
                    idUser=resultSet.getInt("id");

                    try {  // Handle FXML loading exceptions
                        if (role.equals("ADMIN")) {
                            Parent page1 = FXMLLoader.load(getClass().getResource("/Affichage.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } else if (role.equals("CLIENT")) {
                            Parent page1 = FXMLLoader.load(getClass().getResource("/Home.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } else {
                            System.out.println("Unknown user role: " + role);  // Handle unknown roles
                        }
                    } catch (IOException e) {  // Catch FXML loading exceptions
                        System.out.println("Error loading FXML: " + e.getMessage());
                    }
                } else {  // User not found
                    System.out.println("Invalid credentials.");  // Display error message
                    // ... handle incorrect credentials (e.g., display error message to user)
                }

        }
    @FXML
    void inscri(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/interface.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);

        }

    }

    @FXML
    void otp(ActionEvent event) {

        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/otp.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
ShownPassword.setVisible(false);
    }
}
