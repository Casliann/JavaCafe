module cafeboard.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens cafeboard.client to javafx.fxml;
    opens cafeboard.entities to com.fasterxml.jackson.databind;

    exports cafeboard.client to javafx.graphics;
}
