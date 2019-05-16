/*****  Created by Tammy Le, 2/5/2019        *******/
package com.example.wordaddicts;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class BeginActivity extends AppCompatActivity {


    MediaPlayer myMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);

        //set media player
        final MediaPlayer layoutClicked = MediaPlayer.create(this, R.raw.clicked);

        //Get the RelativeLayout object
        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout);

        //Implements onclick listener
        /*
        set on click listener for the whole layout
        a new intent is created taking user to the MenuActivity and sound is played accordingly
         */
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //play sound
                layoutClicked.start();
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
