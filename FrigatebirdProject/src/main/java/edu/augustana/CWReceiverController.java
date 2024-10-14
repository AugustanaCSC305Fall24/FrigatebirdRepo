package edu.augustana;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.imageio.IIOException;
import java.io.IOException;

public class CWReceiverController {
    @FXML
    private TextField messageInput;
    @FXML
    private TextField frequencyInput;
    @FXML
    private TextArea morseDisplay;

    @FXML
    private TextField morsecodeMessage;

    @FXML
    public void onTransmitButtonClick() {
        String message = messageInput.getText();
        String frequency = frequencyInput.getText();
        // Convert the message to Morse code using the Morse class
        Morse morseConverter = new Morse();
        String morseCode = morseConverter.toMorse(message);

        morsecodeMessage.setText(morseCode);

        System.out.println("Message: " + message);
        System.out.println("Frequency: " + frequency);
        System.out.println("Morse Code: " + morseCode);
    }

    @FXML
    void setBackAction(ActionEvent event) throws IOException {
        App.setRoot("HomePage");
    }
}
