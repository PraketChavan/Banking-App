package com.example.bankingapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingapp.Activities.ScrollingActivity;
import com.example.bankingapp.Model.CustomerModel;
import com.example.bankingapp.R;

import java.util.ArrayList;
import java.util.Locale;

public class CustomerRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder>{

    private ArrayList<CustomerModel> customerList;
    private ScrollingActivity scrollingActivity;

    public CustomerRecyclerAdapter(ArrayList<CustomerModel> customerList, ScrollingActivity scrollingActivity) {
        this.customerList = customerList;
        this.scrollingActivity = scrollingActivity;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(viewItem);
        viewHolder.setClickListener((view, position) -> scrollingActivity.popUpAction(position));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CustomerModel customer = customerList.get(position);
        holder.username.setText(customer.getName());
        holder.email.setText(customer.getEmail());
        holder.balance.setText(String.format(Locale.getDefault(),"£ %.2f", customer.getBalance()));
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

}
