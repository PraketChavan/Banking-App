package com.example.bankingapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingapp.Model.TransactionModel;
import com.example.bankingapp.R;

import java.util.ArrayList;
import java.util.Locale;

public class TransactionRecyclerAdapter extends RecyclerView.Adapter<TransactionRecyclerAdapter.MyViewHolder>{

    private ArrayList<TransactionModel> transactionList;
    private boolean customer;

    public TransactionRecyclerAdapter(ArrayList<TransactionModel> transactionList, boolean customer) {
        this.transactionList = transactionList;
        this.customer = customer;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView to, from, amount, date;

        public MyViewHolder(final View itemView) {
            super(itemView);
            to = itemView.findViewById(R.id.transItem_to);
            from = itemView.findViewById(R.id.transItem_from);
            amount = itemView.findViewById(R.id.transItem_amount);
            date = itemView.findViewById(R.id.transItem_date);
        }
    }

    @NonNull
    @Override
    public TransactionRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (!customer) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_popup, parent, false);
        }
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TransactionRecyclerAdapter.MyViewHolder holder, int position) {
        TransactionModel transaction = transactionList.get(position);
        holder.from.setText(transaction.getFrom());
        holder.to.setText(transaction.getTo());
        holder.amount.setText(String.format(Locale.getDefault(),"£ %.2f", transaction.getAmount()));
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }
}

