package com.example.wordaddicts;

/*****  Created by Tammy Le, 10/5/2019        *******/
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

public class AboutActivity extends AppCompatActivity {

    private TextView aboutCoin;//set the coin view for this activity
    private ImageView plusCoin;
    private LinearLayout shopButton;//button that takes users to the store
    private int coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //link variables with layout elements
        aboutCoin = (TextView) findViewById(R.id.aboutCoin);
        shopButton = (LinearLayout) findViewById(R.id.shopButton);
        coin = retrieveCoin();//retrieve the coin from shared preference 

        //set media player
        final MediaPlayer layoutClicked = MediaPlayer.create(this, R.raw.coinclink);
        //set the coin, all activity will display the same amount of coins which is taken from main activity
        aboutCoin.setText("" + coin);

        //If players click on the coin image at the bottom of the page --> coin will be plus 100
        plusCoin = (ImageView) findViewById(R.id.buttonpluscoin);
        /*
        set the click listener for the ImageView plusCoin
        SharedPreference is used to assure that user can only click 3 times per day
        every click is rewarded with 10 coins
         */
        plusCoin.setOnClickListener(new View.OnClickListener() {
            int clicks = 0;//variable to store the number of clicks
            Date date = new Date();//get the current date

            @Override
            public void onClick(View v) {

                layoutClicked.start();
                //sharedPreference to store the date and number of clicks that day
                SharedPreferences preferences = getSharedPreferences("REWARD_PREFS", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("clicks", clicks);
                editor.putString("date", date.toString());

                clicks++;

                //clicklimit
                if(clicks<4 && date.toString() != preferences.getString("date", null))// only allow 3 clicks one day
                {
                    coin += 10;
                    editCoin(coin);//put new value of coin into the sharedpreference;
                    aboutCoin.setText("" + coin);
                }
                else {
                    plusCoin.setClickable(false);//unable user to click on the ImageView after 3 clicks
                }

            }

        });
        //navigate to the shopActivity
        /*
        set click listener for the coin icon and the amount of coin left
        new intent is created to take user to the ShopActivity
         */
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShopActivity.class);
                startActivity(intent);
                finish();
            }
        });


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
