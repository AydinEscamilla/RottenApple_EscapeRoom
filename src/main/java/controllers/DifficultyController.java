package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import escaperoom.App;
import com.model.GameSystemFacade;

public class DifficultyController implements Initializable {



    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void easy(MouseEvent event) throws IOException {
        App.setRoot("user_home");
    }

    @FXML
    private void medium(MouseEvent event) throws IOException {
        App.setRoot("user_home");
    }

    @FXML
    private void hard(MouseEvent event) throws IOException {
        App.setRoot("user_home");
    }

    @FXML
    private void settings(MouseEvent event) throws IOException {
        App.setRoot("settings");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}
