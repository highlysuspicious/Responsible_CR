package com.example.responsible_cr.CR;

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

public class cr_exam implements Initializable {


    @FXML
    private Button btn;

    @FXML
    private Button btn1;

    @FXML
    private Label error_text;

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


    @FXML
    private TextField tno;

    @FXML
    private TextField ttime;
    @FXML
    private DatePicker tdate;

    @FXML
    private TextField tsubject;

    @FXML
    private TextField tsyllabus;



    ObservableList<exam> view = FXCollections.observableArrayList();



    @FXML
    void add_exam(ActionEvent event) throws IOException {

        if(tno.getText().isEmpty() || tdate.getValue()==null || ttime.getText().isEmpty() || tsubject.getText().isEmpty() || tsyllabus.getText().isEmpty())
        {
            error_text.setText("Please fill up all the fields");
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2000), ae -> error_text.setText("\0")));
            timeline.play();
        }

        else {
            String no = tno.getText();
            String date = String.valueOf(tdate.getValue());
            String time = ttime.getText();
            String subject = tsubject.getText();
            String syllabus = tsyllabus.getText();

            exam r = new exam(no,date, time, subject, syllabus);
            view.add(r);

            tno.setText("");
            tdate.setValue(null);
            ttime.setText("");
            tsubject.setText("");
            tsyllabus.setText("");

            File userFile1 = new File("src/main/resources/com/example/responsible_cr/files/exam/" + no);
            boolean is_user_file_created1 = userFile1.mkdir();

            File infoFile = new File("src/main/resources/com/example/responsible_cr/files/exam/" + no + "/info.txt");
            FileWriter writer = new FileWriter(infoFile);
            writer.write(no + "\n");
            writer.write(date + "\n");
            writer.write(time + "\n");
            writer.write(subject + "\n");
            writer.write(syllabus + "\n");
            writer.close();
        }

    }

    @FXML
    void delete_exam(ActionEvent event) {
        exam selectedExam = exam_view.getSelectionModel().getSelectedItem();
        if (selectedExam != null) {
            String no = selectedExam.getNo();

            // Remove the routine from the TableView
            view.remove(selectedExam);

            // Delete the directory and files associated with the routine
            deleteExamFiles(no);
        } else {
            // Show an error message if no exam is selected
            error_text.setText("Please select a exam slot to delete.");
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2000), ae -> error_text.setText("\0")));
            timeline.play();
        }
    }

    private void deleteExamFiles(String no) {
        String directoryPath = "src/main/resources/com/example/responsible_cr/files/exam/" + no;
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            // Delete the routine directory and its contents
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