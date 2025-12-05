package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import escaperoom.App;
import com.model.GameSystemFacade;

public class UserHomeController implements Initializable {
    @FXML
    private Label lbl_welcome;

    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void settings(MouseEvent event) throws IOException {
        App.setRoot("settings");
    }

    @FXML
    private void newGame(MouseEvent event) throws IOException {
        App.setRoot("evidenceroom");
    }



    @FXML
    private void leaderboard(MouseEvent event) throws IOException {
        App.setRoot("leaderboard"); 
    }

     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}