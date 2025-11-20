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



public class SignupController implements Initializable {
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private Label lbl_error;

    GameSystemFacade escapeRoom = GameSystemFacade.getInstance();


    @FXML
    private void btnSignupClicked(MouseEvent event) throws IOException {
        lbl_error.setText("");  // clear old errors

        String username = txt_username.getText().trim();
        String password = txt_password.getText().trim();
       

        
        // check for empty fields
        if (username.equals("") || password.equals("") ) 
        {
            lbl_error.setText("Username and Password are required.");
            return;
        }

        User newUser = escapeRoom.signup(username, password);

        
        if (newUser == null) {
            lbl_error.setText("Username is already taken. Try another");
            return;
        }

        escapeRoom.login(username, password);
        User user = escapeRoom.getCurrentUser();

        //  escapeRoom current user is set at
        App.setRoot("user_home");
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("home");
    }

       @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}