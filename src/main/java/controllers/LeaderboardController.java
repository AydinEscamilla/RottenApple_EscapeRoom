package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import com.model.GameSystemFacade;
import com.model.User;
import escaperoom.App;

public class LeaderboardController implements Initializable {
    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void settings(MouseEvent event) throws IOException {
        App.setRoot("settings");
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
