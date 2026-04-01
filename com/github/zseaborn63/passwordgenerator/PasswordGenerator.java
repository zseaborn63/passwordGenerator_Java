package com.github.zseaborn63.passwordgenerator;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.zseaborn63.passwordgenerator.util.ConvertToChars;
import com.github.zseaborn63.passwordgenerator.util.GetWords;

public class PasswordGenerator{
    private static char[] lowercaseChars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static char[] separators = new char[]{' ', '_', '-'};
    private static Map<String,String> intReplacementMap = Map.of(
        "e", "3",
        "b", "6",
        "l", "1",
        "o", "0",
        "s", "5",
        "g", "9"
    );
    private static Map<String,String> specialCharReplacementMap = Map.of(
        "a", "@",
        "l", "!",
        "g", "&",
        "s", "$",
        "c", "<",
        "n", "~",
        "t", "+"
    );

    private static char selectRandomArrayItem(char[] selectionArray){
        SecureRandom randomness = new SecureRandom();
        char randomlyChosen = selectionArray[randomness.nextInt(selectionArray.length)];
        return randomlyChosen;
    }

    private static int selectRandomArrayItem(int[] selectionArray){
        SecureRandom randomness = new SecureRandom();
        int randomlyChosen = selectionArray[randomness.nextInt(selectionArray.length)];
        return randomlyChosen;
    }

    private static StringBuilder replacementCharsAvailable(char[] inputChars, Map<String,String>replacementMap){
        StringBuilder availableReplacements = new StringBuilder();
        for (char c: inputChars){
            if (replacementMap.containsKey(String.valueOf(c))){
                availableReplacements.append(c);
            }
        }
        return availableReplacements;
    }

    private static int[] getOccurringIndexes(char[] inputChars, char selectedChar){
        List<Integer> replacementIndexes = new ArrayList<>();
        for (int i = 0; i < inputChars.length; i++){
            if (inputChars[i] == selectedChar){
                replacementIndexes.add(i);
            }
        }
        
        int[] intArray = replacementIndexes.stream().mapToInt(i -> i).toArray();
        return intArray;
    }
    
    private static char[] replaceChar(char[] inputChars, Map<String,String> replacementMap){

        StringBuilder availableReplacements = replacementCharsAvailable(inputChars, replacementMap);

        if (availableReplacements.isEmpty()){
            return inputChars;
        } 

        char[] availableArray = new char[availableReplacements.length()];
        availableReplacements.getChars(0, availableReplacements.length(), availableArray, 0);
        char replacementChar = selectRandomArrayItem(availableArray);

        int[] replacementIndexes = getOccurringIndexes(inputChars, replacementChar);
        int replacementIndex = selectRandomArrayItem(replacementIndexes);
        inputChars[replacementIndex] = replacementMap.get(String.valueOf(replacementChar)).toCharArray()[0];

        return inputChars;
    }

    private static char[] misspell(char[] inputChars){
        char replacementChar = '\u0000';
        
        while (replacementChar == '\0'){
            char chosenToBeReplaced = selectRandomArrayItem(inputChars);
            boolean invalidCharacterChosen = new String(lowercaseChars).indexOf(chosenToBeReplaced) == -1;
            if (!invalidCharacterChosen){
                replacementChar = chosenToBeReplaced;
            }
        }

        char replaceWith = '\u0000';
        while (replaceWith == '\0'){
            char chosenToReplace = selectRandomArrayItem(lowercaseChars);
            if (chosenToReplace != replacementChar){
                replaceWith = chosenToReplace;
            }
        }
        
        int[] replacementCharOccurrences = getOccurringIndexes(inputChars, replacementChar);
        int replacementIndex = selectRandomArrayItem(replacementCharOccurrences);
        inputChars[replacementIndex] = replaceWith;

        return inputChars;
    }

    private static char[] randomCapitalization(char[] inputChars){
        char toCapitalize = '\u0000';
        while (toCapitalize == '\0'){
            char chosenChar = selectRandomArrayItem(inputChars);
            boolean invalidCharacterChosen = new String(lowercaseChars).indexOf(chosenChar) == -1;
            if (!invalidCharacterChosen){
                toCapitalize = chosenChar;
            }
        }

        int[] charOccurrences = getOccurringIndexes(inputChars, toCapitalize);
        int replacementIndex = selectRandomArrayItem(charOccurrences);
        inputChars[replacementIndex] = Character.toUpperCase(toCapitalize);
        return inputChars;
    }

    public static void main(){
        System.out.println("Hello Password Generator!");

        String[] words;
        String password = null;

        while (password == null){

            words = GetWords.main();

            char[] passChars = ConvertToChars.convert(words, separators);

            char[] intReplaceChars = replaceChar(Arrays.copyOf(passChars, passChars.length), intReplacementMap);
            if (Arrays.equals(passChars, intReplaceChars)){
                continue;
            }
            passChars = intReplaceChars;

            char[] specialCharReplacementChars = replaceChar(passChars, specialCharReplacementMap);
            if (Arrays.equals(passChars, specialCharReplacementChars)){
                continue;
            }
            passChars = specialCharReplacementChars;

            passChars = misspell(passChars);
            passChars = randomCapitalization(passChars);
            password = new String(passChars);
        }


        
        password = "tmp";
        System.out.println("The Generated Password:");
        System.out.println("\t" + password);
    }
}
