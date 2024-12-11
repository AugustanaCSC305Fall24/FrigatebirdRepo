package edu.augustana;

import edu.augustana.sound.SoundGenerator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URI;
import java.util.Random;

import jakarta.websocket.*;
import com.google.gson.Gson;


@ClientEndpoint
public class App extends Application {

    private static Scene scene;
    public static HAMRadio radio = new HAMRadio();
    private Session webSocketSession = null;
    private static App app;
    private CWSenderController CWsender;

    @Override
    public void start(Stage stage) throws IOException {
        app = this;
        connectToServer("34.55.199.24", "person"+new Random().nextInt(1000000));
        scene = new Scene(loadFXML("HomePage"), 640, 480);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(
                evt -> {
                    radio.getReceivingSoundPlayer().stopStaticPlaying();
                });

    }

    @Override
    public void stop() {
        if (isConnectedToServer()) {
            try {
                webSocketSession.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void connectToServer(String serverIPAddress, String userName) {
        try {
            if (isConnectedToServer()) {
                app.webSocketSession.close();
            }
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            app.webSocketSession = container.connectToServer(app, new URI("ws://"+serverIPAddress+":8000/ws/"+userName));
        } catch (Exception e) {
            e.printStackTrace();
            Platform.runLater(() -> new Alert(Alert.AlertType.ERROR, "Error connecting to server! " + e.getMessage()).show());
        }
    }
    public static boolean isConnectedToServer() {
        return app.webSocketSession != null && app.webSocketSession.isOpen();
    }

    public static void sendMessageToServer(CWMessage msg) {
        if (isConnectedToServer()) {
            String jsonMessage = new Gson().toJson(msg);
            System.out.println("DEBUG: Sending WebSocket message: " + jsonMessage);
            app.webSocketSession.getAsyncRemote().sendText(jsonMessage);
        }
    }

    @OnMessage
    public void onMessage(String jsonMessage) {
        System.out.println("DEBUG: Received WebSocket message: " + jsonMessage);
        CWMessage chatMessage = new Gson().fromJson(jsonMessage, CWMessage.class);
        radio.receiveMorseMessage(chatMessage.getMorseMessage(), chatMessage.getFrequency());
//        chatMessage.setFromRemoteClient(true);
//        CWsender.appendToChatBox(jsonMessage);
    }

   public static void setRoot(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = loader.load();
        scene.setRoot(root);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
