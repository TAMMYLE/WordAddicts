package com.example.wordaddicts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private CardView classicCard, speedCard, highScoreCard, shopCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

//        buttonPlay = findViewById(R.id.button);
//        buttonPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openMainActivity();
//            }
//        });

        classicCard = (CardView) findViewById(R.id.classicModeCard);
        speedCard = (CardView) findViewById(R.id.speedModeCard);
        highScoreCard = (CardView) findViewById(R.id.highScoreCard);
        shopCard = (CardView) findViewById(R.id.shopCard);

        classicCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });
        speedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimerActivity();
            }
        });

        highScoreCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHighScoreListActivity();
            }
        });

        shopCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
