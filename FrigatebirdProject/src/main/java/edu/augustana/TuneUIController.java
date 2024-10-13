package edu.augustana;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * TuneUIController bridges the SceneBuilder UI with the backend TuneController logic.
 */
public class TuneUIController {

    private TuneController tuneController = new TuneController(); // Backend logic

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
        // Initialize frequency slider
        frequencySlider.setMin(0);
        frequencySlider.setMax(30000);
        frequencySlider.setValue(tuneController.getFrequency());

        frequencySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double frequency = newVal.doubleValue();
            frequencyLabel.setText(String.format("%.2f MHz", frequency / 1000));
            tuneController.setFrequency(frequency);
        });

        // Initialize filter mode ComboBox
        filterModeComboBox.getItems().addAll("Bandpass", "Low-pass", "High-pass");
        filterModeComboBox.setValue(tuneController.getFilterMode());

        filterModeComboBox.setOnAction(event -> {
            String filterMode = filterModeComboBox.getValue();
            tuneController.setFilterMode(filterMode);
        });

        // Initialize volume slider
        volumeSlider.setMin(0);
        volumeSlider.setMax(100);
        volumeSlider.setValue(tuneController.getVolume());

        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double volume = newVal.doubleValue();
            tuneController.setVolume(volume);
        });
    }
}
