module com.example.projektver120523 {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
    requires java.sql;
    requires java.desktop;

    opens com.example.projektver to javafx.fxml;
    exports com.example.projektver;
}