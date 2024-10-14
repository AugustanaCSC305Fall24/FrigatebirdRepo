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
        Morse morseConverter = new Morse();
        String morseCode = morseConverter.toMorse(message);

        morsecodeMessage.setText(morseCode);

        // Play the Morse code using a new thread to avoid blocking the UI
        new Thread(() -> soundPlayer.playMorseSymbol(morseCode)).start();
    }

//    @FXML
//    void StopButton(ActionEvent event) {
//        // Stop the sound playback
//        soundPlayer.stopPlayback();
//    }
}
