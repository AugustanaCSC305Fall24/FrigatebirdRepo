package edu.augustana.sound;

import edu.augustana.App;
import edu.augustana.HAMRadio;

import javax.sound.sampled.*;

public class SoundGenerator {

    private static final int SAMPLE_RATE = 44100;
    private double volume = 1.0; // Volume (range 0.0 to 1.0)
    private volatile boolean isPlaying = true;
    private static final double WPM = 18;  // Words per minute
    private static final double UNIT_LENGTH = 60.0 / (WPM * 50);  // Unit length in seconds

    private SourceDataLine line;
    private Thread playbackThread;
    private StaticNoiseGeneratorThread staticNoiseThread;

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }

    // Method to play a tone at a specific frequency and duration, adjusting for pitch based on deviation
    private void playTone(double baseFrequency, int durationMs, double deviation) {
        try {
            double adjustedFrequency = baseFrequency * (1 - (deviation / 1000)); // Adjust frequency based on deviation
            byte[] buffer = generateTone(adjustedFrequency, durationMs, deviation); // Generate adjusted tone data

            AudioFormat format = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
            SourceDataLine line = AudioSystem.getSourceDataLine(format);
            line.open(format);
            line.start();

            line.write(buffer, 0, buffer.length);
            line.drain();
            line.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Method to generate the raw tone data, including distortion based on the deviation
    private byte[] generateTone(double frequency, int durationMs, double deviation) {
        int length = (int) (SAMPLE_RATE * (durationMs / 1000.0));
        byte[] buffer = new byte[length];
        double distortionFactor = Math.min(1.0, deviation / 100.0); // Ensure distortion factor does not exceed 1.0

        for (int i = 0; i < length; i++) {
            double angle = 2.0 * Math.PI * i * frequency / SAMPLE_RATE;
            double baseWave = Math.sin(angle) * Byte.MAX_VALUE * volume;

            // Add distortion based on the deviation
            double noise = Math.random() * 2 - 1; // Random value between -1 and 1
            baseWave = baseWave * (1 - distortionFactor) + noise * Byte.MAX_VALUE * volume * distortionFactor;

            buffer[i] = (byte) baseWave;
        }
        return buffer;
    }

    // Play Morse symbols with tones, adjusting the pitch based on the frequency deviation
    public void playMorseSymbol(String morseCode, double deviation) {
        final int DOT_DURATION = 100;
        final int DASH_DURATION = 3 * DOT_DURATION;
        final long UNIT_MILLIS = (long) (UNIT_LENGTH * 1000);

        for (int i = 0; i < morseCode.length(); i++) {
            char symbol = morseCode.charAt(i);

            if (symbol == '.') {
                playTone(600, DOT_DURATION, deviation); // Pass deviation to adjust pitch and clarity
            } else if (symbol == '-') {
                playTone(600, DASH_DURATION, deviation);
            }

            try {
                if (i < morseCode.length() - 1) {
                    char nextSymbol = morseCode.charAt(i + 1);
                    if (nextSymbol == ' ') {
                        if (i + 2 < morseCode.length() && morseCode.charAt(i + 2) != ' ') {
                            Thread.sleep(3 * UNIT_MILLIS);
                        } else {
                            Thread.sleep(7 * UNIT_MILLIS);
                        }
                    } else {
                        Thread.sleep(UNIT_MILLIS);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void startStaticPlaying() {
        if (staticNoiseThread == null) {
            staticNoiseThread = new StaticNoiseGeneratorThread(App.radio);
            staticNoiseThread.start();
        }
    }

    public void stopStaticPlaying() {
        if (staticNoiseThread != null) {
            staticNoiseThread.exit();
            staticNoiseThread = null;
        }
    }

    public void updateStaticNoiseVolume(double filterLevel) {
        if (staticNoiseThread != null) {
            staticNoiseThread.updateVolume(filterLevel / 100.0); // Scale filter level to 0.0 - 1.0
        }
    }
}
