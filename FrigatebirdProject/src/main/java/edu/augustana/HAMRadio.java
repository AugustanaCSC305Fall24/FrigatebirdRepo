package edu.augustana;

import edu.augustana.sound.SoundGenerator;

public class HAMRadio {
    private double frequency = 7067;
    private double volume = 0.5;
    private int filterLevel = 50;
    private final SoundGenerator receivingSoundPlayer = new SoundGenerator();
    private static final double FREQUENCY_TOLERANCE = 100.0;

    public void setFrequency(double frequency) {
        if (frequency >= 7000 && frequency <= 7067) {
            this.frequency = frequency;
        }
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFilterLevel(int filterLevel) {
        this.filterLevel = filterLevel;
    }

    public int getFilterLevel() {
        return filterLevel;
    }

    public void setVolume(double volume) {
        this.volume = volume;
        receivingSoundPlayer.setVolume(volume);
    }

    public double getVolume() {
        return volume;
    }

    public boolean canHear(double sendersFrequency) {
        return Math.abs(sendersFrequency - frequency) <= FREQUENCY_TOLERANCE;
    }

    public double calculateFrequencyDeviation(double sendersFrequency) {
        return Math.abs(frequency - sendersFrequency);
    }

    public void receiveMorseMessage(String morseCode, double freq) {
        double deviation = calculateFrequencyDeviation(freq);
        new Thread(() -> receivingSoundPlayer.playMorseSymbol(morseCode, deviation)).start();
    }

    public SoundGenerator getReceivingSoundPlayer() {
        return receivingSoundPlayer;
    }


}
