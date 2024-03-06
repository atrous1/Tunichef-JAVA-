package edu.esprit.tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class HelloApplication extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/BackProduit.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 669, 464);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Tunichef");
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
