package com.example.responsible_cr;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/opening_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 760);
        stage.setTitle("Hello!");
      //  wrong.setVisible(false);
       // wrong.setVisible(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}