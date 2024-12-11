package edu.augustana;

import java.io.IOException;
import javafx.fxml.FXML;

public class HomePageController {

    @FXML
    private void switchToSenderRadio() throws IOException {
        App.setRoot("tune");
    }

    @FXML
    private void switchToReceiverRadio() throws IOException {
        App.setRoot("receiver");
    }

    @FXML
    private void switchToPracticeMode() throws IOException {
        App.setRoot("practiseOption");
    }

    @FXML
    private void switchToAISceneMode() throws IOException {
        App.setRoot("AIScenarioAvalanche");
    }

    @FXML
    private void switchtoGeminiAI() throws IOException {
        App.setRoot("AIController");
    }
}
