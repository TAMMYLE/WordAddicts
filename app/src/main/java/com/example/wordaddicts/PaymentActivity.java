package com.example.wordaddicts;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wordaddicts.Config.Config;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

/*
inspired by the video by EMDT Dev
https://www.youtube.com/watch?v=k5lPy_50f0Y&t=32s
 */
public class PaymentActivity extends AppCompatActivity {



    public static final int PAYPAL_REQUEST_CODE = 7171;

    private static PayPalConfiguration configuration = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);

    private int coin;

    Button btnPayNow;
    TextView editAmount;
    Double value;
    String amount = "";

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);



        //coin
        coin = retrieveCoin();

        //start Paypal service
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,configuration);
        startService(intent);

        btnPayNow = (Button) findViewById(R.id.btnPayNow);
        editAmount = (TextView) findViewById(R.id.editAmount);


        //get the amount of money
        Intent intent1 = getIntent();
        value = intent1.getDoubleExtra("pack", 0.0);
        amount = value.toString();
        editAmount.setText(amount + "AUD");

        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPayment();

            }
        });

    }

    /*
    processPayment
    processing the payment bringing user to PaymentActivity by PayPal
    parameters: no parameters needed
    returns: void
     */
    private void processPayment()
    {

        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(amount)), "AUD", "Donate for developers", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this, com.paypal.android.sdk.payments.PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration);
        intent.putExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);

    }

    /*
    onActivityResult has different results based on the code
    if RESULT_OK then coin is added according to the amount of money they pay
    a sound is played when payment is processed successfully
    if the payemnt process is failed, appropriate Toast message will pop up
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //set media player
        final MediaPlayer coinGained = MediaPlayer.create(this, R.raw.coinclink);
        if(requestCode == PAYPAL_REQUEST_CODE){
            if(resultCode == RESULT_OK)
            {
                PaymentConfirmation confirmation = data.getParcelableExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                if(confirmation != null)
                {
                    try{
                        String paymentDetails = confirmation.toJSONObject().toString(4);

                        startActivity(new Intent(this, ShopActivity.class)
//                                .putExtra("PaymentDetails", paymentDetails)
//                                .putExtra("PaymentAmount", amount)

                        );
                        if(value == 1.99)
                        {
                            coin += 250;
                        }
                        else if(value == 2.99)
                        {
                            coin += 500;
                        }
                        else if(value == 7.99)
                        {
                            coin += 1500;
                        }
                        else if(value == 14.99)
                        {
                            coin += 3000;
                        }

                        editCoin(coin);

                        coinGained.start();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
            else if(resultCode == Activity.RESULT_CANCELED)
            {
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
            }

        }
        else if(resultCode == com.paypal.android.sdk.payments.PaymentActivity.RESULT_EXTRAS_INVALID)
        {
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        }
    }


    /*
    editCoin()
    take the current amount of coin and put it into the SharedPreferences
    parameters: current number of coin in Integer form
    return: void, the function just edit the coin in the SharedPreference, doesn't return anything
    */
    public void editCoin(int coin)
    {
        SharedPreferences preferences = getSharedPreferences("COIN_PREFS", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("coin", coin);
        editor.apply();
    }
    //retrieve the value of coin from SharedPreference
        /*
    retrieveCoin()
    retrieve the amount of coin currently holding in the SharedPreference
    parameters: no parameters needed
    returns: integer contains the number of coins.
     */
    public int retrieveCoin()
    {
        int coin1;
        SharedPreferences preferences = getSharedPreferences("COIN_PREFS", 0);

        coin1 = preferences.getInt("coin", 0);
        return coin1;
    }

}
