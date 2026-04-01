package com.github.zseaborn63.passwordgenerator;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Map;

import com.github.zseaborn63.passwordgenerator.util.ConvertToChars;
import com.github.zseaborn63.passwordgenerator.util.GetWords;

public class PasswordGenerator{
    private static String[] separators = new String[]{" ", "_", "-"};
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
    
    private static void replaceChar(char[] args){
        System.out.println("To be completed");

        SecureRandom replaceCharRandom = new SecureRandom();
    }

    private static void misspell(char[] args){
        System.out.println("To be completed");

        SecureRandom misspellRandom = new SecureRandom();
    }

    public static void main(){
        System.out.println("Hello Password Generator!");

        String[] words;
        String password;

        words = GetWords.main();
        String[] wordsCopy = Arrays.copyOf(words, words.length);

        char[] passChars = ConvertToChars.convert(words, separators);
        

        password = "tmp";
        System.out.println("The Generated Password:");
        System.out.println("\t" + password);
    }
}
