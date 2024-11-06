package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CWReceiverController {

    @FXML
    private TextField messageInput;
    @FXML
    private TextField frequencyInput;
    @FXML
    private TextArea morsecodeMessage;
    @FXML
    private AnchorPane chatLogBox;
    private VBox chatLogVBox = new VBox();


    @FXML
    private TextArea chatBox;
    
    @FXML
    private Button backButton;

    @FXML
    private TextField morseInput;


    private double savedFrequency;
    private String savedFilterMode;
    private double savedVolume;

    private static final double FREQUENCY_TOLERANCE = 100.0;

    private Sound soundPlayer = new Sound();  // Sound instance


    public void setSavedSettings(double frequency, String filterMode, double volume) {
        this.savedFrequency = frequency;
        this.savedFilterMode = filterMode;
        this.savedVolume = volume;

        System.out.println("Received Settings:");
        System.out.println("Frequency: " + frequency);
        System.out.println("Filter Mode: " + filterMode);
        System.out.println("Volume: " + volume);
    }

    @FXML
    public void backToHomeAction(ActionEvent event) throws IOException {
        App.setRoot("HomePage");
    }


    public void handleMorseInput() {
        String input = morseInput.getText().trim();


        if (input.equals("1")) {
            morsecodeMessage.appendText(".");
        } else if (input.equals("2")) {
            morsecodeMessage.appendText("-");
        } else {
            System.out.println("Invalid input: Only '1' for dit and '2' for dah are allowed.");
        }

        morseInput.clear();
    }

    @FXML
    public void onTransmitButtonClick() {
        String message = messageInput.getText();


        double enteredFrequency = Double.parseDouble(frequencyInput.getText());



        if (Math.abs(enteredFrequency - savedFrequency) <= FREQUENCY_TOLERANCE) {
            Morse morseConverter = new Morse();
            String morseCode = morseConverter.toMorse(message);

            appendToChatBox("Message: " + message);

        Label messageLabel = new Label("Message: " + message);
        chatLogVBox.getChildren().add(messageLabel);


        morsecodeMessage.setText(morseCode);


        Sound soundPlayer = new Sound();
        new Thread(() -> soundPlayer.playMorseSymbol(morseCode)).start();


            morsecodeMessage.setText(morseCode);


            new Thread(() -> soundPlayer.playMorseSymbol(morseCode)).start();

            System.out.println("Message transmitted: " + message);
        } else {
            morsecodeMessage.setText("Frequency out of range.");
            System.out.println("Error: Frequency mismatch.");
        }
    }
    private void appendToChatBox(String message) {

        chatBox.appendText(message + "\n");
    }

}
