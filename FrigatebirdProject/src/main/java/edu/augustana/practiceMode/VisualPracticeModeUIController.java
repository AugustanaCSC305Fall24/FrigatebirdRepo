package edu.augustana.practiceMode;

import edu.augustana.App;
import edu.augustana.Morse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class VisualPracticeModeUIController extends Morse {

    @FXML
    private Button backButton;

    @FXML
    private Button refreshButton;
    @FXML
    public TextField letterDisplay;

    @FXML
    public TextField morseInput;

    public char currentLetter;

    private VisualMorsePractice visualMorsePractice;

    @FXML
    public void backToPractiseModeAction(ActionEvent event) throws IOException {
        App.setRoot("PractiseOption");
    }
    // Method to display a random letter and check Morse code input
    @FXML
    public void displayLetterAndCheckMorse() {
        currentLetter = visualMorsePractice.getRandomLetter();
        letterDisplay.setText(String.valueOf(currentLetter));

        // Clear previous input
        morseInput.clear();
    }


    @FXML
    public void checkMorseCode() {
        String userInput = morseInput.getText().trim();

        // Check if the input matches the Morse code
        if (visualMorsePractice.isMorseCodeCorrect(currentLetter, userInput)) {
            showAlert("Correct!", "You entered the correct Morse code.");
        } else {
            String correctMorse = visualMorsePractice.getMorseCode(currentLetter);
            showAlert("Incorrect", "The correct Morse code for " + currentLetter + " is " + correctMorse + ".");
        }
    }


//    private char getRandomLetterFromMorseMap() {
//        List<Character> letters = new ArrayList<>(morseMap.keySet());
//        Random random = new Random();
//        return letters.get(random.nextInt(letters.size()));
//    }

    // Utility method to show alert messages
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void initialize(){
        visualMorsePractice = new VisualMorsePractice(Morse.getMorseMap());
        displayLetterAndCheckMorse();
    }
}
