module com.example.gesecole {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.gesecole to javafx.fxml;
    exports com.example.gesecole;
}