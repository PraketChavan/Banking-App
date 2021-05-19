package com.example.bankingapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingapp.Model.CustomerModel;
import com.example.bankingapp.R;

import java.util.ArrayList;
import java.util.Locale;

public class CustomerRecyclerAdapter extends RecyclerView.Adapter<CustomerRecyclerAdapter.MyViewHolder>{

    private ArrayList<CustomerModel> customerList;

    public CustomerRecyclerAdapter(ArrayList<CustomerModel> customerList) {
        this.customerList = customerList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView username, email, balance;

        public MyViewHolder(final View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.userItem_Username);
            balance= itemView.findViewById(R.id.userItem_BalanceNum);
            email = itemView.findViewById(R.id.userItem_Email);
        }
    }

    @NonNull
    @Override
    public CustomerRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerRecyclerAdapter.MyViewHolder holder, int position) {
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
