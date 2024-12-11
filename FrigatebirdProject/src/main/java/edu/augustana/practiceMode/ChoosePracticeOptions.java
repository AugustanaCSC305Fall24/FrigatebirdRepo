package edu.augustana.practiceMode;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.augustana.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ChoosePracticeOptions {

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
