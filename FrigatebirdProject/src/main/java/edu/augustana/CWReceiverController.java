package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class CWReceiverController {
    @FXML
    private TextField messageInput;
    @FXML
    private TextField frequencyInput;
    @FXML
    private TextArea morsecodeMessage;
    @FXML
    private AnchorPane chatLogBox;
    private VBox chatLogVBox = new VBox();    // VBox to hold the chat messages

    private Sound soundPlayer = new Sound();  // Sound instance

    @FXML
    public void onTransmitButtonClick() {
        String message = messageInput.getText();
        //chatLogBox.setAccessibleText(message);

        // Convert message to Morse code
        Morse morseConverter = new Morse();
        String morseCode = morseConverter.toMorse(message);

        Label messageLabel = new Label("Message: " + message);
        chatLogVBox.getChildren().add(messageLabel);

        // Display the Morse code
        morsecodeMessage.setText(morseCode);

        // Play the Morse code using tones
        Sound soundPlayer = new Sound();
        new Thread(() -> soundPlayer.playMorseSymbol(morseCode)).start();  // Play asynchronously

    }

    @FXML
    void initialize() {

        chatLogBox.getChildren().add(chatLogVBox);

        assert chatLogBox != null : "fx:id=\"chatLogBox\" was not injected: check your FXML file 'CWReceiver.fxml'.";
        assert frequencyInput != null : "fx:id=\"frequencyInput\" was not injected: check your FXML file 'CWReceiver.fxml'.";
        assert messageInput != null : "fx:id=\"messageInput\" was not injected: check your FXML file 'CWReceiver.fxml'.";
        assert morsecodeMessage != null : "fx:id=\"morsecodeMessage\" was not injected: check your FXML file 'CWReceiver.fxml'.";

    }

}
