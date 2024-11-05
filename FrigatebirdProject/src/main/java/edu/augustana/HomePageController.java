package edu.augustana;

import java.io.IOException;
import javafx.fxml.FXML;

public class HomePageController {

    @FXML
    private void switchToTuningView() throws IOException {
        App.setRoot("tune");
    }

    @FXML
    private void switchToCWReceiver() throws IOException {
        App.setRoot("CWReceiver");
    }
    @FXML
    private void switchToPracticeMode() throws IOException{
        App.setRoot("practiceMode");
    }
}

