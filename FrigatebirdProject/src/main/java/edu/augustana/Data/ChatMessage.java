package edu.augustana.Data;

import javafx.scene.paint.Color;

public class ChatMessage {
    private String text;
    private String sender;
    private String colorCode;
    private boolean isBold;
    private boolean isFromRemoteClient;

    public ChatMessage(String text, String sender, Color color, boolean isBold) {
        this.text = text;
        this.sender = sender;
        this.colorCode = color.toString();
        this.isBold = isBold;
        this.isFromRemoteClient = false;
    }

    public String getText() {
        return text;
    }

    public String getSender() {
        return sender;
    }

    public Color getColor() {
        return Color.valueOf(colorCode);
    }

    public boolean isBold() {
        return isBold;
    }

    public boolean isFromRemoteClient() {
        return isFromRemoteClient;
    }
    public void setFromRemoteClient(boolean fromRemoteClient) {
        this.isFromRemoteClient = fromRemoteClient;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "text='" + text + '\'' +
                ", sender='" + sender + '\'' +
                ", color=" + colorCode.toString() +
                '}';
    }

}
