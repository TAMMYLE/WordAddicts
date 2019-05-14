package com.example.wordaddicts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HighScoreListActivity extends AppCompatActivity {

    CardView classicHighScoreCard, speedHighScoreCard;
    TextView highScoreListCoinTextView;
    LinearLayout highScoreListShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_list);

        classicHighScoreCard = (CardView) findViewById(R.id.classicHighScoreCard);
        speedHighScoreCard = (CardView) findViewById(R.id.speedHighScoreCard);

        highScoreListCoinTextView = (TextView) findViewById(R.id.highscorelistCoin);
        highScoreListCoinTextView.setText("" + MainActivity.coin);

        highScoreListShop = (LinearLayout) findViewById(R.id.highscorelistShop);
        highScoreListShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShopActivity.class);
                startActivity(intent);
                finish();
            }
        });

        classicHighScoreCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHighScoreActivity();
            }
        });
//
        speedHighScoreCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimerHighScoreActivity();
            }
        });

    }

    public void openHighScoreActivity(){
        Intent intent = new Intent(this, HighScoreActivity.class);
        startActivity(intent);
    }

    public void openTimerHighScoreActivity(){
        Intent intent = new Intent(this, TimerHighScoreActivity.class);
        startActivity(intent);
    }
}
