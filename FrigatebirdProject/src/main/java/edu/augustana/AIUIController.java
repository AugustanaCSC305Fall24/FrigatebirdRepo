package edu.augustana;

import edu.augustana.Data.ChatMessage;
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
    void initialize() {
        // Initialize the chat room
        chatRoom = new ChatRoom("HAM Radio AI Chat");
        chatRoom.setNewMessageEventListener(message -> {
            Platform.runLater(() -> chatBotText.appendText(message.getSender() + ": " + message.getText() + "\n"));
        });

        // Add AI bot to the chat room
        String geminiApiKey = "AIzaSyB0aJNi94sa_i1vfSjdiWFwc3e6U5sdBxk";
        GeminiBot radioBot = new GeminiBot(
                "Radio AI",
                Color.BLUE,
                chatRoom,
                "You are a HAM radio expert providing advice and assistance. Always reply in English unless asked to reply in Morse code.",
                geminiApiKey
        );
        chatRoom.getBots().add(radioBot);
    }

    @FXML
    void sendtoTextArea(ActionEvent event) {
        // Get the user input
        String userInput = morseCode.getText().trim();
        if (userInput.isEmpty()) {
            return; // Do nothing if the input is empty
        }

        // Add the user's message to the chat room
        ChatMessage userMessage = new ChatMessage(userInput, "User", Color.BLACK, true);
        chatRoom.addMessage(userMessage);

        // Clear the input field
        morseCode.clear();

        // Trigger AI bot responses
        chatRoom.getBots().forEach(bot -> bot.requestMessage());

        // Ensure chat text wraps properly
        chatBotText.setWrapText(true);
    }

    @FXML
    void returnToLobby() throws IOException {
        App.setRoot("HomePage");
    }
}