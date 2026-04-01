package com.github.zseaborn63.passwordgenerator.util;

import java.security.SecureRandom;

public class ConvertToChars {
    public static char[] convert(String[] wordArray, char[] allSeperators){
        SecureRandom sepRandom = new SecureRandom();
        int sepIndex = sepRandom.nextInt(allSeperators.length);
        char sep = allSeperators[sepIndex];

        // Split array of words into correct array of characters
        int passLength = 0;
        for (String word: wordArray){
            passLength += word.length();
        }
        passLength += 2; // Account for added seperators

        char[] passCharacters = new char[passLength];
        int currentIndex = 0;
        for (String word: wordArray){
            char[] wordCharacters = word.toLowerCase().toCharArray();
            for (char c: wordCharacters){
                passCharacters[currentIndex] = c;
                currentIndex ++;
            }
            if (currentIndex < passLength){
                passCharacters[currentIndex] = sep;
                currentIndex ++;
            }
        }

        return passCharacters;
    }
}
