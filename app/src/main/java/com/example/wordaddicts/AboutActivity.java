package com.example.wordaddicts;

/*****  Created by Tammy Le, 10/5/2019        *******/
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    TextView aboutCoin;
    ImageView plusCoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        aboutCoin = (TextView) findViewById(R.id.aboutCoin);

        //set media player
        final MediaPlayer layoutClicked = MediaPlayer.create(this, R.raw.coinclink);

        aboutCoin.setText("" + MainActivity.coin);

        //If players click on the coin image at the bottom of the page --> coin will be plus 100
        plusCoin = (ImageView) findViewById(R.id.buttonpluscoin);
        plusCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutClicked.start();
                MainActivity.coin += 100;
                aboutCoin.setText("" + MainActivity.coin);
            }
        });

    }
}
