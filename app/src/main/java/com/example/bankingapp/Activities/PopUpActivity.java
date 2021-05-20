package com.example.bankingapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankingapp.Adapters.CustomerRecyclerAdapter;
import com.example.bankingapp.Adapters.TransactionRecyclerAdapter;
import com.example.bankingapp.Database.DatabaseHelper;
import com.example.bankingapp.Model.CustomerModel;
import com.example.bankingapp.Model.TransactionModel;
import com.example.bankingapp.R;

import java.util.ArrayList;
import java.util.Locale;

public class PopUpActivity extends AppCompatActivity {
    private TextView username, email, balanceAmount;
    private int customerId;
    private RecyclerView recyclerView;
    private ArrayList<TransactionModel> transactionHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int width, height;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        recyclerView = findViewById(R.id.popTransHistory);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.8), (int)(height * 0.7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        populateDate();
        transactionHistory = getTransactionHistory();

        setAdapter();
    }

    private ArrayList<TransactionModel> getTransactionHistory() {
        DatabaseHelper db = new DatabaseHelper(this);
        return db.selectCustomerTransaction(customerId);
    }

    private void setAdapter() {
        TransactionRecyclerAdapter adapter = new TransactionRecyclerAdapter(transactionHistory, true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void populateDate() {
        Intent myIntent = getIntent();

        username = findViewById(R.id.popUserName);
        email = findViewById(R.id.popupEmail);
        balanceAmount = findViewById(R.id.popUpBalAmount);
        CustomerModel customer = (CustomerModel) myIntent.getSerializableExtra("CustomerId");
        customerId = customer.getId();

        username.setText(customer.getName());
        email.setText(customer.getEmail());
        balanceAmount.setText(String.format(Locale.getDefault(), "£ %.2f", customer.getBalance()));
    }
}