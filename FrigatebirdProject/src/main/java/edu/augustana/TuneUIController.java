package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;

public class TuneUIController {

    @FXML
    private Slider frequencySlider;

    @FXML
    private Label frequencyLabel;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Slider filterSlider;

    @FXML
    private Label filterLabel;

    @FXML
    public void initialize() {
        HAMRadio radio = App.radio;


        frequencySlider.setMin(7.00);
        frequencySlider.setMax(7.067);
        frequencySlider.setValue(radio.getFrequency() / 1000);

        frequencySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double frequency = newVal.doubleValue();
            frequencyLabel.setText(String.format("%.3f MHz", frequency));
            radio.setFrequency(frequency * 1000);
        });


        volumeSlider.setMin(0);
        volumeSlider.setMax(100);
        volumeSlider.setValue(radio.getVolume() * 100.0);

        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double volume = newVal.doubleValue();
            radio.setVolume(volume / 100.0);
        });


        filterSlider.setMin(1000);
        filterSlider.setMax(3000);
        filterSlider.setMajorTickUnit(1);
        filterSlider.setMinorTickCount(0);
        filterSlider.setSnapToTicks(true); // Snap to the 1000, 2000, 3000 points
        filterSlider.setValue(2000);


        filterSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            int filterValue = newVal.intValue();
            filterLabel.setText(String.valueOf(filterValue));

            // Map filter value to a noise volume range (0.0 to 1.0)
            double noiseVolume = mapFilterToVolume(filterValue, 1000, 3000, 0.0, 1.0);

            // Update the static noise volume dynamically
            if (radio.getReceivingSoundPlayer() != null) {
                radio.getReceivingSoundPlayer().updateStaticNoiseVolume(noiseVolume);
            }
        });



        if (radio.getReceivingSoundPlayer() != null) {
            radio.getReceivingSoundPlayer().startStaticPlaying();
        } else {
            System.err.println("Error: ReceivingSoundPlayer not initialized.");
        }
    }

    @FXML
    void backToHomeAction(ActionEvent event) throws IOException {
        if (App.radio.getReceivingSoundPlayer() != null) {
            App.radio.getReceivingSoundPlayer().stopStaticPlaying();
        }
        App.setRoot("HomePage");
    }

    @FXML
    public void onNextButtonClick(ActionEvent event) throws IOException {
        System.out.println("Volume set to: " + App.radio.getVolume());
        System.out.println("Frequency set to: " + (App.radio.getFrequency() / 1000) + " MHz");
        System.out.println("Filter level set to: " + App.radio.getFilterLevel());

        Stage senderWindow = new Stage();
        senderWindow.setTitle("Fake sender");

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CWSender.fxml"));
        Parent root = fxmlLoader.load();
        Scene senderScene = new Scene(root, 600, 400);
        senderWindow.setScene(senderScene);
        senderWindow.show();
    }

    // Utility method to map filter range (1000-3000) to volume range (0.0-1.0)
    private double mapFilterToVolume(int filterValue, int minFilter, int maxFilter, double minVolume, double maxVolume) {
        return minVolume + ((double) (filterValue - minFilter) / (maxFilter - minFilter)) * (maxVolume - minVolume);
    }
}
