package edu.augustana.sound;

import edu.augustana.App;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class ReceiverController {

    @FXML
    private TextArea ipInput;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Slider frequencySlider;

    @FXML
    private TextArea receivedMessageArea;

    @FXML
    private void backToHomeAction() throws IOException {
        App.setRoot("HomePage");
    }


    @FXML
    private void initialize() {
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Volume Slider Value: " + newVal);
        });

        frequencySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Frequency Slider Value: " + newVal);
        });
    }
}
