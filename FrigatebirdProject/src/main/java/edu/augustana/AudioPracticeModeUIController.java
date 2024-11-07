package edu.augustana;

import edu.augustana.sound.SoundGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AudioPracticeModeUIController extends Morse {

    private SoundGenerator randomMorseSound = new SoundGenerator();

    @FXML
    public void backToPracticeModeAction(ActionEvent event) throws IOException {
        App.setRoot("PractiseOption");
    }

    @FXML
    void playSound(ActionEvent event) {
        char randomMorseCode = getRandomLetterFromMorseMap();
        String morseCode = morseMap.get(randomMorseCode);
        if (morseCode != null) {
            double defaultDeviation = 0.0;
            randomMorseSound.playMorseSymbol(morseCode, defaultDeviation);
        }
        System.out.println("playSound button clicked!");
    }

    private char getRandomLetterFromMorseMap() {
        List<Character> letters = new ArrayList<>(morseMap.keySet());
        Random random = new Random();
        return letters.get(random.nextInt(letters.size()));
    }

    @FXML
    void initialize() {

    }
}
