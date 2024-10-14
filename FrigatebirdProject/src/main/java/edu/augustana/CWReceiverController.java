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
    public void onTransmitButtonClick() {
        String message = messageInput.getText();
        String frequency = frequencyInput.getText();

        System.out.println("Message: " + message);
        System.out.println("Frequency: " + frequency);
    }

    @FXML
    void setBackAction(ActionEvent event) throws IOException {
        App.setRoot("HomePage");
    }
}
