package com.example.wordaddicts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HighScoreListActivity extends AppCompatActivity {

    private CardView classicHighScoreCard, speedHighScoreCard;
    private TextView highScoreListCoinTextView;
    private LinearLayout highScoreListShop;
    private int coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_list);
        //get all the elements from the layout
        classicHighScoreCard = (CardView) findViewById(R.id.classicHighScoreCard);
        speedHighScoreCard = (CardView) findViewById(R.id.speedHighScoreCard);

        highScoreListCoinTextView = (TextView) findViewById(R.id.highscorelistCoin);
        coin = retrieveCoin(); // retireve the coin from the shared preference
        highScoreListCoinTextView.setText("" + coin);

        highScoreListShop = (LinearLayout) findViewById(R.id.highscorelist);
        //navigate to shop
        highScoreListShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShopActivity.class);
                startActivity(intent);
                finish();
            }
        });

        /*
        navigate to highscore of classic mode
         */
        classicHighScoreCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHighScoreActivity();
            }
        });
        /*
        navigate to highscore of speed mode
         */

        speedHighScoreCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimerHighScoreActivity();
            }
        });

    }
    //fucntions to navigate to other activities using intents
        /*
    openHighScoreAcitivity
    navigate to highscore activity through intent
    parameters: no parameters needed
    returns: void
    */
    public void openHighScoreActivity(){
        Intent intent = new Intent(this, HighScoreActivity.class);
        startActivity(intent);
    }
    /*
    openTimerHighScoreAcitivity
    navigate to timerhighscoreactivity through intent
    parameters: no parameters needed
    returns: void
    */
    public void openTimerHighScoreActivity(){
        Intent intent = new Intent(this, TimerHighScoreActivity.class);
        startActivity(intent);
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
