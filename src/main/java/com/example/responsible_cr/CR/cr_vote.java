package com.example.responsible_cr.CR;

import com.example.responsible_cr.vote;
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

public class cr_vote implements Initializable {


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
    private TextField tno;

    @FXML
    private TextField toption;

    @FXML
    private TextField ttopic;

    @FXML
    private Label topic;


    ObservableList<vote> view = FXCollections.observableArrayList();



    @FXML
    void add_option(ActionEvent event) throws IOException {

        if(tno.getText().isEmpty() || toption.getText().isEmpty())
        {
            error_text.setText("Please fill up all the fields");
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2000), ae -> error_text.setText("\0")));
            timeline.play();
        }

        else {
            String no = tno.getText();
            String option = toption.getText();

            vote v = new vote(no, option,0);
            view.add(v);

            tno.setText("");
            toption.setText("");

            File userFile1 = new File("src/main/resources/com/example/responsible_cr/files/vote/" + no);
            boolean is_user_file_created1 = userFile1.mkdir();

            File infoFile = new File("src/main/resources/com/example/responsible_cr/files/vote/" + no + "/info.txt");
            FileWriter writer = new FileWriter(infoFile);
            writer.write(no + "\n");
            writer.write( option + "\n");
            writer.write(0 + "\n");
            writer.close();
        }

    }

    @FXML
    void set_topic(ActionEvent event) throws IOException {
        String topic1 = ttopic.getText();

        ttopic.setText("");
        topic.setText(topic1);

        File infoFile = new File("src/main/resources/com/example/responsible_cr/files/vote/vote_topic.txt");
        FileWriter writer = new FileWriter(infoFile);
        writer.write(topic1 + "\n");
        writer.close();
    }

    @FXML
    void delete_option(ActionEvent event) {
        vote selectedvote = vote_view.getSelectionModel().getSelectedItem();
        if (selectedvote != null) {
            String no = selectedvote.getNo();

            // Remove the vote from the TableView
            view.remove(selectedvote);

            // Delete the directory and files associated with the routine
            deleteVoteFiles(no);
        } else {
            // Show an error message if no vote is selected
            error_text.setText("Please select a vote to delete.");
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2000), ae -> error_text.setText("\0")));
            timeline.play();
        }
    }

    private void deleteVoteFiles(String no) {
        String directoryPath = "src/main/resources/com/example/responsible_cr/files/vote/" + no;
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            // Delete the vote directory and its contents
            deleteDirectory(directory);
        }
    }

    private void deleteDirectory(File directory) {
        File[] allContents = directory.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directory.delete();
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