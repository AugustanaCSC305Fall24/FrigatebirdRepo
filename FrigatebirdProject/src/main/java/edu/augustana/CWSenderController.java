package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CWSenderController {

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

    private long lastInputTime = System.currentTimeMillis();

    @FXML
    private TextField morseInput;



    @FXML private void initialize() {
        frequencyInput.setText("" );

        morseInput.setOnKeyPressed(this::handleMorseInput);

    }


    public void handleMorseInput(KeyEvent evt) {
        long currentTime = System.currentTimeMillis();
        KeyCode input = evt.getCode();
        System.out.println("input: **" + input + "**");
        if (currentTime - lastInputTime > 1000) {
            morsecodeMessage.appendText(" "); // Add a space if more than 1 second has passed
        }
        if (input.equals(KeyCode.DIGIT1)) {
            morsecodeMessage.appendText(".");
            double deviation = App.radio.calculateFrequencyDeviation(App.radio.getFrequency());
            App.radio.getReceivingSoundPlayer().playMorseSymbol(".", deviation);
        } else if (input.equals(KeyCode.DIGIT2)) {
            morsecodeMessage.appendText("-");
            double deviation = App.radio.calculateFrequencyDeviation(App.radio.getFrequency());
            App.radio.getReceivingSoundPlayer().playMorseSymbol("-", deviation);
        } else {
            System.out.println("Invalid input: Only '1' for dit and '2' for dah are allowed.");
        }
        lastInputTime = currentTime;
        morseInput.clear();
    }


    @FXML
    public void onTransmitButtonClick() {
        String message = messageInput.getText();
        String morseCodeText = morsecodeMessage.getText();
        double sendersFrequency = Double.parseDouble(frequencyInput.getText());

        if (App.radio.canHear(sendersFrequency)) {
            appendToChatBox("User: " + message);
            Label messageLabel = new Label("User: " + message);
            chatLogVBox.getChildren().add(messageLabel);

            Morse morseConverter = new Morse();
            String englishMessage = morseConverter.toEnglish(morseCodeText);
            appendToChatBox("Translated to English: " + englishMessage);
            String morseCode = morseConverter.toMorse(message);
            morsecodeMessage.setText(morseCode);



            App.radio.receiveMorseMessage(morseCode, sendersFrequency);

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
