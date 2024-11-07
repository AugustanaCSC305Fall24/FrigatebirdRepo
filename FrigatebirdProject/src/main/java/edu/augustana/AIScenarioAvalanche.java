package edu.augustana;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AIScenarioAvalanche {

    @FXML
    private TextArea chatLog;

    @FXML
    private TextField messageSender;

    @FXML
    private void backToHomeAction()throws IOException{
        App.setRoot("HomePage");
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void initialize(){
        showAlert("Help!", "Help, there has been an Avalanche on a nearby mountain. We are stuck");
    }

}
