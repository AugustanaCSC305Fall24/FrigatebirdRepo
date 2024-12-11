package edu.augustana;

import edu.augustana.Data.ChatRoom;
import edu.augustana.bots.GeminiBot;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import java.io.IOException;

public class AIUIController {

    @FXML
    private TextArea chatBotText;

    @FXML
    private TextField morseCode;

    private ChatRoom chatRoom;

    @FXML
    void returnToLobby() throws IOException {
        App.setRoot("HomePage");
    }

    @FXML
    void sendtoTextArea(ActionEvent event)throws IOException {

    }

    @FXML
    void initialize() {
        assert chatBotText != null : "fx:id=\"chatBot\" was not injected: check your FXML file 'ChatBot.fxml'.";
        assert morseCode != null : "fx:id=\"textArea\" was not injected: check your FXML file 'ChatBot.fxml'.";

//        chatRoom = new ChatRoom("HAM Radio AI Chat");
//        chatRoom.setNewMessageEventListener(message -> {
//            Platform.runLater(() -> chatBotText.appendText(message.getSender() + ": " + message.getText() + "\n"));
//        });
//
//        // Add AI bot to the chat room
//        String geminiApiKey = "AIzaSyB0aJNi94sa_i1vfSjdiWFwc3e6U5sdBxk";
//        GeminiBot radioBot = new GeminiBot(
//                "Radio AI",
//                Color.BLUE,
//                chatRoom,
//                "You are a HAM radio expert providing advice and assistance. Always reply in english unless asked to reply in morse code.",
//                geminiApiKey
//        );
//        chatRoom.getBots().add(radioBot);
    }

}

