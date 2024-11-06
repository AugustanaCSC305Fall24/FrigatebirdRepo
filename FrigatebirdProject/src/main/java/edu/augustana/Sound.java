package edu.augustana;

import javax.sound.sampled.*;
import java.util.Random;

public class Sound extends Morse  {

 private static final int SAMPLE_RATE = 44100;  // Standard sample rate for audio
 private double volume = 1.0; //Volume (range 0.0 to 1.0)
 private volatile boolean isPlaying = true;
 private static final double WPM = 18;  // Words per minute
 private static final double UNIT_LENGTH = 60.0 / (WPM * 50);  // Unit length in seconds

 private SourceDataLine line;
 private Thread playbackThread;

 public void setVolume(double volume){
  this.volume = volume / 100.0; //Convert slider value (0-100) to range (0.0-1.0)
 }


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
   buffer[i] = (byte) (Math.sin(angle) * 127 * volume);  // Generate sine wave
  }
  return buffer;
 }

 // Play Morse symbols with tones
 public void playMorseSymbol(String morseCode) {
  final int DOT_DURATION = 100;
  final int DASH_Duration = 3 * DOT_DURATION;
  final long UNIT_MILLIS = (long) (UNIT_LENGTH * 1000);

  for (int i = 0; i< morseCode.length(); i++){
   char symbol = morseCode.charAt(i);

   if (symbol == '.'){
    playTone(600, DOT_DURATION);
   } else if (symbol == '-'){
    playTone(600, DASH_Duration);
   }

   try{
    if ( i < morseCode.length() - 1){
     char nextSymbol = morseCode.charAt(i + 1);

     if(nextSymbol == ' '){
      if(i + 2 < morseCode.length() && morseCode.charAt(i + 2) != ' '){
       Thread.sleep(3* UNIT_MILLIS);
      } else {
       Thread.sleep(7 * UNIT_MILLIS);
      }
     } else {
      Thread.sleep(UNIT_MILLIS);
     }
    }
   } catch (InterruptedException e) {
       Thread.currentThread().interrupt();
       break;
   }
  }

//  for (char symbol : morseCode.toCharArray()) {
//
//    if (symbol == '.') {
//     playTone(600, 100);
//    } else if (symbol == '-') {
//     playTone(600, 300);
//    }
//
//    try {
//     if (isPlaying) {
//      Thread.sleep(100);  // Pause between symbols
//     }
//    } catch (InterruptedException e) {
//     Thread.currentThread().interrupt();
//     break;
//    }
//   }
 }

}
