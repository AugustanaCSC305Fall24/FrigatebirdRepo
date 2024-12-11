package edu.augustana;

import java.util.HashMap;
import java.util.Map;

public class Morse {
    public static Map<Character, String> morseMap = new HashMap();
    public Map<String, Character> morseToCharMap = new HashMap<>();
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
        //this.morseMap.put('0', "-----");
        //this.morseMap.put('1', ".----");
        //this.morseMap.put('2', "..---");
        //this.morseMap.put('3', "...--");
        //this.morseMap.put('4', "....-");
        //this.morseMap.put('5', ".....");
        //this.morseMap.put('6', "-....");
        //this.morseMap.put('7', "--...");
        //this.morseMap.put('8', "---..");
        //this.morseMap.put('9', "----.");
        //this.morseMap.put('.', ".-.-.-");
        //this.morseMap.put(',', "--..--");
        //this.morseMap.put('?', "..--..");
        //this.morseMap.put('!', "-.-.--");
       // this.morseMap.put(':', "---...");
        //this.morseMap.put(';', "-.-.-.");
        //this.morseMap.put('\'', ".----.");
        //this.morseMap.put('"', ".-..-.");
        //this.morseMap.put('-', "-....-");
       // this.morseMap.put('/', "-..-.");
        //this.morseMap.put('(', "-.--.-");
        //this.morseMap.put(')', "-.--.-");
        //this.morseMap.put('=', "-...-");
        //this.morseMap.put('@', ".--.-.");

        for (Map.Entry<Character, String> entry : morseMap.entrySet()) {
            morseToCharMap.put(entry.getValue(), entry.getKey());
        }
    }
    public static Map<Character, String> getMorseMap() {
        return morseMap;
    }

    public String toMorse(String message) {
        StringBuilder result = new StringBuilder();
        message = message.toUpperCase();
        boolean newWord = true;

        for (char c : message.toCharArray()) {
            if (c == ' ') {
                result.append("   ");
                newWord = true;
            } else {
                String morseCode = morseMap.get(c);
                if (morseCode != null) {
                    if (!newWord) {
                        result.append(" ");
                    }
                    result.append(morseCode);
                    newWord = false;
                }

            }
        }
        return result.toString();
    }




public String toEnglish(String morseMessage) {
    String result = "";
    String[] words = morseMessage.split("   ");
    for (String word : words) {
        String[] chars = word.split(" ");
        for (String morseChar : chars) {
            if (this.morseToCharMap.containsKey(morseChar)) {
                result += this.morseToCharMap.get(morseChar);
            }
        }
        result += " ";
    }

    return result.trim();
}

}

