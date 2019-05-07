/*****  Created by Tammy Le, 2/5/2019        *******/
package com.example.wordaddicts;


import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

public class BeginActivity extends AppCompatActivity {


    TextView myBeginning;
    VideoView myVideoView;

    MediaPlayer myMediaPlayer;

    int myCurrentVideoPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);

        myBeginning = (TextView) findViewById(R.id.circletextview);

        //Connect VideoView to UI
        myVideoView = (VideoView) findViewById(R.id.bgVideoView);

        //Build video UI
        Uri uri = Uri.parse("android.resources://"+getPackageName()+"/"+R.raw.backgroundvid1);

        //Set the new Uri to Video View
        myVideoView.setVideoURI(uri);
        //Start the VideoView
        myVideoView.start();


        myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                myMediaPlayer = mp;

                mp.setLooping(true);

                if (myCurrentVideoPosition != 0){
                    myMediaPlayer.seekTo(myCurrentVideoPosition);
                    myMediaPlayer.start();
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();

        myCurrentVideoPosition = myMediaPlayer.getCurrentPosition();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
