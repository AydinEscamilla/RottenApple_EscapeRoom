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

public class EndingController implements Initializable {

    private GameSystemFacade facade = GameSystemFacade.getInstance();

   

    @FXML
    private void home(MouseEvent event) throws IOException {
        App.setRoot("home");
    }

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }


    
}