package com.example.responsible_cr.student;

import com.example.responsible_cr.routine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class student_routine implements Initializable {


    @FXML
    private TableView<routine> routine_view;

    @FXML
    private TableColumn<routine, String> no;
    @FXML
    private TableColumn<routine, String> date;

    @FXML
    private TableColumn<routine, String> day;

    @FXML
    private TableColumn<routine, String> p1;

    @FXML
    private TableColumn<routine, String> p2;

    @FXML
    private TableColumn<routine, String> p3;

    @FXML
    private TableColumn<routine, String> p4;

    @FXML
    private TableColumn<routine, String> p5;

    ObservableList<routine> view = FXCollections.observableArrayList();


    routine rout = new routine();
    public void view_history() throws FileNotFoundException {

        String directoryPath = "src/main/resources/com/example/responsible_cr/files/routine/";
        File directory = new File(directoryPath);

        // Check if the directory exists
        if (directory.exists() && directory.isDirectory()) {
            // List the files and subdirectories in the directory
            File[] filesAndDirs = directory.listFiles();

            if (filesAndDirs != null) {
                for (File fileOrDir : filesAndDirs) {
                    if (fileOrDir.isDirectory()) {
                        String c1= fileOrDir.getName();

                        get_items(c1);

                        view.add(rout);
                    }
                }
            }
        }
    }


    void get_items(String c) throws FileNotFoundException {

        File infoFile2 = new File("src/main/resources/com/example/responsible_cr/files/routine/" + c + "/info.txt");
        if (infoFile2.exists()) {
            Scanner sc = new Scanner(infoFile2);

            sc.useDelimiter("\n");

            String n = sc.next();
            String dt = sc.next();
            String da = sc.next();
            String p1 = sc.next();
            String p2 = sc.next();
            String p3 = sc.next();
            String p4 = sc.next();
            String p5 = sc.next();

            rout = new routine(n,dt,da,p1,p2,p3,p4,p5);
            sc.close();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        no.setCellValueFactory(new PropertyValueFactory<routine,String>("no"));
        date.setCellValueFactory(new PropertyValueFactory<routine,String>("date"));
        day.setCellValueFactory(new PropertyValueFactory<routine,String>("day"));
        p1.setCellValueFactory(new PropertyValueFactory<routine,String>("p1"));
        p2.setCellValueFactory(new PropertyValueFactory<routine,String>("p2"));
        p3.setCellValueFactory(new PropertyValueFactory<routine,String>("p3"));
        p4.setCellValueFactory(new PropertyValueFactory<routine,String>("p4"));
        p5.setCellValueFactory(new PropertyValueFactory<routine,String>("p5"));

        try {
            view_history();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        routine_view.setItems(view);
    }
}