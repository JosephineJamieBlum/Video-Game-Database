module com.mycompany.assignment1 {
    requires javafx.controls;
    requires javafx.fxml;
requires com.google.gson;
    requires java.sql;
    opens com.mycompany.assignment1 to javafx.fxml, com.google.gson;
    exports com.mycompany.assignment1;
}

