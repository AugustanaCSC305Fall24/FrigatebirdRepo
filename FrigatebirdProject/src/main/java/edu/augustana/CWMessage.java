package edu.augustana;

public class CWMessage {

    private final String morseMessage;
    private final double frequency;
    private boolean isFromRemoteClient;

    public void setFromRemoteClient(boolean fromRemoteClient) {
        this.isFromRemoteClient = fromRemoteClient;
    }
    public boolean isFromRemoteClient(){
        return isFromRemoteClient();
    }

    public CWMessage(String morseMessage, double frequency) {
        this.morseMessage = morseMessage;
        this.frequency = frequency;
    }


    public String getMorseMessage() {
        return morseMessage;
    }

    public double getFrequency() {
        return frequency;
    }
}
