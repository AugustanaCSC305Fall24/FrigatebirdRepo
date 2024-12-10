package edu.augustana;

public class CWMessage {

    private final String morseMessage;
    private final double frequency;

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
