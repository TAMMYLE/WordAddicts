//created by Phong Le, 6/5/2019
package com.example.wordaddicts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimerHighScoreActivity extends AppCompatActivity {

    private TextView timerScore, currentTextView, best1TextView, best2TextView, best3TextView;

    private int latestScore;
    private int best1, best2, best3;
    private int coin;

    private LinearLayout timerHighScoreShop;

    TextView highScoreSpeedCoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_high_score);

        //get all the elements from the layout
        currentTextView = (TextView) findViewById(R.id.speedCurrent);
        best1TextView= (TextView) findViewById(R.id.speedBest1);
        best2TextView= (TextView) findViewById(R.id.speedBest2);
        best3TextView = (TextView) findViewById(R.id.speedBest3);

        highScoreSpeedCoin = (TextView) findViewById(R.id.highscoreSpeedCoin);
        coin = retrieveCoin();//retrieve the coin from shared preference
        highScoreSpeedCoin.setText("" + coin);

        timerHighScoreShop = (LinearLayout) findViewById(R.id.timerHighScoreShop);


        // this part is to load old scores from main
        SharedPreferences preferences = getSharedPreferences("TIME_PREFS", 0);
        //initialize the scores
        latestScore = preferences.getInt("latestscore", 0);
        best1 = preferences.getInt("best1", 0);
        best2 = preferences.getInt("best2", 0);
        best3 = preferences.getInt("best3", 0);


        // this part is to replace the old high score with the new high score
        // if the latest score is greater than the third best score, assign latest score to best3
        if (latestScore > best3){
            best3 = latestScore;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best3", best3);
            editor.apply();
        }
        // if the latest score is greater than the second best score
        // assign best2 to a temporary variable
        // then assign latest score to best2
        // then assign best3 to temporary variable
        if (latestScore > best2){
            int temp = best2;
            best2 = latestScore;
            best3 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best3", best3);
            editor.putInt("best2", best2);
            editor.apply();
        }
        // if the latest score is greater than the first best score
        // assign best1 to a temporary variable
        // then assign latest score to best1
        // then assign best2 to temporary variable
        if (latestScore > best1){
            int temp = best1;
            best1 = latestScore;
            best2 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best2", best2);
            editor.putInt("best1", best1);
            editor.apply();
        }

        //create textlines of highest scores
//        timerScore.setText("LATEST SCORE: " + latestScore + "\n" +
//                "BEST 1: " + best1 + "\n" +
//                "BEST 2: " + best2 + "\n" +
//                "BEST 3: " + best3 );

        currentTextView.setText("Your current score is " + latestScore);
        best1TextView.setText("Your best score is " + best1);
        best2TextView.setText("Your second best score is " + best2);
        best3TextView.setText("Your third best score is " + best3);

        timerHighScoreShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShopActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    // this part will bring player back to the main activity.
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), TimerActivity.class);
        startActivity(intent);
        finish();
    }
    //put the new value of coin to the sharedpreference
    public void editCoin(int coin)
    {
        SharedPreferences preferences = getSharedPreferences("COIN_PREFS", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("coin", coin);
        editor.apply();
    }
    //retrieve the value of coin from SharedPreference
    public int retrieveCoin()
    {
        int coin1;
        SharedPreferences preferences = getSharedPreferences("COIN_PREFS", 0);

        coin1 = preferences.getInt("coin", 0);
        return coin1;
    }
}
