package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.border.Border;

import org.w3c.dom.Text;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import escaperoom.App;
import com.model.GameSystemFacade;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;



public class OfficeSpaceController implements Initializable {

   

     @FXML
    private Pane dialog_pane;
    @FXML
    private Text dialog_text;
   
    @FXML
    private AnchorPane pauseOverlay;

     

    @FXML
    private void home(MouseEvent event) throws IOException {
        App.setRoot("home");
    }

     @FXML
    private void handleHint() {
        setPaused(true);
    }

    @FXML
    private void handlePause() {
        setPaused(true);
    }

    @FXML
    private void handleResume() {
        setPaused(false);
    }

    private void setPaused(boolean paused) {
        pauseOverlay.setVisible(paused);
        pauseOverlay.setManaged(paused);
    }

     @FXML
    private void handleSaveQuit() throws IOException{
        App.setRoot("home");
    }

   
    @FXML
    private void settings(MouseEvent event) throws IOException {
        App.setRoot("settings");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        pauseOverlay.setVisible(false);
        pauseOverlay.setManaged(false);
        // TODO Auto-generated method stub
    }
    
}
