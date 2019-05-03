/*****  Created by Tammy Le, 16/4/2019        *******/
package com.example.wordaddicts;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class TimerActivity extends AppCompatActivity{


    ProgressBar mProgressBar, mProgressBar1;

    private Button buttonStartTime, buttonStopTime;
    private Button buttonCheck;
    private EditText textViewInput;
    private TextView textViewShuffle;

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

        textViewInput = (EditText)
                findViewById(R.id.textViewInput);
        textViewShuffle = (TextView)
                findViewById(R.id.shuffledWord)    ;
        textViewShuffle.setVisibility(View.INVISIBLE);//hiding the word that user needs to guess


        //initialize the click listeners
        // If id is a Start button
        buttonStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call setTimer function
                setTimer();

                //set the visibility of all needed views
                buttonStartTime.setVisibility(View.INVISIBLE);
                //buttonStopTime.setVisibility(View.VISIBLE);
                buttonCheck.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
                textViewShuffle.setVisibility(View.VISIBLE);//show the word that user needs to guess

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
                textViewInput.onEditorAction(EditorInfo.IME_ACTION_DONE);

                //display a message according to the result
                if(availableWords.compareWords(givenWord, guessWord))
                {
                    textViewInput.setText("");              //clear the input
                    renewWord();                            //call renew function

                    plusTime();
                }
            }
        });
        buttonCheck.setVisibility(View.INVISIBLE);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar_timerview);
        mProgressBar1 = (ProgressBar) findViewById(R.id.progressbar1_timerview);



    }



    /**
     * function renewWord: is used to shuffle new word based on a given word on LettersStorage class
     * */
    private void renewWord() {
        givenWord = availableWords.randomWord();
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

    public class MyCountDown extends CountDownTimer {
        public MyCountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            totalTimeCountInMilliseconds = l;
            mProgressBar1.setProgress((int) totalTimeCountInMilliseconds);
        }

        @Override
        public void onFinish() {
            //set up the visibility of all needed views
            textViewInput.setVisibility(View.VISIBLE);
            buttonStartTime.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar1.setVisibility(View.GONE);
            textViewInput.setText("");
        }
    }


}
