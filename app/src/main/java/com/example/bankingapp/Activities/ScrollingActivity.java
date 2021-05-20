package com.example.bankingapp.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.bankingapp.Adapters.CustomerRecyclerAdapter;
import com.example.bankingapp.Database.DatabaseHelper;
import com.example.bankingapp.Model.CustomerModel;
import com.example.bankingapp.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bankingapp.databinding.ActivityScrollingBinding;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {

    private ActivityScrollingBinding binding;
    private ArrayList<CustomerModel> customerList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());
        FloatingActionButton fab = binding.fab;

        recyclerView = findViewById(R.id.userList);

        DatabaseHelper db = new DatabaseHelper(ScrollingActivity.this);
        //db.delete();
        db.initialiseData();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScrollingActivity.this, TransactionHistory.class);
                startActivity(intent);
            }
        });
        customerList = db.selectAllCustomer();
        setAdapter();
    }

    public void onClick(View view){
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }

    private void setAdapter() {
        CustomerRecyclerAdapter adapter = new CustomerRecyclerAdapter(customerList, ScrollingActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void popUpAction(int position){
        Intent intent = new Intent(getApplicationContext(), PopUpActivity.class);
        intent.putExtra("CustomerId", customerList.get(position));
        startActivity(intent);
    }
}