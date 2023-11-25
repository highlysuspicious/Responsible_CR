package com.example.responsible_cr;

import com.example.responsible_cr.student.student_vote;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.EventObject;
import java.util.List;
import java.util.Scanner;

public class login {

    @FXML
    private TextField usernameField;
    @FXML
    public Label wrong;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;
    public Stage stage = new Stage();
    public Scene scene;
    public  Parent root;
    public Parent root2;


    public void initialize() {

    }


    @FXML
    void go_cr_login(ActionEvent event) throws IOException {

        root = FXMLLoader.load(HelloApplication.class.getResource("fxml/CR/cr_login.fxml"));
        switch_scene(root,event);

    }


    @FXML
    void go_faculty_login(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("fxml/faculty/faculty_login.fxml"));
        switch_scene(root,event);
    }

    @FXML
    void go_student_login(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("fxml/student/student_login.fxml"));
        switch_scene(root,event);
    }

    @FXML
    void back_opening(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("fxml/opening_page.fxml"));
        switch_scene(root,event);
    }

    public void student_login(ActionEvent event) throws IOException {

        String u = usernameField.getText();
        String p = passwordField.getText();
        System.out.println(u);
        System.out.println(p);

        File infoFile2 = new File("src/main/resources/com/example/responsible_cr/files/student/"+u+"/info.txt");
        if (infoFile2.exists()) {

            Scanner sc = new Scanner(infoFile2);
            sc.useDelimiter("\n");

            String n = sc.next();
            n = sc.next();


            System.out.println(n);

            if (n.equals(p)) {

                student_vote sv = new student_vote();
                sv.set_user_name(u);

                root = FXMLLoader.load(HelloApplication.class.getResource("fxml/student/dashboard_student.fxml"));
                switch_scene(root,event);
            }
            else {wrong.setText("sorry,wrong password,try again");}


        }
        else
        { wrong.setText("user does not exist");}
    }

    public void faculty_login(ActionEvent event) throws IOException {
        String u = usernameField.getText();
        String p = passwordField.getText();
        System.out.println(u);
        System.out.println(p);

        File infoFile2 = new File("src/main/resources/com/example/responsible_cr/files/faculty/"+u+"/info.txt");
        if (infoFile2.exists()) {
            Scanner sc = new Scanner(infoFile2);
            sc.useDelimiter("\n");

            String n = sc.next();
            n = sc.next();


            System.out.println(n);

            if (n.equals(p)) {

                root = FXMLLoader.load(HelloApplication.class.getResource("fxml/faculty/dashboard_faculty.fxml"));
                switch_scene(root,event);
            }
            else {wrong.setText("sorry,wrong password,try again");}


        }
        else
        { wrong.setText("user does not exist");}
    }

    public void cr_login(ActionEvent event) throws IOException {
        String u = usernameField.getText();
        String p = passwordField.getText();
        System.out.println(u);
        System.out.println(p);

        File infoFile2 = new File("src/main/resources/com/example/responsible_cr/files/cr/"+u+"/info.txt");
        if (infoFile2.exists()) {
            Scanner sc = new Scanner(infoFile2);
            sc.useDelimiter("\n");

            String n = sc.next();
            n = sc.next();


            System.out.println(n);

            if (n.equals(p)) {

                System.out.println("hello");
                root = FXMLLoader.load(HelloApplication.class.getResource("fxml/CR/dashboard_cr.fxml"));
                switch_scene(root,event);
            }
            else {wrong.setText("sorry,wrong password,try again");}
        }
        else
        { wrong.setText("user does not exist");}
    }




    public void switch_scene(Parent root, EventObject event) throws IOException
    {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Responsible CR");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    @FXML
      public void forgotpass_student(ActionEvent event) throws IOException
      {
          forgotpass fp = new forgotpass();
          fp.setAcc_type("student");

          root = FXMLLoader.load(HelloApplication.class.getResource("fxml/forgotpass.fxml"));
      switch_scene(root,event);
      }
    public void forgotpass_cr(ActionEvent event) throws IOException
    {
        forgotpass fp = new forgotpass();
        fp.setAcc_type("CR");

        root = FXMLLoader.load(HelloApplication.class.getResource("fxml/forgotpass.fxml"));
        switch_scene(root,event);
    }

    public void forgotpass_faculty(ActionEvent event) throws IOException
    {
        forgotpass fp = new forgotpass();
        fp.setAcc_type("faculty");

        root = FXMLLoader.load(HelloApplication.class.getResource("fxml/forgotpass.fxml"));
        switch_scene(root,event);
    }

}
