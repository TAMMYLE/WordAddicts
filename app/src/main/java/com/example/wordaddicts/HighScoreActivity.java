//Created by Tammy Le, 15/4/2019
package com.example.wordaddicts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HighScoreActivity extends AppCompatActivity {


    TextView wordscore;

    int latestScore;
    int best1, best2, best3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        wordscore = (TextView) findViewById(R.id.wordscore);

        // this part is to load old scores from main
        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        //initialize the scores
        latestScore = preferences.getInt("latestscore", 0);
        best1 = preferences.getInt("best1", 0);
        best2 = preferences.getInt("best2", 0);
        best3 = preferences.getInt("best3", 0);


        // this part is to replace the old high score with the new high score
        // if the latest score is greater than the third best score, assign latest score to best3
        if (latestScore > best3){
            best3 = latestScore;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best3", best3);
            editor.apply();
        }
        // if the latest score is greater than the second best score
        // assign best2 to a temporary variable
        // then assign latest score to best2
        // then assign best3 to temporary variable
        if (latestScore > best2){
            int temp = best2;
            best2 = latestScore;
            best3 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best3", best3);
            editor.putInt("best2", best2);
            editor.apply();
        }
        // if the latest score is greater than the first best score
        // assign best1 to a temporary variable
        // then assign latest score to best1
        // then assign best2 to temporary variable
        if (latestScore > best1){
            int temp = best1;
            best1 = latestScore;
            best2 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best2", best2);
            editor.putInt("best1", best1);
            editor.apply();
        }

        //create textlines of highest scores
        wordscore.setText("LATEST SCORE: " + latestScore + "\n" +
        "BEST 1: " + best1 + "\n" +
                "BEST 2: " + best2 + "\n" +
                        "BEST 3: " + best3 );

    }

    // this part will bring player back to the main activity.
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
