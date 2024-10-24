package edu.augustana;

import java.util.HashMap;
import java.util.Map;

public class Morse {
    private Map<Character, String> morseMap = new HashMap();
    private Map<String, Character> morseToCharMap = new HashMap<>();
    public Morse() {
        this.morseMap.put('A', ".-");
        this.morseMap.put('B', "-...");
        this.morseMap.put('C', "-.-.");
        this.morseMap.put('D', "-..");
        this.morseMap.put('E', ".");
        this.morseMap.put('F', "..-.");
        this.morseMap.put('G', "--.");
        this.morseMap.put('H', "....");
        this.morseMap.put('I', "..");
        this.morseMap.put('J', ".---");
        this.morseMap.put('K', "-.-");
        this.morseMap.put('L', ".-..");
        this.morseMap.put('M', "--");
        this.morseMap.put('N', "-.");
        this.morseMap.put('O', "---");
        this.morseMap.put('P', ".--.");
        this.morseMap.put('Q', "--.-");
        this.morseMap.put('R', ".-.");
        this.morseMap.put('S', "...");
        this.morseMap.put('T', "-");
        this.morseMap.put('U', "..-");
        this.morseMap.put('V', "...-");
        this.morseMap.put('W', ".--");
        this.morseMap.put('X', "-..-");
        this.morseMap.put('Y', "-.--");
        this.morseMap.put('Z', "--..");
        this.morseMap.put('0', "-----");
        this.morseMap.put('1', ".----");
        this.morseMap.put('2', "..---");
        this.morseMap.put('3', "...--");
        this.morseMap.put('4', "....-");
        this.morseMap.put('5', ".....");
        this.morseMap.put('6', "-....");
        this.morseMap.put('7', "--...");
        this.morseMap.put('8', "---..");
        this.morseMap.put('9', "----.");
        this.morseMap.put('.', ".-.-.-");
        this.morseMap.put(',', "--..--");
        this.morseMap.put('?', "..--..");
        this.morseMap.put('!', "-.-.--");
        this.morseMap.put(':', "---...");
        this.morseMap.put(';', "-.-.-.");
        this.morseMap.put('\'', ".----.");
        this.morseMap.put('"', ".-..-.");
        this.morseMap.put('-', "-....-");
        this.morseMap.put('/', "-..-.");
        this.morseMap.put('(', "-.--.-");
        this.morseMap.put(')', "-.--.-");
        this.morseMap.put('=', "-...-");
        this.morseMap.put('@', ".--.-.");

        for (Map.Entry<Character, String> entry : morseMap.entrySet()) {
            morseToCharMap.put(entry.getValue(), entry.getKey());
        }
    }

    public String toMorse(String message) {
        String result = "";
        message = message.toUpperCase();
        boolean first = true;
        char[] var4 = message.toCharArray();
        int var5 = var4.length;

        for(int i = 0; i < var5; ++i) {
            char c = var4[i];
            if (this.morseMap.containsKey(c)) {
                if (!first) {
                    result = result + " ";
                }

                result = result + (String)this.morseMap.get(c);
                first = false;
            } else if (c == ' ') {
                result = result + "   ";
                first = true;
            }
        }

        return result;
    }



//    private void playMorseSymbol(String morseCode) {
//        System.out.println("Playing Morse for: " + morseCode);
//    }
public String toEnglish(String morseMessage) {
    String result = "";
    String[] words = morseMessage.split("   ");  // Split by three spaces (word separator)

    for (String word : words) {
        String[] chars = word.split(" ");  // Split each word by single space (letter separator)
        for (String morseChar : chars) {
            if (this.morseToCharMap.containsKey(morseChar)) {
                result += this.morseToCharMap.get(morseChar);
            }
        }
        result += " ";  // Add a space between words
    }

    return result.trim();  // Remove trailing space
}
}

