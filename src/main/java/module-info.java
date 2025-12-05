module com.model {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires javafx.graphics;
    requires java.xml;
    requires java.desktop;

    //  wherever we have Java files we open them to javafx
    opens escaperoom to javafx.fxml;
    
    exports escaperoom;

    opens controllers to javafx.fxml;

    exports controllers;

    opens com.model to javafx.fxml;
    
    exports com.model;
}
