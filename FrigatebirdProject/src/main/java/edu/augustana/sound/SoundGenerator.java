package edu.augustana.sound;

import edu.augustana.App;

import javax.sound.sampled.*;

public class SoundGenerator {
    private static final int SAMPLE_RATE = 44100;
    private double volume = 1.0;
    private static final double WPM = 18;
    private static final double UNIT_LENGTH = 60.0 / (WPM * 50);

    private StaticNoiseGeneratorThread staticNoiseThread;

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }

    public void playTone(double baseFrequency, int durationMs, double deviation) {
        try {
            double adjustedFrequency = baseFrequency * (1 - (deviation / 1000));
            byte[] buffer = generateTone(adjustedFrequency, durationMs, deviation);
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

    public byte[] generateTone(double frequency, int durationMs, double deviation) {
        int length = (int) (SAMPLE_RATE * (durationMs / 1000.0));
        byte[] buffer = new byte[length];
        double distortionFactor = Math.min(1.0, deviation / 100.0);

        for (int i = 0; i < length; i++) {
            double angle = 2.0 * Math.PI * i * frequency / SAMPLE_RATE;
            double baseWave = Math.sin(angle) * Byte.MAX_VALUE * volume;
            double noise = Math.random() * 2 - 1;
            baseWave = baseWave * (1 - distortionFactor) + noise * Byte.MAX_VALUE * volume * distortionFactor;
            buffer[i] = (byte) baseWave;
        }
        return buffer;
    }

    public double getUnitLength() {
        return UNIT_LENGTH;
    }

    public void playMorseSymbol(String morseCode, double deviation) {
        final int DOT_DURATION = 100;
        final int DASH_DURATION = 3 * DOT_DURATION;
        final long UNIT_MILLIS = (long) (UNIT_LENGTH * 10000);

        for (int i = 0; i < morseCode.length(); i++) {
            char symbol = morseCode.charAt(i);
            if (symbol == '.') {
                playTone(600, DOT_DURATION, deviation);
            } else if (symbol == '-') {
                playTone(600, DASH_DURATION, deviation);
            }
            if (i < morseCode.length() - 1) {
                try {
                    Thread.sleep(UNIT_MILLIS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
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
            staticNoiseThread.updateVolume(filterLevel / 100.0);
        }
    }
}
