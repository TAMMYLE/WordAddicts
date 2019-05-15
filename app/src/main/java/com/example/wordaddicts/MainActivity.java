package com.example.wordaddicts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
//<<<<<<< Updated upstream

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //declare variable for score . <Tammy Le - 15/4/2019>
    int score;

    private int coin = 500; //added a coin integer <Phong 16/4/2019>
    private EditText textInput;
    private Button checkButton, resetButton, hintButton;
    private TextView result, hint;
    private TextView shuffedLetters;
    private TextView coinView, mainCoin; //added coinView <Phong - 16/4/2019>
    private ImageView highscore;

    LinearLayout mainShop;


    private String guessWord;
    private String givenWord;
    private String shuffedWord;

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

        mainShop = (LinearLayout) findViewById(R.id.mainShop);


        mainCoin = (TextView) findViewById(R.id.mainCoin);
        coin = retrieveCoin();// retrieve coin from the sharedPreference
        mainCoin.setText("" + coin);

        highscore = (ImageView) findViewById(R.id.highscore);
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
                /*
                Added by Tammy Le, 15/4/2019
                if the answer is correct, score plus ten
                set the Score field with extra score
                clear the input
                renew the given word
                the if condition is triggered when user guess the right word (guessWord == givenWord

                 */
                if(availableWords.compareWords(givenWord, guessWord))
                {
                    score += 10;
                    result.setText("Score: " + score);
                    textInput.setText("");
                    renewWord();
//
                }
                else
                {
                    result.setText("Score" + score);

                    //added by Tammy Le
                    //When player gets a wrong word -> input will be vibrated
                    //Inspired by Priya
                    Animation vibrate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.vibrate);
                    textInput.startAnimation(vibrate);
//
                }




            }
        });
        //this function reset the game, give new shuffled word (created by Phong 10/4/2019)
        /*
        resetButton
        set the click listener for resetButton, everytime it's clicked a new word is chosen and shuffled
        textInput is set to empty and score is set to 0
        hint is clear
         */
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                givenWord = availableWords.randomWord();
                shuffedWord = availableWords.shuffleWord(givenWord);
                shuffedLetters.setText(shuffedWord);
                result.setText("Score: 0");
                textInput.setText("");
                hint.setText("");

            }
        });
        /*
        set click listener for highscore
        the current score will be put in the SharedPreference
        a new intent is triggered, bring user to HighScoreActivity
         */
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

        /*
        set click listener for shop (icon or the amount of coin left)
        new intent is created taking the user to ShopActivity
         */
        mainShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShopActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //hintButtun function added <Phong 16/4/2019>
        /*
        hintButton gives hint when possible
        there are two situations when hint is not given
        1. all hints have been given
        2. user doesn't have enough coins to buy a hint
        every hint revealed, 100 coin is deducted
        new number of coin will be put in the SharedPreference through editCoin() method

         */
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pop up a message if the hints is already fully revealed
                if(!(givenWord.length() > hint.length()))
                {
                    Toast.makeText(getApplicationContext(), "You have got all the hints", Toast.LENGTH_SHORT).show();
                }
                //pop up a message if player doesn't have enough coins
                if(coin < 100)
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
                editCoin(coin);
                mainCoin.setText("" + coin);
            }
        });

    }
    //Added by Tammy Le, 15/4/2019
    //Function to renew the word that player has to guess
    /*
    renewWord()
    get a new random word and shuffle all the letters to display
    parameters: no parameters needed
    return: void
     */
    public void renewWord() {
        givenWord = availableWords.randomWord();
        shuffedWord = availableWords.shuffleWord(givenWord);
        shuffedLetters.setText(shuffedWord);
    }

    /*
    editCoin()
    take the current amount of coin and put it into the SharedPreferences
    parameters: current number of coin in Integer form
    return: void, the function just edit the coin in the SharedPreference, doesn't return anything
     */
    public void editCoin(int coin)
    {
        SharedPreferences preferences = getSharedPreferences("COIN_PREFS", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("coin", coin);
        editor.apply();
    }
    //retrieve the value of coin from SharedPreference
    /*
    retrieveCoin()
    retrieve the amount of coin currently holding in the SharedPreference
    parameters: no parameters needed
    returns: integer contains the number of coins.
     */
    public int retrieveCoin()
    {
        int coin1;
        SharedPreferences preferences = getSharedPreferences("COIN_PREFS", 0);

        coin1 = preferences.getInt("coin", 0);
        return coin1;
    }
}