package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import escaperoom.App;
import com.model.GameSystemFacade;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class EvidenceRoomController implements Initializable {

    

    @FXML
    private Pane dialog_pane;
    @FXML
    private Text dialog_text;
    @FXML
    private Pane inventory_space_1;
    @FXML
    private Button hat_button;
    @FXML
    private AnchorPane puzzleOverlay;
    @FXML
    private Button key_button;
    @FXML
    private ImageView key_inventory_space_1;
    @FXML
    private Button next_room;
    @FXML
    private AnchorPane pauseOverlay;

    
    
    @FXML
    private void handlePuzzle(ActionEvent event) {
        puzzleOverlay.setVisible(true);
        puzzleOverlay.setManaged(true);
    }

    @FXML
    private void completePuzzle(ActionEvent event) {
        puzzleOverlay.setVisible(false);
        puzzleOverlay.setManaged(false);
        // Add key to inventory
        key_inventory_space_1.setVisible(true);
        // Disable hat button after puzzle is completed
        hat_button.setDisable(true);
        dialog_text.setText("Nice, you got the key! It looks off though, more like another key you know... wait, it's the holding cell key! Maybe his key is by the holding cell!");
        // Make next room button visible
        next_room.setVisible(true);
    }

    @FXML
    private void nextRoom(MouseEvent event) throws IOException {
        App.setRoot("shortholdingroom");
    }


    @FXML
    private void handleHint() {
        hat_button.setStyle(
            "-fx-background-color: clear; " +
            "-fx-border-color: red; " +
            "-fx-border-width: 2; " +
            "-fx-border-radius: 5;"
        );
    }

    //  Universal Game Controls

    @FXML
    private void home(MouseEvent event) throws IOException {
        App.setRoot("home");
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
