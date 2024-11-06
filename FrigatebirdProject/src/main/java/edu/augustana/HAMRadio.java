package edu.augustana;


public class HAMRadio {
    private double frequency;
    private String filterMode;
    private double volume;


    public HAMRadio() {
        this.frequency = 15000; // 15 MHz
        this.filterMode = "Bandpass";
        this.volume = 50;
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


    public void setVolume(double volume) {
        if (volume >= 0 && volume <= 100) {
            this.volume = volume;
            System.out.println("Volume set to: " + volume);
        } else {
            System.out.println("Error: Volume out of range.");
        }
    }


    public double getVolume() {
        return volume;
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
