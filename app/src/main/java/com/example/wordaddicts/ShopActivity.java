package com.example.wordaddicts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {

    private TextView storeCoin;
    private ImageView info;
    private int coin;

    private CardView basicPack, valuePack, megaPack, superPack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        basicPack = (CardView) findViewById(R.id.basicPack);
        valuePack = (CardView) findViewById(R.id.valuePack);
        megaPack = (CardView) findViewById(R.id.megaPack);
        superPack = (CardView) findViewById(R.id.superPack);

        //set media player
        final MediaPlayer packClick = MediaPlayer.create(this, R.raw.clicked);

        storeCoin = (TextView) findViewById(R.id.storeCoin);

        coin = retrieveCoin();//get elements from shared preference
        storeCoin.setText("" + coin);

        info = (ImageView) findViewById(R.id.infobutton);
        /*
        navigate to AboutActivity
        appropriate sound is played
         */
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
                finish();
            }
        });


        basicPack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                intent.putExtra("pack", 1.99);
                startActivity(intent);
                finish();
            packClick.start();

            }
        });

        valuePack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                packClick.start();
                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                intent.putExtra("pack", 2.99);
                startActivity(intent);
                finish();

            }
        });

        megaPack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                packClick.start();
                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                intent.putExtra("pack", 7.99);
                startActivity(intent);
                finish();

            }
        });

        superPack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                packClick.start();
                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                intent.putExtra("pack", 14.99);
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
