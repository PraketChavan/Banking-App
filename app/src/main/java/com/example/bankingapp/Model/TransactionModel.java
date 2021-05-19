package com.example.bankingapp.Model;

public class TransactionModel {
    private int id;
    private String from;
    private String to;
    private float amount;

    public TransactionModel(int id, String from, String to, float amount) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public TransactionModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
