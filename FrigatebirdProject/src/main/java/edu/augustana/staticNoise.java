package edu.augustana;

import javax.sound.sampled.*;
import java.nio.ByteBuffer;
import java.util.Random;

// courtesy of https://gist.github.com/m5mat/f622df23a49586337009c60a9966964c

public class staticNoise extends Thread {
    private static staticNoise generatorThread;

    final static public int SAMPLE_SIZE = 2;
    final static public int PACKET_SIZE = 5000;

    SourceDataLine line;
    public boolean exitExecution = false;

    public static void main(String[] args) {
        try {
            generatorThread = new staticNoise();
            generatorThread.start();
            Thread.sleep(10000);
            generatorThread.exit();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void run() {

        try {
            AudioFormat format = new AudioFormat(44100, 16, 1, true, true);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format, PACKET_SIZE * 2);

            if (!AudioSystem.isLineSupported(info)) {
                throw new LineUnavailableException();
            }

            line = (SourceDataLine)AudioSystem.getLine(info);
            line.open(format);
            line.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        ByteBuffer buffer = ByteBuffer.allocate(PACKET_SIZE);

        Random random = new Random();
        while (exitExecution == false) {
            buffer.clear();
            for (int i=0; i < PACKET_SIZE /SAMPLE_SIZE; i++) {
                buffer.putShort((short) (random.nextGaussian() * Short.MAX_VALUE));
            }
            line.write(buffer.array(), 0, buffer.position());
        }

        line.drain();
        line.close();
    }

    public void exit() {
        exitExecution =true;
    }
}
