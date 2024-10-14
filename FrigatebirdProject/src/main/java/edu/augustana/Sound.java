package edu.augustana;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound extends Morse {

 public void playMorseSymbol(String morseCode) {
  // Loop through each character in the Morse code string
  for (char symbol : morseCode.toCharArray()) {
   if (symbol == '.') {
    playSound("dit.wav"); // Play 'dit' sound for dot
   } else if (symbol == '-') {
    playSound("dah.wav"); // Play 'dah' sound for dash
   }

   try {
    Thread.sleep(300); // Pause between sounds
   } catch (InterruptedException e) {
    Thread.currentThread().interrupt();
   }
  }
 }

 private void playSound(String fileName) {
  try {
   File soundFile = new File(fileName); // Locate sound file
   Clip clip = AudioSystem.getClip();  // Prepare audio clip
   clip.open(AudioSystem.getAudioInputStream(soundFile)); // Load audio
   clip.start(); // Play the sound

   // Wait for the sound to finish playing
   while (clip.isRunning()) {
    Thread.sleep(50); // Poll until playback is complete
   }
   clip.close(); // Close the audio clip after use
  } catch (IOException | UnsupportedAudioFileException |
           LineUnavailableException | InterruptedException e) {
   e.printStackTrace(); // Handle exceptions
  }
 }
}
