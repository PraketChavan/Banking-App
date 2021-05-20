package com.example.bankingapp.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingapp.R;

public class MyViewHolder extends RecyclerView.ViewHolder{
    public TextView username, email, balance;
    private ClickListener clickListener;

    public MyViewHolder(final View itemView) {
        super(itemView);
        username = itemView.findViewById(R.id.userItem_Username);
        balance= itemView.findViewById(R.id.userItem_BalanceNum);
        email = itemView.findViewById(R.id.userItem_Email);
        itemView.setOnClickListener(view -> clickListener.onItemClick(view, getAdapterPosition()));
    }

    public interface ClickListener {
        void onItemClick(View view, int position);
    }

    public void setClickListener(ClickListener listener) {
        this.clickListener = listener;
    }
}