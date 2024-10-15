package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.io.IOException;

public class TuneUIController {

    @FXML
    private Slider frequencySlider;
    @FXML
    private Label frequencyLabel;
    @FXML
    private ComboBox<String> filterModeComboBox;
    @FXML
    private Slider volumeSlider;

    private double savedFrequency;
    private double savedVolume;
    private String savedFilterMode;

    @FXML
    public void initialize() {
        HAMRadio radio = App.radio;

        frequencySlider.setMin(0);
        frequencySlider.setMax(30000);
        frequencySlider.setValue(radio.getFrequency());

        frequencySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double frequency = newVal.doubleValue();
            frequencyLabel.setText(String.format("%.2f MHz", frequency / 1000));
            radio.setFrequency(frequency);
        });

        filterModeComboBox.getItems().addAll("Bandpass", "Low-pass", "High-pass");
        filterModeComboBox.setValue(radio.getFilterMode());

        filterModeComboBox.setOnAction(event -> {
            String filterMode = filterModeComboBox.getValue();
            radio.setFilterMode(filterMode);
        });

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
        App.setTuneUIController(this);  // Store controller reference in App
        App.setRoot("HomePage");  // Go back to HomePage
    }

    public void saveSettings(ActionEvent event) {
        savedFrequency = frequencySlider.getValue();
        savedFilterMode = filterModeComboBox.getValue();
        savedVolume = volumeSlider.getValue();

        System.out.println("Settings Saved:");
        System.out.println("Frequency: " + savedFrequency + " MHz");
        System.out.println("Filter Mode: " + savedFilterMode);
        System.out.println("Volume: " + savedVolume);
    }

    public double getSavedFrequency() {
        return savedFrequency;
    }

    public String getSavedFilterMode() {
        return savedFilterMode;
    }

    public double getSavedVolume() {
        return savedVolume;
    }
}
