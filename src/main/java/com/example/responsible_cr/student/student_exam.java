package com.example.responsible_cr.student;

import com.example.responsible_cr.exam;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class student_exam implements Initializable {



    @FXML
    private TableView<exam> exam_view;

    @FXML
    private TableColumn<exam,String> no;

    @FXML
    private TableColumn<exam,String> date;

    @FXML
    private TableColumn<exam,String> time;

    @FXML
    private TableColumn<exam,String> subject;

    @FXML
    private TableColumn<exam,String> syllabus;




    ObservableList<exam> view = FXCollections.observableArrayList();


    exam ex = new exam();
    public void view_history() throws FileNotFoundException {

        String directoryPath = "src/main/resources/com/example/responsible_cr/files/exam/";
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

                        view.add(ex);
                    }
                }
            }
        }
    }


    void get_items(String c) throws FileNotFoundException {

        File infoFile2 = new File("src/main/resources/com/example/responsible_cr/files/exam/" + c + "/info.txt");
        if (infoFile2.exists()) {
            Scanner sc = new Scanner(infoFile2);

            sc.useDelimiter("\n");

            String n = sc.next();
            String dt = sc.next();
            String dtt = sc.next();
            String sub = sc.next();
            String syl = sc.next();


            ex = new exam(n,dt,dtt,sub,syl);
            sc.close();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        no.setCellValueFactory(new PropertyValueFactory<exam,String>("no"));
        date.setCellValueFactory(new PropertyValueFactory<exam,String>("date"));
        time.setCellValueFactory(new PropertyValueFactory<exam,String>("time"));
        subject.setCellValueFactory(new PropertyValueFactory<exam,String>("subject"));
        syllabus.setCellValueFactory(new PropertyValueFactory<exam,String>("syllabus"));

        try {
            view_history();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        exam_view.setItems(view);
    }
}