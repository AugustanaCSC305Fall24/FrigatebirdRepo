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

        // Convert message to Morse code
        Morse morseConverter = new Morse();
        String morseCode = morseConverter.toMorse(message);

        // Display the Morse code
        morsecodeMessage.setText(morseCode);

        // Play the Morse code using tones
        Sound soundPlayer = new Sound();
        new Thread(() -> soundPlayer.playMorseSymbol(morseCode)).start();  // Play asynchronously
    }



    @FXML
    void setBackAction(ActionEvent event) throws IOException {
        App.setRoot("HomePage");
    }
}
