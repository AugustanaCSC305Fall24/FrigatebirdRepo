package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.io.IOException;

/**
 * TuneUIController bridges the SceneBuilder UI with the backend TuneController logic.
 */
public class TuneUIController {

    @FXML
    private Slider frequencySlider;
    @FXML
    private Label frequencyLabel;
    @FXML
    private ComboBox<String> filterModeComboBox;
    @FXML
    private Slider volumeSlider;

    /**
     * Initialize the UI and bind components to the TuneController logic.
     */
    @FXML
    public void initialize() {

        HAMRadio radio = App.radio;
        // Initialize frequency slider
        frequencySlider.setMin(0);
        frequencySlider.setMax(30000);
        frequencySlider.setValue(radio.getFrequency());

        frequencySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double frequency = newVal.doubleValue();
            frequencyLabel.setText(String.format("%.2f MHz", frequency / 1000));
            radio.setFrequency(frequency);
        });

        // Initialize filter mode ComboBox
        filterModeComboBox.getItems().addAll("Bandpass", "Low-pass", "High-pass");
        filterModeComboBox.setValue(radio.getFilterMode());

        filterModeComboBox.setOnAction(event -> {
            String filterMode = filterModeComboBox.getValue();
            radio.setFilterMode(filterMode);
        });

        // Initialize volume slider
        volumeSlider.setMin(0);
        volumeSlider.setMax(100);
        volumeSlider.setValue(radio.getVolume());

        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double volume = newVal.doubleValue();
            radio.setVolume(volume);
        });
    }

    @FXML
    void backToHomeAction(ActionEvent event) throws IOException {
        System.out.println("DEBUG: radio="+App.radio);
        App.setRoot("HomePage");
    }
}
