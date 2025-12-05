package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import escaperoom.App;
import com.model.GameSystemFacade;

public class LoginController implements Initializable {
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private Label lbl_error;

    GameSystemFacade escapeRoom = GameSystemFacade.getInstance();


    @FXML
    private void btnLoginClicked(MouseEvent event) throws IOException {
        lbl_error.setText("");  // clear old errors

        String username = txt_username.getText().trim();
        String password = txt_password.getText().trim();

        
        if (username.isEmpty() || password.isEmpty()) {
            lbl_error.setText("Please enter a username \n and password.");
            return;
        }

        boolean validLogin = escapeRoom.login(username, password);
        if (!validLogin) {
            lbl_error.setText("Invalid username or password.");
            return;
        }

        App.setRoot("difficulty");
    }

    @FXML
    private void onSignupClicked(ActionEvent event) throws IOException {
        App.setRoot("signup");
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void settings(MouseEvent event) throws IOException {
        App.setRoot("settings");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
}
