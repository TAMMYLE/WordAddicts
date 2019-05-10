package com.example.wordaddicts;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private CardView classicCard, speedCard, highScoreCard, shopCard;

    TextView aboutCoin;
    ImageView infoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        aboutCoin = (TextView) findViewById(R.id.aboutCoin);
        infoButton = (ImageView) findViewById(R.id.infobutton);

        //set media player
        final MediaPlayer layoutClicked = MediaPlayer.create(this, R.raw.mysecondclick);

        aboutCoin.setText("" + MainActivity.coin);

        classicCard = (CardView) findViewById(R.id.classicModeCard);
        speedCard = (CardView) findViewById(R.id.speedModeCard);
        highScoreCard = (CardView) findViewById(R.id.highScoreCard);
        shopCard = (CardView) findViewById(R.id.shopCard);

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //play sound
                layoutClicked.start();
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
            }
        });

        classicCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //play sound
                layoutClicked.start();
                openMainActivity();
            }
        });
        speedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //play sound
                layoutClicked.start();
                openTimerActivity();
            }
        });

        highScoreCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //play sound
                layoutClicked.start();
                openHighScoreListActivity();
            }
        });

        shopCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //play sound
                layoutClicked.start();
                openShopActivity();
            }
        });
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openTimerActivity()
    {
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }
    public void openHighScoreListActivity()
    {
        Intent intent = new Intent(this, HighScoreListActivity.class);
        startActivity(intent);
    }

    public void openShopActivity()
    {
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);
    }
}
