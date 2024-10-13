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
    private TextArea morseDisplay;

    @FXML
    public void onTransmitButtonClick() {
        String message = messageInput.getText();
        String frequency = frequencyInput.getText();

        System.out.println("Message: " + message);
        System.out.println("Frequency: " + frequency);
    }
}
