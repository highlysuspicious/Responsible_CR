package com.example.responsible_cr.student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class student_notice implements Initializable {

    @FXML
    private ListView<String> list;
    public ObservableList<String> nam = FXCollections.observableArrayList();


    void call_factory ()
    {
        list.setCellFactory(param -> new ListCell<String>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item==null) {
                    setGraphic(null);
                    setText(null);
                    // other stuff to do...

                }else{

                    // set the width's
                    setMinWidth(param.getWidth());
                    setMaxWidth(560);
                    setPrefWidth(param.getWidth());

                    // allow wrapping
                    setWrapText(true);

                    setText(item.toString());


                }
            }
        });
    }

    public ContextMenu contextMenu = new ContextMenu();
    public MenuItem copyMenuItem = new MenuItem("Copy");
    void copy_text() {
        {
            String selectedText = list.getSelectionModel().getSelectedItem();
            if (selectedText != null) {
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(selectedText);
                clipboard.setContent(content);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            File file = new File("src/main/resources/com/example/responsible_cr/files/notice.txt");

            Scanner sc = new Scanner(file);
            sc.useDelimiter("\n");

            while(sc.hasNext())
            {
                String p = sc.next();

                nam.add(p);
                call_factory();
                list.setItems(nam);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        copyMenuItem.setOnAction(event -> copy_text());
        contextMenu.getItems().add(copyMenuItem);
        list.setContextMenu(contextMenu);

    }
}