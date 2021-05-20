package com.example.bankingapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bankingapp.Adapters.CustomerRecyclerAdapter;
import com.example.bankingapp.Database.DatabaseHelper;
import com.example.bankingapp.Interface.ClickActivity;
import com.example.bankingapp.Model.CustomerModel;
import com.example.bankingapp.R;

import java.util.ArrayList;

public class SelectCustomerFrom extends AppCompatActivity implements ClickActivity {

    ArrayList<CustomerModel> customerList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_customer_from);
        recyclerView = findViewById(R.id.selectCustFromList);
        DatabaseHelper db = new DatabaseHelper(this);
        customerList = db.selectAllCustomer();
        for (int i = 0; i < customerList.size(); i++){
            if (customerList.get(i).getBalance() <= 0) {
                customerList.remove(i);
            }
        }

        setAdapter();
    }

    private void setAdapter() {
        CustomerRecyclerAdapter adapter = new CustomerRecyclerAdapter(customerList, SelectCustomerFrom.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void nextActivity(int position) {
        Intent intent = new Intent(this, SelectCustomerTo.class);
        intent.putExtra("CustomerFrom", customerList.get(position));
        startActivity(intent);
    }
}