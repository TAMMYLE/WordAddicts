/*****  Created by Tammy Le, 16/4/2019        *******/
package com.example.wordaddicts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
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
    private int coin;

    private ImageView highScoreTimer;

    private Button buttonStartTime;
    private Button buttonBuyTime;

    private EditText textViewInput;
    private TextView textViewShuffle, scoreTextView, timerCoin;
    private TextView leveltextview;

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

        //set media player
        final MediaPlayer correctWord = MediaPlayer.create(this, R.raw.correct);
        final MediaPlayer incorrectWord = MediaPlayer.create(this, R.raw.incorrect);
        final MediaPlayer newgame = MediaPlayer.create(this, R.raw.myclicksound);

        //initialize views

        buttonStartTime = (Button) findViewById(R.id.button_timerview_start);
        buttonBuyTime = (Button) findViewById(R.id.buyTimeButton);
        buttonBuyTime.setVisibility(View.INVISIBLE);// hide the button when the game has not started

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar_timerview);
        mProgressBar1 = (ProgressBar) findViewById(R.id.progressbar1_timerview);

        highScoreTimer = (ImageView) findViewById(R.id.highscoreTimer);

        //declare level variable
        //added by Tammy Le, 13/5/2019
        leveltextview = (TextView) findViewById(R.id.leveltextview);
        leveltextview.setText("LEVEL 1");//initialize the level
        leveltextview.setVisibility(View.INVISIBLE);// set the LevelTextView to be invisible until the game starts

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
        coin = retrieveCoin();//retrieve coin from the shared preference
        timerCoin.setText("" + coin);

        timerShop = (LinearLayout) findViewById(R.id.timerShop);

        //Set max length for first level input
        textViewInput.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(4) });

        /*
        set the onKey listener for textViewInput
        it gets the user input from the keyboard and then compare with the givenWord
        The level is also displayed according to what score user is having
        sound is played accordingly
         */
        textViewInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    //retrieving user's input --> convert to string
                    guessWord = textViewInput.getText().toString();

                    //added by Tammy, 3/5/2019
                    //Hide the soft keyboard when user pressed
                    closeKeyboard();

                    //clear the input
                    textViewInput.setText("");
                    //display a message according to the result
                    if(availableWords.compareWords(givenWord, guessWord))
                    {

                        if(score >=800)
                        {
                            //set max length for input Level 4
                            textViewInput.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(6) });
                            leveltextview.setText("IV");
                            renewWordLevel4();
                        }

                        else if(score >=400)
                        {
                            //set max length for input Level 3
                            textViewInput.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(5) });
                            leveltextview.setText("III");
                            renewWordLevel3();
                        }

                        else if(score >= 200)
                        {
                            //set max length for input Level 2
                            textViewInput.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(4) });
                            leveltextview.setText("II" );
                            renewWordLevel2();
                        }
                        else
                        {
                            leveltextview.setText("I");
                            renewWord();
                        }

                        score += 10;//add the score
                        scoreTextView.setText("Score: " + score);//increase the score when user insert a right answer
                        plusTime();
                        //play sound
                        correctWord.start();

                    }
                    else
                    {
                     //added by Tammy Le
                        //When player gets a wrong word -> input will be vibrated
                        //Inspired by Priya
                        Animation vibrate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.vibrate);
                        textViewInput.startAnimation(vibrate);
                        //play sound incorrect
                        incorrectWord.start();
                    }

                }
                return false;
            }
        });
        /*
        initialize the click listeners for start button
        sound is played
        setTimer() function is called
        set the visibility of all the needed views
        start the timer by calling startTimer()
        */

        buttonStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //play sound
                newgame.start();

                setTimer();
                score = 0;
                scoreTextView.setText("Score: " + score);
                leveltextview.setText("I");
                buttonStartTime.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
                textViewShuffle.setVisibility(View.VISIBLE);//show the word that user needs to guess
                scoreTextView.setVisibility(View.VISIBLE);// show the score when the game starts
                leveltextview.setVisibility(View.VISIBLE);// show the level when the game starts
                timerCoin.setVisibility(View.VISIBLE);//show the coin when the game starts
                buttonBuyTime.setVisibility(View.VISIBLE);// show the buyTime button when the game starts
                textViewInput.setVisibility(View.VISIBLE);//show the textInput


                startTimer();

                //set the visibility of the main progress bar
                mProgressBar1.setVisibility(View.VISIBLE);



            }
        });


        ///?Buy time button
        /*
        set the function to buy time when user click buytime Button
        there will be a condition
        if user's coin is more than 100 then they can buy more time buy trigger the plusTimer() function
        new amount of coin is also put into the SharedPreference to assure the consitency of coin through out all activities
         */
        buttonBuyTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(coin >= 100)
                {
                    coin = coin - 100;
                    editCoin(coin);// update the coin to the shared preference
                    plusTime();
                    timerCoin.setText("" + coin);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You don't have enough coin to buy time", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*
        set click listener for highScore icon
        sound gets played and new intent is created to take user to TimerHighScorepActivity
         */
        highScoreTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //play sound
                newgame.start();
                Intent intent = new Intent(getApplicationContext(), TimerHighScoreActivity.class);
                startActivity(intent);
                finish();
            }
        });

        /**
        *   set a click listener when user click on the coin or amount of coin left
        *   sound gets played and new intent is created to take user to ShopActivity
        **/
        timerShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //play sound
                newgame.start();
                Intent intent = new Intent(getApplicationContext(), ShopActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    /*
    renewWord()
    get a new random word and shuffle all the letters to display
    parameters: no parameters needed
    return: void
    */
    private void renewWord() {
        givenWord = availableWords.randomWordLevel1();
        shuffedWord = availableWords.shuffleWord(givenWord);
        textViewShuffle.setText(shuffedWord);
    }
    //different renewWord function to change to more complex word when user hitting a curtain score
        /*
    renewWordLevel2()
    get a new random word from WORDLEVEL2 array in LetterStorage class and shuffle all the letters to display
    parameters: no parameters needed
    return: void
     */
    private void renewWordLevel2()
    {
        givenWord = availableWords.randomWordLevel2();
        shuffedWord = availableWords.shuffleWord(givenWord);
        textViewShuffle.setText(shuffedWord);
    }
    /*
   renewWordLevel3()
  get a new random word from WORDLEVEL3 array in LetterStorage class and shuffle all the letters to display
  parameters: no parameters needed
  return: void
 */
    private void renewWordLevel3()
    {
        givenWord = availableWords.randomWordLevel3();
        shuffedWord = availableWords.shuffleWord(givenWord);
        textViewShuffle.setText(shuffedWord);
    }

    //Added by Tammy Le, 13/5/2019
            /*
    renewWordLevel4()
    get a new random word from WORDLEVEL4 array in LetterStorage class and shuffle all the letters to display
    parameters: no parameters needed
    return: void
     */
    private void renewWordLevel4()
    {
        givenWord = availableWords.randomWordLevel4();
        shuffedWord = availableWords.shuffleWord(givenWord);
        textViewShuffle.setText(shuffedWord);
    }

    /**
     *  function setTimer: is used to initialize values for the timer
     *  parameters: no parameters needed for this funtion
     *  returns : void
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
     * parameters: no parameters needed for this function
     * returns : void
     * */

    private void startTimer() {

        //call renewWord function once the timer is on
        renewWord();
        countDownTimer = new MyCountDown(totalTimeCountInMilliseconds, 50);
        countDownTimer.start();
    }

    /**
     *   function plusTime: is used to give extra time whenever player gets correct word
     *   parameters: no parameters needed for this function
     *   returns : void
     */
    private void plusTime(){

        if(totalTimeCountInMilliseconds < 10000)
        {
            countDownTimer.cancel();
            countDownTimer = new MyCountDown(totalTimeCountInMilliseconds + 3000, 50);// give extra 3000 milliseconds if they get the answer right
        }
        else
        {
            countDownTimer.cancel();
            countDownTimer = new MyCountDown(10000, 50);// set the timer to 10000 millisec if totalTimeCountInMilliseconds + 3000 > 10000
        }

        countDownTimer.start();


    }
//function to close the soft keyboard
    /*
    closeKeyboard: use to close the softkeyboard when its called
    parameters: no parameters needed
    returns: void, the function just closes the keyboard, no return value needed
     */
    private void closeKeyboard()
    {
        View view = this.getCurrentFocus();
        if(view != null)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    //create a custom CountDownTimer
    /*
    a new class created extended from CountDownTimer
    we customed the onTick and onFinish functions to be suitable for our own app purpose
    like set visibility of some elememts for them to appear in appropriate way
    or set the progress bar along with the timer
     */
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
            textViewShuffle.setText(givenWord);
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