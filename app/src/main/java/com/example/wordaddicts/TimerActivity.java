/*****  Created by Tammy Le, 16/4/2019        *******/
package com.example.wordaddicts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class TimerActivity extends AppCompatActivity{


    ProgressBar mProgressBar, mProgressBar1;
    int score;

    private ImageView highScoreTimer;

    private Button buttonStartTime;
    private Button buttonCheck;
    private Button buttonBuyTime;

    private EditText textViewInput;
    private TextView textViewShuffle, scoreTextView, timerCoin;

    private LinearLayout timerShop;

    private String guessWord;          //what user put in
    private String givenWord;          // what word the game wants user to guess
    private String shuffedWord;        // the word the game gives but already shuffled

    private LettersStorage availableWords = new LettersStorage();

    private MyCountDown countDownTimer;

    //declare the total time of the timer counting in milliseconds
    private long totalTimeCountInMilliseconds;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        //initialize views

        buttonStartTime = (Button) findViewById(R.id.button_timerview_start);
        buttonCheck = (Button) findViewById(R.id.buttonCheck);
        buttonBuyTime = (Button) findViewById(R.id.buyTimeButton);
        buttonBuyTime.setVisibility(View.INVISIBLE);// hide the button when the game has not started



        highScoreTimer = (ImageView) findViewById(R.id.highscoreTimer);




        textViewInput = (EditText)
                findViewById(R.id.textViewInput);
        textViewInput.setVisibility(View.INVISIBLE);

        textViewShuffle = (TextView)
                findViewById(R.id.shuffledWord)    ;
        textViewShuffle.setVisibility(View.INVISIBLE);//hiding the word that user needs to guess

        scoreTextView = (TextView) findViewById(R.id.scoreTextView); // take the scoreTextView from the layout
        scoreTextView.setText("Score : 0");// set the initial score to 0;
        scoreTextView.setVisibility(View.INVISIBLE);// set the scoreTextView to be invisible until the game starts
        score = 0;//set the score to 0;

        timerCoin = (TextView) findViewById(R.id.timerCoin);
        timerCoin.setText("" + MainActivity.coin);
//        timerCoin.setVisibility(View.INVISIBLE);

        timerShop = (LinearLayout) findViewById(R.id.timerShop);




        //initialize the click listeners
        // If id is a Start button
        buttonStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call setTimer function
                setTimer();
                score = 0;
                scoreTextView.setText("Score: " + score);
                //set the visibility of all needed views
                buttonStartTime.setVisibility(View.INVISIBLE);
                buttonCheck.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
                textViewShuffle.setVisibility(View.VISIBLE);//show the word that user needs to guess
                scoreTextView.setVisibility(View.VISIBLE);// show the score when the game start
                timerCoin.setVisibility(View.VISIBLE);//show the coin when the game starts
                buttonBuyTime.setVisibility(View.VISIBLE);// show the buyTime button when the game starts
                textViewInput.setVisibility(View.VISIBLE);//show the textInput

                //call startTimer function
                startTimer();

                //set the visibility of the main progress bar
                mProgressBar1.setVisibility(View.VISIBLE);



            }
        });

        //if ID is a Check button
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //retrieving user's input --> convert to string
                guessWord = textViewInput.getText().toString();

                //added by Tammy, 3/5/2019
                //Hide the soft keyboard when user pressed
                //textViewInput.onEditorAction(EditorInfo.IME_ACTION_DONE);
                closeKeyboard();

                textViewInput.setText("");//clear the input

                //display a message according to the result
                if(availableWords.compareWords(givenWord, guessWord))
                {

                    if(score >=400)
                    {
                        renewWordLevel3();
                    }

                    else if(score >= 200)
                    {
                        renewWordLevel2();
                    }
                    else
                    {
                        renewWord();
                    }

                    score += 10;//add the score
                    scoreTextView.setText("Score: " + score);//increase the score when user insert a right answer

                    plusTime();


                }
                else
                {   //if the input is wrong --> vibration will be applied for textviewInput, indicates the input is wrong.
                    textViewInput.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.vibrate ));
                }
//
            }
        });
        buttonCheck.setVisibility(View.INVISIBLE);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar_timerview);
        mProgressBar1 = (ProgressBar) findViewById(R.id.progressbar1_timerview);

        ///?Buy time button
        buttonBuyTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.coin >= 100)
                {
                    MainActivity.coin = MainActivity.coin - 100;

                    plusTime();
                    timerCoin.setText("" + MainActivity.coin);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You don't have enough coin to buy time", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //navigate to the highScore activity when click
        highScoreTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TimerHighScoreActivity.class);
                startActivity(intent);
                finish();
            }
        });

        timerShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShopActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }



    /**
     * function renewWord: is used to shuffle new word based on a given word on LettersStorage class
     * */
    private void renewWord() {
        givenWord = availableWords.randomWordLevel1();
        shuffedWord = availableWords.shuffleWord(givenWord);
        textViewShuffle.setText(shuffedWord);
    }
    //different renewWord function to change to more complex word when user hitting a curtain score
    private void renewWordLevel2()
    {
        givenWord = availableWords.randomWordLevel2();
        shuffedWord = availableWords.shuffleWord(givenWord);
        textViewShuffle.setText(shuffedWord);
    }

    private void renewWordLevel3()
    {
        givenWord = availableWords.randomWordLevel3();
        shuffedWord = availableWords.shuffleWord(givenWord);
        textViewShuffle.setText(shuffedWord);
    }



    /**
     *  function setTimer: is used to initialize values for the timer
     *
     *  const: time --> set the time to 10 which will be multiplied by 1000 to make the timer to 10 seconds*/
    private void setTimer(){

        //initialize the time variable
        int time = 10;

        //calculate the totaltime
        totalTimeCountInMilliseconds =  time * 1000;

        //set the maximum time of the progress bar timer
        mProgressBar1.setMax( time * 1000);
    }

    /**
     * function startTimer: is used to start counting down the timer
     *
     * */

    private void startTimer() {

        //call renewWord function once the timer is on
        renewWord();
        countDownTimer = new MyCountDown(totalTimeCountInMilliseconds, 50);
        countDownTimer.start();
    }

    /**
     *   function plusTime: is used to give extra time whenever player gets correct word
     */
    private void plusTime(){
        countDownTimer.cancel();
        countDownTimer = new MyCountDown(totalTimeCountInMilliseconds + 3000, 50);
        countDownTimer.start();


    }

    private void closeKeyboard()
    {
        View view = this.getCurrentFocus();
        if(view != null)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public class MyCountDown extends CountDownTimer {
        public MyCountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            totalTimeCountInMilliseconds = l;
            mProgressBar1.setProgress((int) totalTimeCountInMilliseconds);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onFinish() {
            //set up the visibility of all needed views
            textViewInput.setVisibility(View.INVISIBLE);
            buttonStartTime.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar1.setVisibility(View.GONE);
            textViewInput.setText("");
            buttonCheck.setVisibility(View.INVISIBLE);
            textViewShuffle.setText("TIME OUT!!!!");
            buttonBuyTime.setVisibility(View.INVISIBLE);

            //put the new score into highScore list (if suitable)

            SharedPreferences preferences = getSharedPreferences("TIME_PREFS", 0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("latestscore", score);
            editor.apply();

            //Hide the soft keyboard when timer finishes
//            textViewInput.onEditorAction(EditorInfo.IME_ACTION_DONE);

            closeKeyboard();


        }


    }


}