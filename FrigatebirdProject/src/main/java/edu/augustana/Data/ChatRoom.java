package edu.augustana.Data;

import edu.augustana.bots.ChatBotUIController;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private String roomTitle;
    private List<ChatBotUIController> bots;
    private List<edu.augustana.Data.ChatMessage> chatLogMessageList;
    private NewMessageEventListener newMessageEventListener = null;

    public ChatRoom(String roomTitle) {
        this.roomTitle = roomTitle;
        bots = new ArrayList<>();
        chatLogMessageList = new ArrayList<>();
        chatLogMessageList.add(new edu.augustana.Data.ChatMessage("Welcome to " +roomTitle+"!", "System", Color.GREEN, false));
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public List<ChatBotUIController> getBots() {
        return bots;
    }

    public List<edu.augustana.Data.ChatMessage> getChatMessageList() {
        return chatLogMessageList;
    }

    public void addMessage(edu.augustana.Data.ChatMessage message) {
        chatLogMessageList.add(message);
        if (newMessageEventListener != null) {
            newMessageEventListener.onNewMessage(message);
        }
    }

    public void setNewMessageEventListener(NewMessageEventListener listener) {
        newMessageEventListener = listener;
    }

}
