package edu.augustana;

/**
 * HAMRadio manages the state of the radio, including frequency tuning,
 * filter mode selection, and volume settings.
 */
public class HAMRadio {
    private double frequency; // Frequency in kHz
    private String filterMode; // Bandpass, Low-pass, High-pass
    private double volume; // Volume from 0 to 100

    /**
     * Default constructor initializes the radio with default settings.
     */
    public HAMRadio() {
        this.frequency = 15000; // Default frequency: 15 MHz
        this.filterMode = "Bandpass"; // Default filter mode
        this.volume = 50; // Default volume: 50%
    }

    /**
     * Sets the frequency to the specified value.
     * @param frequency The new frequency in kHz.
     */
    public void setFrequency(double frequency) {
        if (frequency >= 0 && frequency <= 30000) {
            this.frequency = frequency;
            System.out.println("Frequency set to: " + frequency + " kHz");
        } else {
            System.out.println("Error: Frequency out of range.");
        }
    }


    /**
     * Gets the current frequency setting.
     * @return The current frequency in kHz.
     */
    public double getFrequency() {
        return frequency;
    }

    /**
     * Sets the filter mode.
     * @param filterMode The filter mode (Bandpass, Low-pass, High-pass).
     */
    public void setFilterMode(String filterMode) {
        if (filterMode.equals("Bandpass") || filterMode.equals("Low-pass") || filterMode.equals("High-pass")) {
            this.filterMode = filterMode;
            System.out.println("Filter mode set to: " + filterMode);
        } else {
            System.out.println("Error: Invalid filter mode.");
        }
    }

    /**
     * Gets the current filter mode.
     * @return The current filter mode.
     */
    public String getFilterMode() {
        return filterMode;
    }

    /**
     * Sets the volume to the specified value.
     * @param volume The new volume level.
     */
    public void setVolume(double volume) {
        if (volume >= 0 && volume <= 100) {
            this.volume = volume;
            System.out.println("Volume set to: " + volume);
        } else {
            System.out.println("Error: Volume out of range.");
        }
    }

    /**
     * Gets the current volume level.
     * @return The current volume level.
     */
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
