/*****  Created by Tammy Le, 16/4/2019        *******/
package com.example.wordaddicts;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class TimerActivity extends AppCompatActivity implements View.OnClickListener{


    ProgressBar mProgressBar, mProgressBar1;

    private Button buttonStartTime, buttonStopTime;
    private Button buttonCheck;
    private EditText textViewInput;
    private TextView textViewShuffle;

    private String guessWord;          //what user put in
    private String givenWord;          // what word the game wants user to guess
    private String shuffedWord;        // the word the game gives but already shuffled

    private LettersStorage availableWords = new LettersStorage();

    private CountDownTimer countDownTimer;

    //declare the total time of the timer counting in milliseconds
    private long totalTimeCountInMilliseconds;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        //initialize views

        buttonStartTime = (Button) findViewById(R.id.button_timerview_start);
        buttonStopTime = (Button) findViewById(R.id.button_timerview_stop);
        buttonCheck = (Button) findViewById(R.id.buttonCheck);

        textViewInput = (EditText)
                findViewById(R.id.textViewInput);
        textViewShuffle = (TextView)
                findViewById(R.id.shuffledWord)    ;

        buttonStartTime.setOnClickListener(this);
        buttonStopTime.setOnClickListener(this);
        buttonCheck.setOnClickListener(this);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar_timerview);
        mProgressBar1 = (ProgressBar) findViewById(R.id.progressbar1_timerview);

    }


    //initialize the click listeners

    @Override
    public void onClick(View v) {

        // the conditions are:
        // checking if the button is :
        // Start button?
        // Stop button?
        // Check button?

        // If id is a Start button
        if (v.getId() == R.id.button_timerview_start) {

            //call setTimer function
            setTimer();

            //set the visibility of all needed views
            buttonStartTime.setVisibility(View.INVISIBLE);
            buttonStopTime.setVisibility(View.VISIBLE);
            buttonCheck.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);

            //call startTimer function
            startTimer();

            //set the visibility of the main progress bar
            mProgressBar1.setVisibility(View.VISIBLE);

            //if ID is a Stop button
        } else if (v.getId() == R.id.button_timerview_stop) {

            // timer is canceled --> set visibility of all needed views
            countDownTimer.cancel();
            countDownTimer.onFinish();
            mProgressBar1.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
            buttonStartTime.setVisibility(View.VISIBLE);
            buttonStopTime.setVisibility(View.INVISIBLE);
            buttonCheck.setVisibility(View.VISIBLE);

            //if ID is a Check button
        } else if (v.getId() == R.id.buttonCheck){

            //this toast for testing
            Toast.makeText(getApplicationContext(), "Its run", Toast.LENGTH_SHORT).show();

            //retrieving user's input --> convert to string
            guessWord = textViewInput.getText().toString();

            //display a message according to the result
            if(availableWords.compareWords(givenWord, guessWord))
            {
                textViewInput.setText("");              //clear the input
                renewWord();                            //call renew function
            }
        }

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
     * function setTimer: is used to initialize values for the timer
     *
     * const: time --> set the time to 10 which will be multiplied by 1000 to make the timer to 10 seconds*/
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
        countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 1) {
            @Override
            public void onTick(long leftTimeInMilliseconds) {

                // calculating the time by dividing it with 1000
                long seconds = leftTimeInMilliseconds / 1000;
                mProgressBar1.setProgress((int) (leftTimeInMilliseconds));
            }
            @Override
            public void onFinish() {

                //set up the visibility of all needed views
                textViewInput.setVisibility(View.VISIBLE);
                buttonStartTime.setVisibility(View.VISIBLE);
                buttonStopTime.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar1.setVisibility(View.GONE);

            }
        }.start();
    }


}
