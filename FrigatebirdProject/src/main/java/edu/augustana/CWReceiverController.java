package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CWReceiverController {
    @FXML
    private TextField messageInput;
    @FXML
    private TextField frequencyInput;
    @FXML
    private TextArea morsecodeMessage;

    private Sound soundPlayer = new Sound();  // Sound instance

    @FXML
    public void onTransmitButtonClick() {
        String message = messageInput.getText();

        // Convert message to Morse code
        Morse morseConverter = new Morse();
        String morseCode = morseConverter.toMorse(message);

        // Display the Morse code
        morsecodeMessage.setText(morseCode);

        // Play the Morse code using tones
        Sound soundPlayer = new Sound();
        new Thread(() -> soundPlayer.playMorseSymbol(morseCode)).start();  // Play asynchronously
    }




}
