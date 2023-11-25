package com.example.responsible_cr.faculty;

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

public class faculty_routine implements Initializable {

    @FXML
    private Button btn;

    @FXML
    private Button btn1;

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

    @FXML
    private TextField tno;

    @FXML
    private DatePicker tdate;

    @FXML
    private TextField tday;

    @FXML
    private TextField tp1;

    @FXML
    private TextField tp2;

    @FXML
    private TextField tp3;

    @FXML
    private TextField tp4;

    @FXML
    private TextField tp5;

    @FXML
    private Label error_text;



    @FXML
    void add_rut(ActionEvent event) throws IOException {

        if(tno.getText().isBlank() || tday.getText().isBlank() || tdate.getValue()==null || tp1.getText().isBlank() || tp2.getText().isBlank() || tp3.getText().isBlank() || tp4.getText().isBlank() || tp5.getText().isBlank())
        {
            error_text.setText("Field cannot be empty");
        }

        else {
            String no = tno.getText();
            String day = tday.getText();
            String date = String.valueOf(tdate.getValue());
            String p1 = tp1.getText();
            String p2 = tp2.getText();
            String p3 = tp3.getText();
            String p4 = tp4.getText();
            String p5 = tp5.getText();

            routine r = new routine(no,date, day, p1, p2, p3, p4, p5);
            view.add(r);

            tno.setText("");
            tday.setText("");
            tdate.setValue(null);
            tp1.setText("");
            tp2.setText("");
            tp3.setText("");
            tp4.setText("");
            tp5.setText("");

            File userFile1 = new File("src/main/resources/com/example/responsible_cr/files/routine/" + no);
            boolean is_user_file_created1 = userFile1.mkdir();

            File infoFile = new File("src/main/resources/com/example/responsible_cr/files/routine/" + no + "/info.txt");
            FileWriter writer = new FileWriter(infoFile);
            writer.write(no + "\n");
            writer.write(date + "\n");
            writer.write(day + "\n");
            writer.write(p1 + "\n");
            writer.write(p2 + "\n");
            writer.write(p3 + "\n");
            writer.write(p4 + "\n");
            writer.write(p5 + "\n");
            writer.close();
        }

    }

    @FXML
    void delete_rut(ActionEvent event) {
        routine selectedRoutine = routine_view.getSelectionModel().getSelectedItem();
        if (selectedRoutine != null) {
            String no = selectedRoutine.getNo();

            // Remove the routine from the TableView
            view.remove(selectedRoutine);

            // Delete the directory and files associated with the routine
            deleteRoutineFiles(no);
        } else {
            // Show an error message if no routine is selected
            error_text.setText("Please select a routine to delete.");
        }
    }

    private void deleteRoutineFiles(String no) {
        String directoryPath = "src/main/resources/com/example/responsible_cr/files/routine/" + no;
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