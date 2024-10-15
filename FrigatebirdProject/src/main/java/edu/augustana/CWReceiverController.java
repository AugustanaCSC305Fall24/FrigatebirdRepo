package edu.augustana;

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

    private double savedFrequency;
    private String savedFilterMode;
    private double savedVolume;

    private static final double FREQUENCY_TOLERANCE = 100.0;  // Tolerance of ±100 MHz

    private Sound soundPlayer = new Sound();  // Sound instance

    /**
     * This method is called to set the saved settings when switching to this scene.
     */
    public void setSavedSettings(double frequency, String filterMode, double volume) {
        this.savedFrequency = frequency;
        this.savedFilterMode = filterMode;
        this.savedVolume = volume;

        System.out.println("Received Settings:");
        System.out.println("Frequency: " + frequency);
        System.out.println("Filter Mode: " + filterMode);
        System.out.println("Volume: " + volume);
    }

    /**
     * Called when the Transmit button is clicked.
     * Checks if the entered frequency is within the ±100 MHz range.
     */
    @FXML
    public void onTransmitButtonClick() {
        String message = messageInput.getText();
        double enteredFrequency = Double.parseDouble(frequencyInput.getText());

        // Use a tolerance to compare floating-point numbers
        if (Math.abs(enteredFrequency - savedFrequency) <= FREQUENCY_TOLERANCE) {
            Morse morseConverter = new Morse();
            String morseCode = morseConverter.toMorse(message);

            // Display the Morse code
            morsecodeMessage.setText(morseCode);

            // Play Morse code sound asynchronously
            new Thread(() -> soundPlayer.playMorseSymbol(morseCode)).start();

            System.out.println("Message transmitted: " + message);
        } else {
            morsecodeMessage.setText("Frequency out of range.");
            System.out.println("Error: Frequency mismatch.");
        }
    }
}
