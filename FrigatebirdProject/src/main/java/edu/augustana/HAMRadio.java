package edu.augustana;

import edu.augustana.sound.SoundGenerator;

public class HAMRadio {
    private double frequency;
    private double volume;
    private int filterLevel;
    private SoundGenerator receivingSoundPlayer;

    private static final double FREQUENCY_TOLERANCE = 100.0;

    public HAMRadio() {
        this.frequency = 15000;
        this.volume = 0.50;
        this.filterLevel = 50;
        this.receivingSoundPlayer = new SoundGenerator();
    }

    public void setFrequency(double frequency) {
        if (frequency >= 7000 && frequency <= 7067) {
            this.frequency = frequency;
            System.out.println("Frequency set to: " + (frequency / 1000) + " MHz");
        } else {
            System.out.println("Error: Frequency out of range.");
        }
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFilterLevel(int filterLevel) {
        if (filterLevel >= 0 && filterLevel <= 100) {
            this.filterLevel = filterLevel;
            System.out.println("Filter level set to: " + filterLevel);
        } else {
            System.out.println("Error: Filter level out of range.");
        }
    }

    public int getFilterLevel() {
        return filterLevel;
    }

    public void setVolume(double volume) {
        if (volume >= 0 && volume <= 1) {
            this.volume = volume;
            this.receivingSoundPlayer.setVolume(volume);
            System.out.println("Volume set to: " + volume);
        } else {
            throw new IllegalArgumentException("Error: Volume out of range.");
        }
    }

    public double getVolume() {
        return volume;
    }

    public boolean canHear(double sendersFrequency) {
        return (Math.abs(sendersFrequency - frequency) <= FREQUENCY_TOLERANCE);
    }

    public double calculateFrequencyDeviation(double sendersFrequency) {
        return Math.abs(this.frequency - sendersFrequency);
    }

    public void receiveMorseMessage(String morseCode, double freq) {
        double deviation = calculateFrequencyDeviation(freq);
        new Thread(() -> receivingSoundPlayer.playMorseSymbol(morseCode, deviation)).start();
    }

    public SoundGenerator getReceivingSoundPlayer() {
        return receivingSoundPlayer;
    }

    @Override
    public String toString() {
        return "HAMRadio{" +
                "frequency=" + frequency +
                ", volume=" + volume +
                ", filterLevel=" + filterLevel +
                '}';
    }
}
