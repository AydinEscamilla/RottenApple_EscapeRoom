package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import escaperoom.App;
import com.model.GameSystemFacade;

public class DifficultyController implements Initializable {

    @FXML
    private StackPane guestWarningBar;

    private GameSystemFacade facade = GameSystemFacade.getInstance();


    @FXML
    private void onSignupClicked(ActionEvent event) throws IOException {
        facade.setGuest(false);
        App.setRoot("signup");
    }

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
        // default: hidden
        guestWarningBar.setVisible(false);
        guestWarningBar.setManaged(false);

        // if current session is guest, show the warning
        if (facade.isGuest()) {
            guestWarningBar.setVisible(true);
            guestWarningBar.setManaged(true);
        }
        
    }
    
}
