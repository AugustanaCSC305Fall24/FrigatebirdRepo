package edu.augustana;

import edu.augustana.Morse;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CWSenderController {

    @FXML
    private TextField messageInput;
    @FXML
    private TextField frequencyInput;
    @FXML
    private TextArea morsecodeMessage;

    @FXML
    public void onTransmitButtonClick() {
        String message = messageInput.getText();
        String morseCodeText = morsecodeMessage.getText();
        double sendersFrequency = Double.parseDouble(frequencyInput.getText());

        if (App.radio.canHear(sendersFrequency)) {
            // Convert the message to Morse code
            Morse morseConverter = new Morse();
            String morseCode = morseConverter.toMorse(message);

            // Create a CWMessage and send it to the server
            CWMessage messageToServer = new CWMessage(morseCode, sendersFrequency);
            App.sendMessageToServer(messageToServer);

            System.out.println("Message transmitted: " + message);
        } else {
            morsecodeMessage.setText("Frequency out of range.");
            System.out.println("Error: Frequency mismatch.");
        }
    }
}
