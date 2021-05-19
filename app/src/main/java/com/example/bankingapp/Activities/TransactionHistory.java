package com.example.bankingapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingapp.Adapters.CustomerRecyclerAdapter;
import com.example.bankingapp.Adapters.TransactionRecyclerAdapter;
import com.example.bankingapp.Database.DatabaseHelper;
import com.example.bankingapp.Model.TransactionModel;
import com.example.bankingapp.R;
import android.os.Bundle;

import java.util.ArrayList;

public class TransactionHistory extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<TransactionModel> transactionlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        recyclerView = findViewById(R.id.transactionHistory);
        DatabaseHelper db = new DatabaseHelper(this);
        transactionlist = db.selectAllTransaction();
        setAdapter();
    }

    private void setAdapter() {
        TransactionRecyclerAdapter adapter = new TransactionRecyclerAdapter(transactionlist);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}