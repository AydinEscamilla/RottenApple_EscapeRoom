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

    // --- Dialog / text ---

    // Intro text: shown when you first enter the room
    @FXML
    private Text dialog_text_intro;

    // Outro text: shown after the puzzle is completed
    @FXML
    private Text dialog_text_outro;

    // --- Main interactive elements ---

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

    // Inventory slot where the new key will appear
    @FXML
    private Pane inventory_space_2;  // not strictly required but kept for structure

    @FXML
    private ImageView key_inventory_space_2;

    // Overlays
    @FXML
    private AnchorPane puzzleOverlay;

    @FXML
    private AnchorPane pauseOverlay;

    // Optional: puzzle image placeholder from frontroom.fxml
    @FXML
    private ImageView puzzle_image;

    // --- Handlers ---

    @FXML
    private void handlePuzzle(ActionEvent event) {
        // Show the puzzle overlay
        puzzleOverlay.setVisible(true);
        puzzleOverlay.setManaged(true);
    }

    @FXML
    private void handleHint() {
        // Highlight the cabinet button to show where to click
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
            // Correct answer

            // Switch from intro text to outro text
            dialog_text_intro.setVisible(false);
            dialog_text_outro.setVisible(true);

            // Reveal key in inventory
            if (key_inventory_space_2 != null) {
                key_inventory_space_2.setVisible(true);
            }

            // Disable cabinet button so the puzzle can't be reopened
            cabinet_button.setDisable(true);

            // Show the "next room" button
            next_room.setVisible(true);

            // Hide the puzzle overlay
            puzzleOverlay.setVisible(false);
            puzzleOverlay.setManaged(false);

        } else if (source == incorrect_button_1 || source == incorrect_button_2) {
            // Wrong answer: keep the overlay up and update the intro text as feedback
            dialog_text_intro.setText("That's not quite right. Try again!");
        }
    }

    @FXML
    private void nextRoom(MouseEvent event) throws IOException {
        // Go to the next scene after the front room.
        // Adjust "shortholdingroom" to whatever your next FXML is named.
        App.setRoot("shortholdingroom");
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
        // You can hook this into your GameSystemFacade later if you want to save.
        App.setRoot("home");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Ensure overlays start hidden
        if (puzzleOverlay != null) {
            puzzleOverlay.setVisible(false);
            puzzleOverlay.setManaged(false);
        }

        if (pauseOverlay != null) {
            pauseOverlay.setVisible(false);
            pauseOverlay.setManaged(false);
        }

        // Text sequence: first show intro, hide outro
        if (dialog_text_intro != null) {
            dialog_text_intro.setVisible(true);
        }
        if (dialog_text_outro != null) {
            dialog_text_outro.setVisible(false);
        }

        // Inventory: don't show the new key until puzzle is solved
        if (key_inventory_space_2 != null) {
            key_inventory_space_2.setVisible(false);
        }

        // "Next room" button should be hidden until puzzle is solved
        if (next_room != null) {
            next_room.setVisible(false);
        }
    }
}
