package edu.augustana;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class VisualMorsePractice {

        private final Map<Character, String> morseMap;

        public VisualMorsePractice(Map<Character, String> morseMap) {
            this.morseMap = morseMap;
        }

        public char getRandomLetter() {
            List<Character> letters = new ArrayList<>(morseMap.keySet());
            Random random = new Random();
            return letters.get(random.nextInt(letters.size()));
        }

        public String getMorseCode(char letter) {
            return morseMap.get(letter);
        }

        public boolean isMorseCodeCorrect(char letter, String input) {
            String correctMorse = getMorseCode(letter);
            return correctMorse != null && correctMorse.equals(input.trim());
        }
    }


