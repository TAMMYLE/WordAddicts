package com.example.wordaddicts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {

    TextView storeCoin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        storeCoin = (TextView) findViewById(R.id.storeCoin);

        storeCoin.setText("" + MainActivity.coin);
    }
}
