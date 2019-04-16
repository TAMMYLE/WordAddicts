package com.example.wordaddicts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//<<<<<<< Updated upstream

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //declare variable for score . <Tammy Le - 15/4/2019>
    int score;

    public int coin = 500; //added a coin integer <Phong 16/4/2019>
    EditText textInput;
    Button checkButton, resetButton, hintButton;
    TextView result, hint;
    TextView shuffedLetters;
    TextView coinView; //added coinView <Phong - 16/4/2019>
    Button highscore;

    String guessWord;
    String givenWord;
    String shuffedWord;

    LettersStorage availableWords = new LettersStorage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        get all buttons, text input from activity_main.xml and assign their value to above variables
        textInput = (EditText) findViewById(R.id.editText);
        checkButton = (Button) findViewById(R.id.check);
        resetButton = (Button) findViewById(R.id.reset);
        hintButton = (Button) findViewById(R.id.hint);
        result = (TextView) findViewById(R.id.result);
        hint = (TextView) findViewById(R.id.hintField);
        shuffedLetters = (TextView) findViewById(R.id.textView2);

        coinView = (TextView) findViewById(R.id.coinView);
        coinView.setText("Coin: " + coin);//display coin player has

        highscore = (Button) findViewById(R.id.highscore);
        // get a random word and shuffle the letters of that word
        givenWord = availableWords.randomWord();
        shuffedWord = availableWords.shuffleWord(givenWord);
        shuffedLetters.setText(shuffedWord);// display that shuffeld word

        //this function checks if the word match when ever the user click check button (created by Phong 9/4/2019)
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guessWord = textInput.getText().toString();
                //display a message according to the result
                if(availableWords.compareWords(givenWord, guessWord))
                {                                       //Added by Tammy Le, 15/4/2019
                    score += 10;                        //if the answer is correct, score plus ten
                    result.setText("Score: " + score);  //set the Score field with extra score
                    textInput.setText("");              //clear the input
                    renewWord();                        //renew the given word
//
                }
                else
                {
                    result.setText("Score" + score);
                    Toast.makeText(getApplicationContext(), "Oops! Try again !", Toast.LENGTH_SHORT).show();
//
                }
            }
        });

        //this function reset the game, give new shuffled word (created by Phong 10/4/2019)
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                givenWord = availableWords.randomWord();
                shuffedWord = availableWords.shuffleWord(givenWord);
                shuffedLetters.setText(shuffedWord);
                result.setText("Score: 0");             //click reset -> the Score back to 0 - added by Tammy Le
                textInput.setText("");
                hint.setText("");                       //set hint to empty

            }
        });

        highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("latestscore", score);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), HighScoreActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //hintButtun function added <Phong 16/4/2019>
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pop up a message if the hints is already fully revealed
                if(!(givenWord.length() > hint.length()))
                {
                    Toast.makeText(getApplicationContext(), "You have got all the hints", Toast.LENGTH_SHORT).show();
                }
                //pop up a message if player doesn't have enough coins
                if(coin <= 100)
                {
                    Toast.makeText(getApplicationContext(), "You don't have enough coin to buy hint", Toast.LENGTH_SHORT).show();
                }

                availableWords.giveHint(givenWord, hint, coin);

                //minus the coins needed to buy hint
                coin = coin - 100;
                if(coin <= 0)
                {
                    coin = 0;
                }
                coinView.setText("Coin: " + coin);
            }
        });

//=======
//import android.widget.Toast;
//
//public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//
//    private TextView shuffleWord;
//    private EditText enterWord;
//    private Button check;
//    private Button newgame;
//    private String wordToFind;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        shuffleWord = (TextView) findViewById(R.id.shuffleWord);
//        enterWord = (EditText) findViewById(R.id.enterWord);
//        check = (Button) findViewById(R.id.check);
//        check.setOnClickListener(this);
//        newgame = (Button) findViewById(R.id.newgame);
//        newgame.setOnClickListener(this);
//
//        newgame();
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (v == check){
//            check();
//        } else if (v == newgame){
//            newgame();
//        }
//    }
//
//    private void check() {
//        String myWord = enterWord.getText().toString();
//
//        if (wordToFind.equals(myWord)) {
//            Toast.makeText(this, "Congratulations ! Your word is " + wordToFind, Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Oops! Try again !", Toast.LENGTH_SHORT).show();
//        }
//    }

//    private void newgame() {
//        wordToFind = Anagram.randomWord();
//        String shuffledWord = Anagram.shuffleWord(wordToFind);
//>>>>>>> Stashed changes
//
    }
    //Added by Tammy Le, 15/4/2019
    //Function to renew the word that player has to guess
    public void renewWord() {
        givenWord = availableWords.randomWord();
        shuffedWord = availableWords.shuffleWord(givenWord);
        shuffedLetters.setText(shuffedWord);
    }
}
