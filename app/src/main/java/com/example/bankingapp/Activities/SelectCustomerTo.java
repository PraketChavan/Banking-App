package com.example.bankingapp.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankingapp.Adapters.CustomerRecyclerAdapter;
import com.example.bankingapp.Database.DatabaseHelper;
import com.example.bankingapp.Interface.ClickActivity;
import com.example.bankingapp.Model.CustomerModel;
import com.example.bankingapp.Model.TransactionModel;
import com.example.bankingapp.R;

import java.util.ArrayList;

public class SelectCustomerTo extends AppCompatActivity implements ClickActivity {

    private ArrayList<CustomerModel> customerList;
    private RecyclerView recyclerView;
    private CustomerModel customerFrom;
    private AlertDialog enterAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_select_customer_to);
        recyclerView = findViewById(R.id.selectCustToList);
        DatabaseHelper db = new DatabaseHelper(this);
        customerList = db.selectAllCustomer();
        customerFrom = (CustomerModel) intent.getSerializableExtra("CustomerFrom");

        for (int i = 0; i < customerList.size(); i++){
            if (customerList.get(i).getId() == customerFrom.getId())
                customerList.remove(i);
        }
        setAdapter();
    }

    private void setAdapter() {
        CustomerRecyclerAdapter adapter = new CustomerRecyclerAdapter(customerList, SelectCustomerTo.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void nextActivity(int position) {
        CustomerModel customerTo = customerList.get(position);
        final AlertDialog.Builder enterAmount = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.enter_amount, null);
        enterAmount.setTitle("Enter the amount to transfer").setView(view).setCancelable(false);
        EditText enter = view.findViewById(R.id.enterAmount);

        enterAmount.setPositiveButton("Transfer", ((dialog, which) -> {})
        ).setNegativeButton("Cancel", ((dialog, which) -> {
            Toast.makeText(this, "The Transaction was cancelled", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SelectCustomerTo.this, ScrollingActivity.class);
            startActivity(intent);
        }));

        this.enterAmount = enterAmount.create();
        this.enterAmount.show();

        this.enterAmount.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener( v -> {
            if (enter.getText().toString() == "" || Float.parseFloat(enter.getText().toString()) == 0.0){
                Toast.makeText(this, "Please enter some amount", Toast.LENGTH_SHORT).show();
            } else if(Float.parseFloat(enter.getText().toString()) > customerFrom.getBalance()){
                Toast.makeText(this, "Entered amount exceed the balance of the user", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(SelectCustomerTo.this, TransactionSuccessfull.class);
                TransactionModel transaction = new TransactionModel(customerFrom.getName(), customerTo.getName(),
                        Float.parseFloat(enter.getText().toString()));
                DatabaseHelper db = new DatabaseHelper(SelectCustomerTo.this);
                db.addNewTransaction(transaction);
                startActivity(intent);
            }

        });


    }
}