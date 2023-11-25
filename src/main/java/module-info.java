module com.example.responsible_cr {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.responsible_cr to javafx.fxml;
    exports com.example.responsible_cr;
    opens com.example.responsible_cr.CR to javafx.fxml;
    exports com.example.responsible_cr.CR;
    opens com.example.responsible_cr.student to javafx.fxml;
    exports com.example.responsible_cr.student;
    opens com.example.responsible_cr.faculty to javafx.fxml;
    exports com.example.responsible_cr.faculty;
}