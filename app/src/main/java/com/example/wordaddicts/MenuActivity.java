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


    private ImageView infoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        infoButton = (ImageView) findViewById(R.id.infobutton);

        //set media player
        final MediaPlayer layoutClicked = MediaPlayer.create(this, R.raw.clicked);



        classicCard = (CardView) findViewById(R.id.classicModeCard);
        speedCard = (CardView) findViewById(R.id.speedModeCard);
        highScoreCard = (CardView) findViewById(R.id.highScoreCard);
        shopCard = (CardView) findViewById(R.id.shopCard);
        /*
        navigating to AboutActivity
        appropriate sound is played
         */
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //play sound
                layoutClicked.start();
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
            }
        });

        /*
        navigate to MainActivity
        appropriate sound is played
         */
        classicCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //play sound
                layoutClicked.start();
                openMainActivity();
            }
        });

        /*
        navigate to TimerActivity
        appropriate sound is played
         */
        speedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //play sound
                layoutClicked.start();
                openTimerActivity();
            }
        });
        /*
        navigate to HighScoreListActivity
        appropriate sound is played
         */
        highScoreCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //play sound
                layoutClicked.start();
                openHighScoreListActivity();
            }
        });
        /*
        navigate to ShopActivity
        appropriate sound is played
         */
        shopCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //play sound
                layoutClicked.start();
                openShopActivity();
            }
        });
    }

    /*
    openMainAcitivity
    navigate to main activity through intent
    parameters: no parameters needed
    returns: void
     */
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    /*
    openTimerAcitivity
    navigate to timer activity through intent
    parameters: no parameters needed
    returns: void
     */
    public void openTimerActivity()
    {
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }
    /*
    openHighScoreListAcitivity
    navigate to highscorelistactivity through intent
    parameters: no parameters needed
    returns: void
    */
    public void openHighScoreListActivity()
    {
        Intent intent = new Intent(this, HighScoreListActivity.class);
        startActivity(intent);
    }
    /*
    openShopAcitivity
    navigate to shop activity through intent
    parameters: no parameters needed
    returns: void
     */

    public void openShopActivity()
    {
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);
    }
}
