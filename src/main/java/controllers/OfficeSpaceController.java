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
import javafx.scene.layout.StackPane;
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
    private Button desk_button;
    @FXML
    private Button correct_button;
     @FXML
    private Button incorrect_button_1;
     @FXML
    private Button incorrect_button_2;
   @FXML
    private Button  officer_mason_key_button;
    @FXML
    private Pane inventory_space_2;
    @FXML
    private ImageView key_inventory_space_1;
     @FXML
    private ImageView key_inventory_space_2;
    @FXML
    private Button next_room;
    @FXML
    private AnchorPane puzzleOverlay;
    @FXML
    private AnchorPane pauseOverlay;

    @FXML
    private void itemUnlocksPuzzle(ActionEvent event) {
        // Logic for using the item to unlock a puzzle can be added here
        Object source = event.getSource();
        if (source == officer_mason_key_button) {
            // Logic for using Officer Mason's Key to unlock a puzzle
            dialog_text.setText("You used Officer Mason's Key to unlock a drawer in the desk. ");
            // Hide the key from inventory after use
            key_inventory_space_2.setVisible(false);
            // Enable the desk button to allow puzzle interaction
            desk_button.setVisible(true);
            desk_button.setDisable(false);
        }

        System.out.println("Used Officer Mason's Key to unlock a puzzle!");
    }
     

     @FXML
    private void handlePuzzle(ActionEvent event) {
        puzzleOverlay.setVisible(true);
        puzzleOverlay.setManaged(true);
    }

    @FXML
    private void handleHint() {
        dialog_text.setText("Use Officer Mason's Key (blue) to unlock the desk drawer.");
    }

    @FXML
    private void completePuzzle(ActionEvent event) {
        Object source = event.getSource();

        if (source == correct_button) {
            // Correct answer
            dialog_text.setText("There's nothing here that can stand as evidence against Officer Mason, but you find something else; a personal memo to go back to the interrogation room to grab something. What did he leave behind?");

           

            // Disable cabinet button after puzzle is completed
            desk_button.setDisable(true);

            // Show next room button
            next_room.setVisible(true);

            // Hide puzzle overlay
            puzzleOverlay.setVisible(false);
            puzzleOverlay.setManaged(false);

        } else if (source == incorrect_button_1 || source == incorrect_button_2) {
            // Wrong answer so we keep overlay up
            dialog_text.setText("That's not quite right. Try again!");
            
        }
       
    }

    @FXML
    private void nextRoom(MouseEvent event) throws IOException {
        App.setRoot("interrogationroom");
    }

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
