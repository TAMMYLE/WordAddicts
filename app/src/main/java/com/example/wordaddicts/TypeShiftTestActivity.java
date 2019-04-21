package com.example.typeshiftprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class TypeShiftTestActivity extends AppCompatActivity {

    public static final Random RANDOM = new Random();
    public static final String[] WORDS = {"GAME","HOPE", "FLOP", "DROP", "FLOW", "LIKE", "LINK", "HATE" };

    public String randomWord(){
        return WORDS[RANDOM.nextInt(WORDS.length)];
    }

    private static String TAG = "TypeshiftTestActivity";

    String word1 = randomWord();
    String word2 = randomWord();

    char[] a = word1.toCharArray();
    char[] b = word2.toCharArray();

    char[] first = new char[2];
    char[] second = new char[2];
    char[] third = new char[2];
    char[] fourth = new char[2];
    char[] fifth = new char[2];

    //function used to get a random numbers of string in an string array
    //idea taken from https://www.youtube.com/watch?v=8I37elnmZ2I
    //parameter an array of objects
    //return void
    // take an array and shuffle all elements in it
    public void shuffleArray (Object[] array)
    {
        int numberOfElements = array.length;

        for (int i = 0; i < array.length; i++)
        {
            int s = i + (int) (Math.random() * (numberOfElements - i));// take a random index
            //simple swap to change elements positions in the array;
            Object temp = array[i];
            array[i] = array[s];
            array[s] = temp;

        }
    }

    //function to put letters of given array of words into array of char in each column in the game
    //parameters : array of words and array of char
    //return void


    public void formArray (String[] wordArray, char[] letterArray, int j)
    {
        for (int i = 0; i < 2; i++)
        {
            letterArray[i] = wordArray[i].charAt(j);//put letters at given index of word array into char array
        }


    }




    ImageView  up1,up2, up3, up4, up5;
    ImageView  down1,down2, down3, down4, down5;
//    Button up1, down1;

    TextView tv_11, tv_21, tv_31, tv_41, tv_51;
    TextView tv_12, tv_22, tv_32, tv_42, tv_52;
    TextView tv_13, tv_23, tv_33, tv_43, tv_53;
    TextView tv_14, tv_24, tv_34, tv_44, tv_54;
    TextView tv_15, tv_25, tv_35, tv_45, tv_55;

    char[] letterArray1, letterArray2, letterArray3, letterArray4, letterArray5;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_shift_test);




        shuffleArray(WORDS);
        Log.d(TAG, WORDS[0]);
        //new arrays to store letters
        letterArray1 = new char[5];
        letterArray2 = new char[5];
        letterArray3 = new char[5];
        letterArray4 = new char[5];
        letterArray5 = new char[5];

        //put elements for letterArray1 (column 1)
        formArray(WORDS, letterArray1, 0);


        //get elements from the xml files
        up1 = (ImageView) findViewById(R.id.up1);
        down1 = (ImageView) findViewById(R.id.down1);

        up2 = (ImageView) findViewById(R.id.up2);
        down2 = (ImageView) findViewById(R.id.down2);

        up3 = (ImageView) findViewById(R.id.up3);
        down3 = (ImageView) findViewById(R.id.down3);

        up4 = (ImageView) findViewById(R.id.up4);
        down4 = (ImageView) findViewById(R.id.down4);

        up5 = (ImageView) findViewById(R.id.up5);
        down5 = (ImageView) findViewById(R.id.down5);

        tv_11 = (TextView) findViewById(R.id.tv_11);
        tv_21 = (TextView) findViewById(R.id.tv_21);
        tv_31 = (TextView) findViewById(R.id.tv_31);
        tv_41 = (TextView) findViewById(R.id.tv_41);
        tv_51 = (TextView) findViewById(R.id.tv_51);

        tv_12 = (TextView) findViewById(R.id.tv_12);
        tv_22 = (TextView) findViewById(R.id.tv_22);
        tv_32 = (TextView) findViewById(R.id.tv_32);
        tv_42 = (TextView) findViewById(R.id.tv_42);
        tv_52 = (TextView) findViewById(R.id.tv_52);


        tv_13 = (TextView) findViewById(R.id.tv_13);
        tv_23 = (TextView) findViewById(R.id.tv_23);
        tv_33 = (TextView) findViewById(R.id.tv_33);
        tv_43 = (TextView) findViewById(R.id.tv_43);
        tv_53 = (TextView) findViewById(R.id.tv_53);

        tv_14 = (TextView) findViewById(R.id.tv_14);
        tv_24 = (TextView) findViewById(R.id.tv_24);
        tv_34 = (TextView) findViewById(R.id.tv_34);
        tv_44 = (TextView) findViewById(R.id.tv_44);
        tv_54 = (TextView) findViewById(R.id.tv_54);



        tv_15 = (TextView) findViewById(R.id.tv_15);
        tv_25 = (TextView) findViewById(R.id.tv_25);
        tv_35 = (TextView) findViewById(R.id.tv_35);
        tv_45 = (TextView) findViewById(R.id.tv_45);
        tv_55 = (TextView) findViewById(R.id.tv_55);


