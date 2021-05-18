package com.example.bankingapp.Model;

public class TransactionModel {
    private int id;
    private int from;
    private int to;
    private float amount;

    public TransactionModel(int id, int from, int to, float amount) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public TransactionModel() {
    }
}
