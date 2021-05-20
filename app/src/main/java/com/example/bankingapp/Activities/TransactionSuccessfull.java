package com.example.bankingapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.bankingapp.R;

public class TransactionSuccessfull extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_successfull);
        Handler handler = new Handler();
        handler.postDelayed(()->{ Intent intent = new Intent(this, ScrollingActivity.class);
            startActivity(intent);}, 3000);

    }
}