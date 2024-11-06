package edu.augustana;


import edu.augustana.sound.SoundGenerator;

public class HAMRadio {
    private double frequency;
    private String filterMode;
    private double volume;
    private SoundGenerator receivingSoundPlayer;

    private static final double FREQUENCY_TOLERANCE = 100.0;

    public HAMRadio() {
        this.frequency = 15000; // 15 MHz
        this.filterMode = "Bandpass";
        this.volume = 0.50;
        this.receivingSoundPlayer = new SoundGenerator();
    }


    public void setFrequency(double frequency) {
        if (frequency >= 0 && frequency <= 30000) {
            this.frequency = frequency;
            System.out.println("Frequency set to: " + frequency + " kHz");
        } else {
            System.out.println("Error: Frequency out of range.");
        }
    }

    public double getFrequency() {
        return frequency;
    }


    public void setFilterMode(String filterMode) {
        if (filterMode.equals("Bandpass") || filterMode.equals("Low-pass") || filterMode.equals("High-pass")) {
            this.filterMode = filterMode;
            System.out.println("Filter mode set to: " + filterMode);
        } else {
            System.out.println("Error: Invalid filter mode.");
        }
    }


    public String getFilterMode() {
        return filterMode;
    }


    /**
     *
     * @param volume (min of 0, max of 1.0)
     */
    public void setVolume(double volume) {
        if (volume >= 0 && volume <= 1) {
            this.volume = volume;
            this.receivingSoundPlayer.setVolume(volume);
            System.out.println("Volume set to: " + volume);
        } else {
            throw new IllegalArgumentException("Error: Volume out of range.");
        }
    }

    /**
     *
     * @return volume (between 0.0 and 1.0)
     */
    public double getVolume() {
        return volume;
    }

    public boolean canHear(double sendersFrequency) {
        return (Math.abs(sendersFrequency - frequency) <= FREQUENCY_TOLERANCE);
    }
    public void receiveMorseMessage(String morseCode, double freq) {
        new Thread(() -> receivingSoundPlayer.playMorseSymbol(morseCode)).start();
    }

    public SoundGenerator getReceivingSoundPlayer() {
        return receivingSoundPlayer;
    }

    @Override
    public String toString() {
        return "HAMRadio{" +
                "frequency=" + frequency +
                ", filterMode='" + filterMode + '\'' +
                ", volume=" + volume +
                '}';
    }

}
