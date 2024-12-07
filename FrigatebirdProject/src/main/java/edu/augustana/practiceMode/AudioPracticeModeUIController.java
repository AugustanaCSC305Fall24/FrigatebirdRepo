package edu.augustana.practiceMode;

import edu.augustana.App;
import edu.augustana.Morse;
import edu.augustana.sound.SoundGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.control.TextField;

public class AudioPracticeModeUIController extends Morse {

    private SoundGenerator randomMorseSound = new SoundGenerator();
    private Character lastGeneratedLetter;

    @FXML
    private TextField userInputField;

    @FXML
    public void backToPracticeModeAction(ActionEvent event) throws IOException {
        App.setRoot("PractiseOption");
    }

    @FXML
    void checkMessage(ActionEvent event) {
        String userInput = userInputField.getText().trim().toUpperCase();

        // Check if the user input matches the last generated letter
        if (lastGeneratedLetter != null && userInput.equals(lastGeneratedLetter.toString())) {
            showAlert("Correct!", "You entered the correct translation for " + lastGeneratedLetter);
        } else {
            showAlert("Incorrect", "The correct translation was " + lastGeneratedLetter);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void playSound(ActionEvent event) {
        char randomMorseCode = getRandomLetterFromMorseMap();
        lastGeneratedLetter = randomMorseCode;
        String morseCode = morseMap.get(randomMorseCode);
        if (morseCode != null) {
            double defaultDeviation = 0.0;
            randomMorseSound.playMorseSymbol(morseCode, defaultDeviation);
            double unitLengthMillis = randomMorseSound.getUnitLength()*1000;
        }
        System.out.println("playSound button clicked!");
        System.out.println("Generated letter: " + lastGeneratedLetter);
    }

    private char getRandomLetterFromMorseMap() {
        List<Character> letters = new ArrayList<>(morseMap.keySet());
        Random random = new Random();
        return letters.get(random.nextInt(letters.size()));
    }

    @FXML
    void initialize() {
        if (userInputField == null) {
            System.out.println("userInputField is null. Check FXML binding.");
        } else {
            System.out.println("userInputField is properly initialized.");
        }

    }
}
