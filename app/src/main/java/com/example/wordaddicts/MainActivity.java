package com.example.wordaddicts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

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

    private String shuffleWord(String word) {
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
     compareWords (edited by Phong 8/4/2019)
     function compare two given words

     parameters
     two words that need to be compared

     returns a boolean value indicating if two given words are match
     */
    public Boolean compareWords(String word1, String word2){
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();
        char a[] = word1.toCharArray();
        char b[] = word2.toCharArray();

        boolean match = true;
        if(a.length != b.length || a.length == 0 || b.length == 0)
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



    EditText textInput;
    Button checkButton, resetButton;
    TextView result;
    TextView shuffedLetters;

    String guessWord;
    String givenWord;
    String shuffedWord;

    LettersStorage availableWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        get all buttons, text input from activity_main.xml and assign their value to above variables
        textInput = (EditText) findViewById(R.id.editText);
        checkButton = (Button) findViewById(R.id.check);
        resetButton = (Button) findViewById(R.id.reset);
        result = (TextView) findViewById(R.id.result);
        shuffedLetters = (TextView) findViewById(R.id.textView2);
        // get a random word and shuffle the letters of that word
        givenWord = randomWord();
        shuffedWord = shuffleWord(givenWord);
        shuffedLetters.setText(shuffedWord);// display that shuffeld word

        //this function checks if the word match when ever the user click check button (created by Phong 9/4/2019)
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guessWord = textInput.getText().toString();
                //display a message according to the result
                if(compareWords(givenWord, guessWord))
                {
                    result.setText("you got it!!!");
                }
                else
                {
                    result.setText("Try another word");
                }
            }
        });

        //this function reset the game, give new shuffled word (created by Phong 10/4/2019)
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                givenWord = randomWord();
                shuffedWord = shuffleWord(givenWord);
                shuffedLetters.setText(shuffedWord);
                result.setText("Result");
                textInput.setText("");

            }
        });

    }
}
