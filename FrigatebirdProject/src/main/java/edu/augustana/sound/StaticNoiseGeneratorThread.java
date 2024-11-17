package edu.augustana.sound;

import edu.augustana.HAMRadio;

import javax.sound.sampled.*;
import java.nio.ByteBuffer;
import java.util.Random;

class StaticNoiseGeneratorThread extends Thread {
    private final HAMRadio radio;
    private volatile double volumeScale = 0.5;
    private SourceDataLine line;
    private boolean exitExecution = false;

    public StaticNoiseGeneratorThread(HAMRadio radio) {
        this.radio = radio;
    }

    public void run() {
        try {
            AudioFormat format = new AudioFormat(44100, 16, 1, true, true);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        ByteBuffer buffer = ByteBuffer.allocate(5000);
        Random random = new Random();

        while (!exitExecution) {
            buffer.clear();
            for (int i = 0; i < 2500; i++) {
                buffer.putShort((short) (random.nextGaussian() * Short.MAX_VALUE / 3 * radio.getVolume() * volumeScale));
            }
            line.write(buffer.array(), 0, buffer.position());
        }

        line.drain();
        line.close();
    }

    public synchronized void updateVolume(double volumeScale) {
        this.volumeScale = volumeScale;
    }

    public void exit() {
        exitExecution = true;
    }
}
