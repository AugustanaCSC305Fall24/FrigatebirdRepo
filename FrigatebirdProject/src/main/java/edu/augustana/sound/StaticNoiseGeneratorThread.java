package edu.augustana.sound;

import edu.augustana.HAMRadio;

import javax.sound.sampled.*;
import java.nio.ByteBuffer;
import java.util.Random;

// courtesy of https://gist.github.com/m5mat/f622df23a49586337009c60a9966964c

class StaticNoiseGeneratorThread extends Thread {
    private static StaticNoiseGeneratorThread generatorThread;

    final static public int SAMPLE_SIZE = 2;
    final static public int PACKET_SIZE = 5000;
    private final HAMRadio radio;
    private volatile double volumeScale = 0.5; // Default value for the filter volume

    SourceDataLine line;
    public boolean exitExecution = false;

    public StaticNoiseGeneratorThread(HAMRadio radio) {
        this.radio = radio;
    }

    public void run() {
        try {
            AudioFormat format = new AudioFormat(44100, 16, 1, true, true);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format, PACKET_SIZE * 2);

            if (!AudioSystem.isLineSupported(info)) {
                throw new LineUnavailableException();
            }

            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        ByteBuffer buffer = ByteBuffer.allocate(PACKET_SIZE);
        Random random = new Random();

        while (!exitExecution) {
            buffer.clear();
            for (int i = 0; i < PACKET_SIZE / SAMPLE_SIZE; i++) {
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

    public static void main(String[] args) {
        try {
            generatorThread = new StaticNoiseGeneratorThread(new HAMRadio());
            generatorThread.start();
            Thread.sleep(10000);
            generatorThread.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
