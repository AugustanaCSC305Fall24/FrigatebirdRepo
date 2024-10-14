package edu.augustana;

import java.util.HashMap;
import java.util.Map;

public class Morse {
    private Map<Character, String> morseMap = new HashMap();

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
    }

    public String toMorse(String message) {
        String result = "";
        message = message.toUpperCase();
        boolean first = true;
        char[] var4 = message.toCharArray();
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            char c = var4[var6];
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

}

