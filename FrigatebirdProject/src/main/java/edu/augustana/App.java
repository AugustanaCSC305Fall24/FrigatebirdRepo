package edu.augustana;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static HAMRadio radio = new HAMRadio();

    private static TuneUIController tuneUIController;  // Store controller reference

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("HomePage"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = loader.load();

        if (fxml.equals("CWReceiver")) {
            CWReceiverController receiverController = loader.getController();
            // Pass saved settings to CWReceiverController
            receiverController.setSavedSettings(
                    tuneUIController.getSavedFrequency(),
                    tuneUIController.getSavedFilterMode(),
                    tuneUIController.getSavedVolume()
            );
        }

        scene.setRoot(root);
    }

    public static void setTuneUIController(TuneUIController controller) {
        tuneUIController = controller;  // Store reference for later use
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
