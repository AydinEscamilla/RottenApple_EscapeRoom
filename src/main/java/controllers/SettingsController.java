package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import escaperoom.App;

public class SettingsController implements Initializable {
    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("home");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}
