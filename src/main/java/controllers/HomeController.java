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

public class HomeController implements Initializable {

    private GameSystemFacade facade = GameSystemFacade.getInstance();

    @FXML
    private void handleGuest(ActionEvent event) throws IOException {
        // Tell the system we're in guest mode
        facade.setGuest(true);  

        facade.logout();

        // Go to difficulty selection screen
        App.setRoot("difficulty");   
    }

    @FXML
    private void onLoginClicked(ActionEvent event) throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void onSignupClicked(ActionEvent event) throws IOException {
        App.setRoot("signup");
    }

    @FXML
    private void settings(MouseEvent event) throws IOException {
        App.setRoot("settings");
    }

    @FXML
    private void guest(MouseEvent event) throws IOException {
        App.setRoot("difficulty");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }


    
}
