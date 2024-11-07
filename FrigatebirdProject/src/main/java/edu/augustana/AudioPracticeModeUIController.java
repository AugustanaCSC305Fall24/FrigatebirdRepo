package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AudioPracticeModeUIController {
    @FXML
    public void backToPracticeModeAction(ActionEvent event) throws IOException {
        App.setRoot("PractiseOption");
    }
}
