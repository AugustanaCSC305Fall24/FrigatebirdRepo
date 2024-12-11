package edu.augustana.sound;

import edu.augustana.App;
import edu.augustana.CWMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class ReceiverController {

    @FXML
    private TextArea ipInput;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Slider frequencySlider;

    @FXML
    private TextArea receivedMessageArea;

    @FXML
    private void backToHomeAction() throws IOException {
        App.setRoot("HomePage");
    }

    @FXML
    private void initialize() {
        App.setReceiverController(this); // Register this controller with App

        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Volume Slider Value: " + newVal);
        });

        frequencySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Frequency Slider Value: " + newVal);
        });
    }

    public void displayReceivedMessage(CWMessage message) {
        String morseMessage = message.getMorseMessage();
        double frequency = message.getFrequency();

        // Append the message to the receivedMessageArea
        receivedMessageArea.appendText(
                "From Frequency: " + frequency + "\nMorse Code: " + morseMessage + "\n\n"
        );

        // Optionally: Play the Morse code as sound
        App.radio.receiveMorseMessage(morseMessage, frequency);
    }
}
