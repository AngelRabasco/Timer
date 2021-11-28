module org.Timer {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.Timer to javafx.fxml;
    exports org.Timer;
}
