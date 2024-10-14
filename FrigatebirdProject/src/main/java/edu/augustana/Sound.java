package edu.augustana;
//
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
//import java.io.File;
//import java.io.IOException;
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.UnsupportedAudioFileException;
//
 public class Sound extends Morse {
//
//    public void playMorseSymbol(String morseCode){
//        for (char symbol : morseCode.toCharArray()) {
//            if (symbol == '.') {
//                playSound("dit.wav");
//            } else if (symbol == '-') {
//                playSound("dah.wav");
//            }
//
//            try {
//                Thread.sleep(300); // Pause between sounds
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }
//    }
//
//    private void playSound(String fileName) {
//        try {
//            File soundFile = new File(fileName);
//            Clip clip = AudioSystem.getClip();
//            clip.open(AudioSystem.getAudioInputStream(soundFile));
//            clip.start();
//
//            // Wait for the sound to finish playing
//            while (clip.isRunning()) {
//                Thread.sleep(50);
//            }
//            clip.close();
//        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//
//
}
