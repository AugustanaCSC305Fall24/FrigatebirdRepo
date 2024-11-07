package edu.augustana;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.io.IOException;

public class AIScenarioAvalanche extends CWSenderController{

    @FXML
    private TextArea chatLog;

    @FXML
    private TextField messageSender;


    private StringBuilder morseCodeBuilder = new StringBuilder(); // To accumulate Morse code
    private long lastInputTime = System.currentTimeMillis();
    @FXML
    private void initialize() {
        messageSender.setOnKeyPressed(this::handleMorseInput);  // Use handleMorseInput for real-time Morse input
        showAlert("Help!", "Help, there has been an Avalanche on a nearby mountain. We are stuck");
    }

    @FXML
    private void backToHomeAction() throws IOException {
        App.setRoot("HomePage");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @Override
    public void handleMorseInput(KeyEvent evt) {
        long currentTime = System.currentTimeMillis();
        KeyCode input = evt.getCode();
        String morseSymbol = "";
        if (currentTime - lastInputTime > 1000) {
            morseCodeBuilder.append(" ");
            chatLog.appendText(" "); // Add a space to chatLog for visual feedback
        }

        if (input.equals(KeyCode.DIGIT1)) {
            morseSymbol = ".";
            morseCodeBuilder.append(morseSymbol);   // Append to the accumulated Morse code
            chatLog.appendText(morseSymbol);        // Display in real-time in chatLog
        } else if (input.equals(KeyCode.DIGIT2)) {
            morseSymbol = "-";
            morseCodeBuilder.append(morseSymbol);   // Append to the accumulated Morse code
            chatLog.appendText(morseSymbol);        // Display in real-time in chatLog
        } else {
            System.out.println("Invalid input: Only '1' for dot and '2' for dash are allowed.");
        }
        lastInputTime = currentTime;
        // Clear input field for the next character
        messageSender.clear();
    }

    public void translateMorseInput() {
        // Convert the accumulated Morse code to English
        String morseText = morseCodeBuilder.toString();
        Morse morseConverter = new Morse();
        String englishMessage = morseConverter.toEnglish(morseText);

        // Display the translated English message in the chatLog
        chatLog.appendText("\n " + englishMessage + "\n");

        // Clear the Morse code builder for new input
        morseCodeBuilder.setLength(0);
    }

}
