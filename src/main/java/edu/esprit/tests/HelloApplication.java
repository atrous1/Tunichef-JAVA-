package edu.esprit.tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AjoutMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Tunichef");
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