//        formArray(WORDS, letterArray2, 1);
//        formArray(WORDS, letterArray3, 2);
//        formArray(WORDS, letterArray4, 3);
//        formArray(WORDS, letterArray5, 4);
        //assign elements from letterArray1 to textview accordingly;
        tv_11.setText(Character.toString(letterArray1[0]));
        tv_21.setText(Character.toString(letterArray1[1]));
        tv_31.setText(Character.toString(letterArray1[2]));
        tv_41.setText(Character.toString(letterArray1[3]));
        tv_51.setText(Character.toString(letterArray1[4]));

//        tv_11.setText("");
//        tv_21.setText("B");
//        tv_31.setText("A");
//        tv_41.setText("");
//        tv_51.setText("");

        tv_12.setText("");
        tv_22.setText("");
        tv_32.setText("A");
        tv_42.setText("");
        tv_52.setText("");

        tv_13.setText("");
        tv_23.setText("");
        tv_33.setText("A");
        tv_43.setText("");
        tv_53.setText("");

        tv_14.setText("");
        tv_24.setText("");
        tv_34.setText("A");
        tv_44.setText("");
        tv_54.setText("");

        tv_15.setText("");
        tv_25.setText("");
        tv_35.setText("A");
        tv_45.setText("");
        tv_55.setText("");


        //set the funtion for all the button up and down
        up1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                upLetter(tv_11,tv_21, tv_31, tv_41, tv_51);
            }
        });


        down1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                downLetter(tv_11,tv_21, tv_31, tv_41, tv_51);
            }
        });


        up2.setClickable(true);
        up2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                upLetter(tv_12,tv_22,tv_32,tv_42,tv_52);

            }
        });

        down2.setClickable(true);
        down2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                downLetter(tv_12,tv_22,tv_32,tv_42,tv_52);
            }
        });



        up3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                upLetter(tv_13,tv_23, tv_33, tv_43, tv_53);
            }
        });


        down3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                downLetter(tv_13,tv_23, tv_33, tv_43, tv_53);
            }
        });

        up4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                upLetter(tv_14,tv_24, tv_34, tv_44, tv_54);
            }
        });


        down4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                downLetter(tv_14,tv_24, tv_34, tv_44, tv_54);
            }
        });

        up5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                upLetter(tv_15,tv_25, tv_35, tv_45, tv_55);
            }
        });


        down5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                downLetter(tv_15,tv_25, tv_35, tv_45, tv_55);
            }
        });
    }
    //build the funtion for up button
    //parameters all textViews
    // return void
    //change the position of the letter upwards
    private void upLetter(TextView tv1, TextView tv2, TextView tv3, TextView tv4, TextView tv5)
    {
        if(tv1.getText() != "")
        {
            return;
        }
        tv1.setText(tv2.getText());
        tv2.setText(tv3.getText());
        tv3.setText(tv4.getText());
        tv4.setText(tv5.getText());
        tv5.setText("");
    }
    //build the funtion for down button
    //parameters all textViews
    // return void
    //change the position of the letter downwards
    private void downLetter (TextView tv1, TextView tv2, TextView tv3, TextView tv4, TextView tv5)
    {
        if(tv5.getText() != "")
        {
            return;
        }

        tv5.setText(tv4.getText());
        tv4.setText(tv3.getText());
        tv3.setText(tv2.getText());
        tv2.setText(tv1.getText());
        tv1.setText("");

    }


}
