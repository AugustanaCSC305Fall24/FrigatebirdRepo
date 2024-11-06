package edu.augustana;

import javax.sound.sampled.*;
import java.util.Random;

public class Sound extends Morse  {

 private static final int SAMPLE_RATE = 44100;
 private double volume = 1.0;
 private volatile boolean isPlaying = true;

 private SourceDataLine line;
 private Thread playbackThread;

 public void setVolume(double volume){
  this.volume = volume / 100.0;
 }

 public void stop(){
  isPlaying = false;
  if (line != null && line.isOpen()) {
   line.stop();
   line.flush();
   line.close();
  }
  if (playbackThread != null && playbackThread.isAlive()) {
   playbackThread.interrupt();  // Interrupt the thread
  }
 }

 private void playTone(double frequency, int durationMs) {
  if(!isPlaying) return;

  try {
   byte[] buffer = generateTone(frequency, durationMs);  // Generate tone data


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


 private byte[] generateTone(double frequency, int durationMs) {
  int length = (int) (SAMPLE_RATE * (durationMs / 1000.0));
  byte[] buffer = new byte[length];

  for (int i = 0; i < length; i++) {
   double angle = 2.0 * Math.PI * i * frequency / SAMPLE_RATE;
   buffer[i] = (byte) (Math.sin(angle) * 127 * volume);  // Generate sine wave
  }
  return buffer;
 }


 public void playMorseSymbol(String morseCode) {
  isPlaying = true; // reset isPlaying at the start
  
  playbackThread = new Thread(() -> {
   for (char symbol : morseCode.toCharArray()) {
    if (!isPlaying) break;  // Stop if the flag is set

    if (symbol == '.') {
     playTone(600, 100);
    } else if (symbol == '-') {
     playTone(600, 300);
    }

    try {
     if (isPlaying) {
      Thread.sleep(100);
     }
    } catch (InterruptedException e) {
     Thread.currentThread().interrupt();
     break;
    }
   }
  });
  playbackThread.start();
 }

}
