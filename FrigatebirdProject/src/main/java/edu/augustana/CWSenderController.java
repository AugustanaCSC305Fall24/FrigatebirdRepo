package edu.augustana;

import edu.augustana.sound.SoundGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

    @FXML
    private TextField morseInput;



    @FXML private void initialize() {
        frequencyInput.setText("" + App.radio.getFrequency());

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

        double sendersFrequency = Double.parseDouble(frequencyInput.getText());

        if (App.radio.canHear(sendersFrequency)) {
            appendToChatBox("Message at " + sendersFrequency +": " + message);
            Label messageLabel = new Label("Message at " + sendersFrequency +": " + message);
            chatLogVBox.getChildren().add(messageLabel);

            Morse morseConverter = new Morse();
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
