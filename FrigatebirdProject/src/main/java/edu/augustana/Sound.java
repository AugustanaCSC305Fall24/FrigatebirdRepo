package edu.augustana;

import javax.sound.sampled.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound extends Morse {

 private static final int SAMPLE_RATE = 44100;  // Standard sample rate for audio
 private volatile boolean isPlaying = false;    // Flag to control playback
 private SourceDataLine line;                   // Audio line reference


 // Method to play a tone at a specific frequency and duration
 private void playTone(double frequency, int durationMs) throws InterruptedException {
  byte[] buffer = generateTone(frequency, durationMs);  // Generate tone data
  AudioFormat format = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
  try {


   // Prepare audio format and line for playback

   //SourceDataLine line = AudioSystem.getSourceDataLine(format);
   line = AudioSystem.getSourceDataLine(format);
   line.open(format);
   line.start();



   // Write the generated tone data to the audio line
   line.write(buffer, 0, buffer.length);
   line.drain();
   //line.close();

  } catch (LineUnavailableException e) {
   e.printStackTrace();
  } finally {
   if(line != null){
    line.close();
   }
  }

  if (Thread.currentThread().isInterrupted()){
   throw new InterruptedException();
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
 public void playMorseSymbol(String morseCode) throws InterruptedException {
  isPlaying = true;

  for (char symbol : morseCode.toCharArray()) {
   if (!isPlaying) break;

   if (symbol == '.') {
    playTone(600, 100);  // Play a 600 Hz tone for 100ms (dot)
   } else if (symbol == '-') {
    playTone(600, 300);  // Play a 600 Hz tone for 300ms (dash)
   }

   //try {
   Thread.sleep(100);  // Pause between symbols
   //} catch (InterruptedException e) {
    //Thread.currentThread().interrupt();  // Restore interrupted status
   }
  }


// private void playSound(String fileName) {
//  try {
//   File soundFile = new File(fileName); // Locate sound file
//   if (!soundFile.exists()) {
//    System.err.println("Error: Sound file not found - " + fileName);
//    return;
//   }
//   Clip clip = AudioSystem.getClip();  // Prepare audio clip
//   clip.open(AudioSystem.getAudioInputStream(soundFile)); // Load audio
//   clip.start(); // Play the sound
//
//   // Wait for the sound to finish playing
//   while (clip.isRunning()) {
//    Thread.sleep(50); // Poll until playback is complete
//   }
//   clip.close(); // Close the audio clip after use
//  } catch (IOException | UnsupportedAudioFileException |
//           LineUnavailableException | InterruptedException e) {
//   e.printStackTrace(); // Handle exceptions
//  }
// }

 public void stopPlayBack(){
  isPlaying = false;
  if(line != null && line.isOpen()){
   line.stop();
   line.close();
  }
 }

}

