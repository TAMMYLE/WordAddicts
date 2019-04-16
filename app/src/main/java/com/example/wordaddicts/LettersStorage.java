package com.example.wordaddicts;

import java.util.Random;

/**
 * Created by Tammy Le 8/4/2019
 */


public class LettersStorage {

    public static final Random RANDOM = new Random();
    public static final String[] WORDS = {"LAPTOP", "SUNDAY", "GAME", "MARVELLOUS", "WHITE", "UPDATE", "FLOWER", "HOTEL", "TEACHER", "STRUCTURE", "YOGURT", "WONDER", "HOPE",
            "BEAUTIFUL", "UNUSUAL", "JUICE", "ORANGE", "STRAWBERRY", "CUP", "POP", "FLOP", "MOP", "UNIQUE"};


    /**
     randomWord:

     function that choose a random word from an array of words above

     Returns:
     return the pseudorandom word

     */
    public static String randomWord(){
        return WORDS[RANDOM.nextInt(WORDS.length)];
    }

    /**
     shuffleWord:

     function that shuffles the chosen word

     Parameters:

     word - a string storing a word

     Returns:
     if word is not empty and the length is not space => converting word to a new character array
     then, shuffling the word

     */

    public static String shuffleWord(String word) {
        if (word != null && !"".equals(word)){
            char a[] = word.toCharArray();

            for (int i = 0; i < a.length; i++){
                int j = RANDOM.nextInt(a.length);
                char tmp = a[i];
                a[i]= a[j];
                a[j] = tmp;
            }

            return new String(a);
        }

        return word;
    }

    /**
     compareWords (added by Phong 8/4/2019)
     function compare two given words

     parameters
     two words that need to be compared

     returns a boolean value indicating if two given words are match
     */
    public static Boolean compareWords(String word1, String word2){
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();
        char a[] = word1.toCharArray();
        char b[] = word2.toCharArray();

        boolean match = true;
        if(a.length != b.length || a.length == 0 || b.length == 0 || a != null || a != null)
        {
            match = false;
            return match;
        }

        for (int i = 0; i < a.length; i++)
        {
            if(a[i] != b[i])
                match = false;

        }
        return match;
    }

}
