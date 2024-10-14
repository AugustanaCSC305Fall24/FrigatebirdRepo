package edu.augustana;

import javax.sound.sampled.*;

public class Sound extends Morse {

 private static final int SAMPLE_RATE = 44100;  // Standard audio sample rate
 private volatile boolean isPlaying = false;    // Flag to control playback
 private SourceDataLine line;                   // Audio line reference

 // Method to play a tone at a specific frequency and duration
 public void playTone(double frequency, int durationMs) {
  try {
   byte[] buffer = generateTone(frequency, durationMs); // Generate tone data
   AudioFormat format = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
   line = AudioSystem.getSourceDataLine(format);  // Initialize the audio line
   line.open(format);
   line.start();
   isPlaying = true;

   // Write the generated tone data to the audio line
   line.write(buffer, 0, buffer.length);
   line.drain();
   line.close();
   isPlaying = false;
  } catch (LineUnavailableException e) {
   e.printStackTrace();
  }
 }

 // Generate raw sine wave tone data
 private byte[] generateTone(double frequency, int durationMs) {
  int length = (int) (SAMPLE_RATE * (durationMs / 1000.0));
  byte[] buffer = new byte[length];

  for (int i = 0; i < length; i++) {
   double angle = 2.0 * Math.PI * i * frequency / SAMPLE_RATE;
   buffer[i] = (byte) (Math.sin(angle) * 127);
  }
  return buffer;
 }

 // Play Morse symbols using tones
 public void playMorseSymbol(String morseCode) {
  for (char symbol : morseCode.toCharArray()) {
   if (!isPlaying) break;  // Stop playback if interrupted

   if (symbol == '.') {
    playTone(600, 100);  // Play dot tone (100ms)
   } else if (symbol == '-') {
    playTone(600, 300);  // Play dash tone (300ms)
   }

   try {
    Thread.sleep(100);  // Pause between symbols
   } catch (InterruptedException e) {
    Thread.currentThread().interrupt();
    break;
   }
  }
 }

 // Stop the audio line if it is playing
// public void stopPlayback() {
//  if (line != null && line.isOpen()) {
//   line.stop();
//   line.close();
//  }
//  isPlaying = false;
// }
}
