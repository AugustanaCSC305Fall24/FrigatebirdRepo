package edu.augustana;

import javax.sound.sampled.*;
import java.util.Random;

public class staticNoise {
    private void playStaticNoise() {
        AudioFormat audioFormat = new AudioFormat(44100, 16, 1, true, false);
        byte[] buffer = new byte[44100 * 2];
        Random random = new Random();
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte) random.nextInt(256);
        }
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(audioFormat);
            line.start();
            line.write(buffer, 0, buffer.length);
            line.drain();
            line.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
