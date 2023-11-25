package com.example.responsible_cr.student;

import com.example.responsible_cr.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;
import java.util.Objects;

public class dashboard_student {
    @FXML
    private AnchorPane danchore;

    @FXML
    void go_back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(HelloApplication.class.getResource("fxml/student/student_login.fxml"));
        switch_scene(root,event);

    }

    @FXML
    void go_routine(ActionEvent event) throws IOException {

        AnchorPane fxml = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("fxml/student/student_routine.fxml")));
        danchore.getChildren().removeAll();
        danchore.getChildren().setAll(fxml);
    }


    @FXML
    void go_notes(ActionEvent event) throws IOException {

        AnchorPane fxml = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("fxml/student/student_note.fxml")));
        danchore.getChildren().removeAll();
        danchore.getChildren().setAll(fxml);

    }

    @FXML
    void go_notice(ActionEvent event) throws IOException {
        AnchorPane fxml = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("fxml/student/student_notice.fxml")));
        danchore.getChildren().removeAll();
        danchore.getChildren().setAll(fxml);
    }

    @FXML
    void go_exam(ActionEvent event) throws IOException {

        AnchorPane fxml = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("fxml/student/student_exam.fxml")));
        danchore.getChildren().removeAll();
        danchore.getChildren().setAll(fxml);

    }

    @FXML
    void go_vote(ActionEvent event) throws IOException {

        AnchorPane fxml = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("fxml/student/student_vote.fxml")));
        danchore.getChildren().removeAll();
        danchore.getChildren().setAll(fxml);
    }


    public void switch_scene(Parent root, EventObject event) throws IOException
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Responsible CR");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}