package edu.augustana;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class practiceModeUIController extends Morse {

//    @FXML
//    private Button backButton;

    @FXML
    private void exitApp ()throws IOException{
        App.setRoot("HomePage");
    }

}
