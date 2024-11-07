package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VisualPracticeModeUIController extends Morse{

    @FXML
    private Button backButton;

    @FXML
    private Button refreshButton; //used for refreshing the symbols

    @FXML
    private TextField letterDisplay;

    @FXML
    private TextField morseInput;

    private char currentLetter;

    @FXML
    public void backToHomeAction(ActionEvent event) throws IOException {
        App.setRoot("HomePage");
    }
    // Method to display a random letter and check Morse code input
    @FXML
    public void displayLetterAndCheckMorse() {
        // Randomly select a letter using morseMap keys
        currentLetter = getRandomLetterFromMorseMap();
        letterDisplay.setText(String.valueOf(currentLetter));  // Display letter in UI

        // Clear previous input
        morseInput.clear();
    }

    // Method called when user submits Morse code input
    @FXML
    public void checkMorseCode() {
        String userInput = morseInput.getText().trim();
        String correctMorse = morseMap.get(currentLetter);

        // Check if the input matches the Morse code for the displayed letter
        if (userInput.equals(correctMorse)) {
            showAlert("Correct!", "You entered the correct Morse code.");
        } else {
            showAlert("Incorrect", "The correct Morse code for " + currentLetter + " is " + correctMorse + ".");
        }
    }

    // Utility method to get a random letter from morseMap keys
    private char getRandomLetterFromMorseMap() {
        List<Character> letters = new ArrayList<>(morseMap.keySet());
        Random random = new Random();
        return letters.get(random.nextInt(letters.size()));
    }

    // Utility method to show alert messages
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void initialize(){
        displayLetterAndCheckMorse();
    }
}
