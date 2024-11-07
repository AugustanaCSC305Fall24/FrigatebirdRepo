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

        filterSlider.setMin(0);
        filterSlider.setMax(100);
        filterSlider.setValue(50);

        filterSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            int filterValue = newVal.intValue();
            filterLabel.setText(String.valueOf(filterValue));
            radio.setFilterLevel(filterValue);
            radio.getReceivingSoundPlayer().updateStaticNoiseVolume(filterValue);
        });

        radio.getReceivingSoundPlayer().startStaticPlaying();
    }

    @FXML
    void backToHomeAction(ActionEvent event) throws IOException {
        App.radio.getReceivingSoundPlayer().stopStaticPlaying();
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
}
