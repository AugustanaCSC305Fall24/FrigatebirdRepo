package edu.augustana;

import edu.augustana.sound.SoundGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class AIScenarioAvalanche extends CWSenderController{


    @FXML
    private TextArea chatLog;

    @FXML
    private TextField messageSender;


    private StringBuilder morseCodeBuilder = new StringBuilder();
    private long lastInputTime = System.currentTimeMillis();

    private SoundGenerator SoundforMorse = new SoundGenerator();

    @FXML
    private void initialize() {
        messageSender.setOnKeyPressed(this::handleMorseInput);
        showAlert("Help!", "Help, there has been an Avalanche on a nearby mountain. We are stuck");
    }

    @FXML
    private void backToHomeAction() throws IOException {
        App.setRoot("HomePage");
    }
    @FXML
    private void onReceiveButtonClick() {
        sendRandomMorseMessage();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void handleMorseInput(KeyEvent evt) {
        long currentTime = System.currentTimeMillis();
        KeyCode input = evt.getCode();
        String morseSymbol = "";
        if (currentTime - lastInputTime > 1000) {
            morseCodeBuilder.append(" ");
            chatLog.appendText(" ");
        }

        if (input.equals(KeyCode.DIGIT1)) {
            morseSymbol = ".";
            morseCodeBuilder.append(morseSymbol);
            chatLog.appendText(morseSymbol);
            SoundforMorse.playTone(600,100,0);
        } else if (input.equals(KeyCode.DIGIT2)) {
            morseSymbol = "-";
            morseCodeBuilder.append(morseSymbol);
            chatLog.appendText(morseSymbol);
            SoundforMorse.playTone(600,300,0);
        } else {
            System.out.println("Invalid input: Only '1' for dot and '2' for dash are allowed.");
        }
        lastInputTime = currentTime;

        messageSender.clear();
    }

    public void translateMorseInput() {

        String morseText = morseCodeBuilder.toString().trim();


        if (morseText.isEmpty()) {
            chatLog.appendText("\n[Error] No Morse code to translate.\n");
            return;
        }

        Morse morseConverter = new Morse();
        String englishMessage = morseConverter.toEnglish(morseText);


        if (englishMessage == null || englishMessage.isEmpty()) {
            chatLog.appendText("\n[Error] Invalid or untranslatable Morse code.\n");
        } else {

            chatLog.appendText("\nTranslated Message: " + englishMessage + "\n");
        }

        morseCodeBuilder.setLength(0);
    }
        public void sendRandomMorseMessage() {

        ArrayList<String> messages = new ArrayList<>(Arrays.asList("water","stuck","Helicopter"));
        Random random = new Random();


            int randomIndex = random.nextInt(messages.size());
            String selectedMessage = messages.get(randomIndex);


        // Display the message in English in chatLog
        chatLog.appendText("User: " + selectedMessage + "\n");

        // Convert the selected message to Morse code
        Morse morseConverter = new Morse();
        String morseTranslation = morseConverter.toMorse(selectedMessage);

        // Display Morse code in chatLog
        chatLog.appendText("Morse: " + morseTranslation + "\n");

        // Play sound for each symbol in Morse code
        for (char symbol : morseTranslation.toCharArray()) {
            if (symbol == '.') {
                SoundforMorse.playTone(600, 100, 0); // Dot sound
            } else if (symbol == '-') {
                SoundforMorse.playTone(600, 300, 0); // Dash sound
            } else if (symbol == ' ') {
                try {
                    Thread.sleep(300); // Delay for space between words
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        chatLog.appendText("\n");
    }


}
