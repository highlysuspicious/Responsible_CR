package com.example.responsible_cr.student;

import com.example.responsible_cr.vote;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class student_vote implements Initializable {


    @FXML
    private Label error_text;

    @FXML
    private TableView<vote> vote_view;

    @FXML
    private TableColumn<vote, String> no;

    @FXML
    private TableColumn<vote, String> option;
    @FXML
    private TableColumn<vote, Integer> count;


    @FXML
    private Label topic;


    ObservableList<vote> view = FXCollections.observableArrayList();

    static String user_name;

    public void set_user_name(String name){
        user_name = name;
    }

    @FXML
    void select_vote(ActionEvent event) throws IOException {

        vote selectedvote = vote_view.getSelectionModel().getSelectedItem();
        if (selectedvote != null) {
            String no = selectedvote.getNo();

            getvoteFiles(no);

        } else {
            // Show an error message if no vote is selected
            error_text.setText("Please select a vote.");
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2000), ae -> error_text.setText("\0")));
            timeline.play();
        }
    }

    private void getvoteFiles(String no) throws IOException {

        String directoryPath0 = "src/main/resources/com/example/responsible_cr/files/vote/" + no + "/" + user_name;
        File directory0 = new File(directoryPath0);

        System.out.println(user_name);
        System.out.println(directory0.exists());

        if(!directory0.exists()) {

            String directoryPath = "src/main/resources/com/example/responsible_cr/files/vote/" + no + "/info.txt";
            File directory = new File(directoryPath);

            Scanner sc = new Scanner(directory);
            sc.useDelimiter("\n");

            String n1 = sc.next();
            String n2 = sc.next();
            Integer n3 = Integer.valueOf(sc.next());


            System.out.println(n3);

            FileWriter writer = new FileWriter(directory);
            writer.write(n1 + "\n");
            writer.write(n2 + "\n");
            writer.write(n3 + 1 + "\n");
            writer.close();

            File userFile1 = new File("src/main/resources/com/example/responsible_cr/files/vote/" + no + "/" + user_name);
            boolean is_user_file_created1 = userFile1.mkdir();

            error_text.setText("Voted successfully");

        }
        else{
            error_text.setText("You have already voted");
        }
    }


    vote v = new vote();
    public void view_history() throws FileNotFoundException {

        String directoryPath = "src/main/resources/com/example/responsible_cr/files/vote/";
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

                        view.add(v);
                    }
                }
            }
        }
    }


    void get_items(String c) throws FileNotFoundException {

        File infoFile2 = new File("src/main/resources/com/example/responsible_cr/files/vote/" + c + "/info.txt");
        if (infoFile2.exists()) {
            Scanner sc = new Scanner(infoFile2);

            sc.useDelimiter("\n");

            String n = sc.next();
            String op = sc.next();
            Integer i = Integer.valueOf(sc.next());


            v = new vote(n,op,i);
            sc.close();
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        no.setCellValueFactory(new PropertyValueFactory<vote,String>("no"));
        option.setCellValueFactory(new PropertyValueFactory<vote,String>("option"));
        count.setCellValueFactory(new PropertyValueFactory<vote,Integer>("count"));


        try {
            view_history();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        vote_view.setItems(view);


        File infoFile = new File("src/main/resources/com/example/responsible_cr/files/vote/vote_topic.txt");
        if (infoFile.exists()) {
            Scanner sc = null;
            try {
                sc = new Scanner(infoFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            sc.useDelimiter("\n");
            String n = sc.next();
            topic.setText(n);
        }
    }
}