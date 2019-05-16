//Created by Tammy Le, 15/4/2019
package com.example.wordaddicts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HighScoreActivity extends AppCompatActivity {


    private TextView wordscore, currentScore, best1TextView, best2TextView, best3TextView;
    private LinearLayout classicShop;

    private int latestScore;
    private int best1, best2, best3;
    private int coin;
    TextView highScoreClassicCoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        //set media player
        final MediaPlayer layoutClicked = MediaPlayer.create(this, R.raw.clicked);

        //get all the elements from the layout
        best1TextView = (TextView) findViewById(R.id.classicBest1);
        best2TextView = (TextView) findViewById(R.id.classicBest2);
        best3TextView = (TextView) findViewById(R.id.classicBest3);
        currentScore = (TextView) findViewById(R.id.classicCurrent);

        classicShop = (LinearLayout) findViewById(R.id.classicHighScore);


        highScoreClassicCoin = (TextView) findViewById(R.id.highscoreclassicCoin);
        coin = retrieveCoin();//retrieve the coin from the preference
        highScoreClassicCoin.setText("" + coin);

        // this part is to load old scores from main
        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        //initialize the scores
        latestScore = preferences.getInt("latestscore", 0);
        best1 = preferences.getInt("best1", 0);
        best2 = preferences.getInt("best2", 0);
        best3 = preferences.getInt("best3", 0);

        /*
        this part is to replace the old high score with the new high score
         if the latest score is greater than the third best score, assign latest score to best3
         */

        if (latestScore > best3){
            best3 = latestScore;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best3", best3);
            editor.apply();
        }
        /*
         if the latest score is greater than the second best score
         assign best2 to a temporary variable
         then assign latest score to best2
         then assign best3 to temporary variable
         */

        if (latestScore > best2){
            int temp = best2;
            best2 = latestScore;
            best3 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best3", best3);
            editor.putInt("best2", best2);
            editor.apply();
        }
        /*
         if the latest score is greater than the first best score
         assign best1 to a temporary variable
         then assign latest score to best1
         then assign best2 to temporary variable
         */

        if (latestScore > best1){
            int temp = best1;
            best1 = latestScore;
            best2 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best2", best2);
            editor.putInt("best1", best1);
            editor.apply();
        }

        /*
        score is displayed accordingly
         */
        currentScore.setText("Your current score is " + latestScore);

        best1TextView.setText("Your best score is " + best1);
        best2TextView.setText("Your second best score is " + best2);
        best3TextView.setText("Your third best score is " + best3);
        /*
        a click listener is set to take user to the shopActivity
         */
        classicShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutClicked.start();
                Intent intent = new Intent(getApplicationContext(), ShopActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // this part will bring player back to the main activity.
    /*
    onBackPressed is customed to take user to a specific activity, in this case MainActivity
    instead of whatever Activity that led to this view
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    //put the new value of coin to the sharedpreference
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
