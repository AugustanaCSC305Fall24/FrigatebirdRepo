package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class practiceModeUIController extends Morse {

    @FXML
    private Button backButton;

    @FXML
    public void backToHomeAction(ActionEvent event) throws IOException {
        App.setRoot("HomePage");  // Go back to HomePage
    }

}
