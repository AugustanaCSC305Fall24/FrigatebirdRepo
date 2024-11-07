package edu.augustana;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ChoosePractiseOptions {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void SwitchToAudio(ActionEvent event) throws IOException {
        App.setRoot("AudioPracticeMode");
    }

    @FXML
    void SwitchToVisual(ActionEvent event) throws IOException {
        App.setRoot("VisualPracticeMode");
    }

    @FXML
    void backToHomeAction() throws IOException{
        App.setRoot("HomePage");
    }

    @FXML
    void initialize() {

    }

}
