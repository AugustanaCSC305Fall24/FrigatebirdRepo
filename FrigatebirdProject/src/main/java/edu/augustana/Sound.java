package edu.augustana;

import javax.sound.sampled.*;

public class Sound extends Morse {

 private static final int SAMPLE_RATE = 44100;  // Standard sample rate for audio

 // Method to play a tone at a specific frequency and duration
 private void playTone(double frequency, int durationMs) {
  try {
   byte[] buffer = generateTone(frequency, durationMs);  // Generate tone data

   // Prepare audio format and line for playback
   AudioFormat format = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
   SourceDataLine line = AudioSystem.getSourceDataLine(format);
   line.open(format);
   line.start();

   // Write the generated tone data to the audio line
   line.write(buffer, 0, buffer.length);
   line.drain();
   line.close();
  } catch (LineUnavailableException e) {
   e.printStackTrace();
  }
 }

 // Method to generate the raw tone data
 private byte[] generateTone(double frequency, int durationMs) {
  int length = (int) (SAMPLE_RATE * (durationMs / 1000.0));
  byte[] buffer = new byte[length];

  for (int i = 0; i < length; i++) {
   double angle = 2.0 * Math.PI * i * frequency / SAMPLE_RATE;
   buffer[i] = (byte) (Math.sin(angle) * 127);  // Generate sine wave
  }
  return buffer;
 }

 // Play Morse symbols with tones
 public void playMorseSymbol(String morseCode) {
  for (char symbol : morseCode.toCharArray()) {
   if (symbol == '.') {
    playTone(600, 100);  // Play a 600 Hz tone for 100ms (dot)
   } else if (symbol == '-') {
    playTone(600, 300);  // Play a 600 Hz tone for 300ms (dash)
   }

   try {
    Thread.sleep(100);  // Pause between symbols
   } catch (InterruptedException e) {
    Thread.currentThread().interrupt();  // Restore interrupted status
   }
  }
 }
}
