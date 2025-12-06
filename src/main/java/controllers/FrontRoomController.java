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
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import escaperoom.App;

public class FrontRoomController implements Initializable {

    @FXML
    private Text dialog_text_intro;

    @FXML
    private Text dialog_text_outro;

    @FXML
    private Button cabinet_button;

    @FXML
    private Button correct_button;

    @FXML
    private Button incorrect_button_1;

    @FXML
    private Button incorrect_button_2;

    @FXML
    private Button next_room;

    @FXML
    private Pane inventory_space_2; 

    @FXML
    private ImageView key_inventory_space_2;

    @FXML
    private AnchorPane puzzleOverlay;

    @FXML
    private AnchorPane pauseOverlay;

    @FXML
    private ImageView puzzle_image;

    @FXML
    private void handlePuzzle(ActionEvent event) {
        puzzleOverlay.setVisible(true);
        puzzleOverlay.setManaged(true);
    }

    @FXML
    private void handleHint() {
        cabinet_button.setStyle(
            "-fx-background-color: clear; " +
            "-fx-border-color: red; " +
            "-fx-border-width: 2; " +
            "-fx-border-radius: 5;"
        );
    }

    @FXML
    private void completePuzzle(ActionEvent event) {
        Object source = event.getSource();

        if (source == correct_button) {
            dialog_text_intro.setVisible(false);
            dialog_text_outro.setVisible(true);

            if (key_inventory_space_2 != null) {
                key_inventory_space_2.setVisible(true);
            }

            cabinet_button.setDisable(true);

            next_room.setVisible(true);

            puzzleOverlay.setVisible(false);
            puzzleOverlay.setManaged(false);

        } else if (source == incorrect_button_1 || source == incorrect_button_2) {
            dialog_text_intro.setText("That's not quite right. Try again!");
        }
    }

    @FXML
    private void nextRoom(MouseEvent event) throws IOException {
        App.setRoot("ending");
    }

    @FXML
    private void home(MouseEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void settings(MouseEvent event) throws IOException {
        App.setRoot("settings");
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
    private void handleSaveQuit() throws IOException {
        App.setRoot("home");
    }
    // This method runs to set the initial state of the screen
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 
        if (puzzleOverlay != null) {
            puzzleOverlay.setVisible(false);
            puzzleOverlay.setManaged(false);
        }

        // Make sure pause overlay is hidden 
        if (pauseOverlay != null) {
            pauseOverlay.setVisible(false);
            pauseOverlay.setManaged(false);
        }

        // Start with intro text visible 
        if (dialog_text_intro != null) {
            dialog_text_intro.setVisible(true);
        }
        // and outro text hidden
        if (dialog_text_outro != null) {
            dialog_text_outro.setVisible(false);
        }
        
        // At the beginning the key in inventory slot 2 is not visible
        if (key_inventory_space_2 != null) {
            key_inventory_space_2.setVisible(false);
        }
        
        // The next room button is hidden until the puzzle is solved
        if (next_room != null) {
            next_room.setVisible(false);
        }
    }
}
