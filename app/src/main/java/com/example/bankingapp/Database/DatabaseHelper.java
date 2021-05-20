package com.example.bankingapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bankingapp.Model.CustomerModel;
import com.example.bankingapp.Model.TransactionModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Customer table schema
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_ID = "cID";
    public static final String COLUMN_CUSTOMER_NAME = "cName";
    public static final String COLUMN_CUSTOMER_EMAIL = "cEmail";
    public static final String COLUMN_CUSTOMER_BALANCE = "cBalance";

    public static final String TRANSACTION_TABLE = "TRANSACTION_TABLE";
    public static final String COLUMN_TRANSACTION_ID = "tID";
    public static final String COLUMN_TRANSACTION_FROM = "tFrom";
    public static final String COLUMN_TRANSACTION_TO = "tTo";
    public static final String COLUMN_TRANSACTION_AMOUNT = "tAmount";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "banking.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStatement1, createStatement2;
        db.execSQL("PRAGMA foreign_keys = ON");

        createStatement1 = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_EMAIL + " TEXT, " + COLUMN_CUSTOMER_BALANCE + " REAL);";

        createStatement2 = "CREATE TABLE " + TRANSACTION_TABLE + " (" + COLUMN_TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TRANSACTION_FROM + " INTEGER, " + COLUMN_TRANSACTION_TO + " INTEGER, " + COLUMN_TRANSACTION_AMOUNT + " REAL, " +
                "FOREIGN KEY(" + COLUMN_TRANSACTION_TO + ") REFERENCES " + CUSTOMER_TABLE + "(" + COLUMN_CUSTOMER_ID + ")," +
                "FOREIGN KEY(" + COLUMN_TRANSACTION_TO + ") REFERENCES " + CUSTOMER_TABLE + "(" + COLUMN_CUSTOMER_ID + "));";

        db.execSQL(createStatement1);
        db.execSQL(createStatement2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void initialiseData() {

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + CUSTOMER_TABLE, null);
        if (!cursor.moveToFirst()) {
            db.execSQL("INSERT INTO " + CUSTOMER_TABLE + "(" + COLUMN_CUSTOMER_NAME + ", " + COLUMN_CUSTOMER_EMAIL + ", " +
                    COLUMN_CUSTOMER_BALANCE + ") VALUES ('Elisha', 'Elisha@gmail.com', 7000.00);");
            db.execSQL("INSERT INTO " + CUSTOMER_TABLE + "(" + COLUMN_CUSTOMER_NAME + ", " + COLUMN_CUSTOMER_EMAIL + ", " +
                    COLUMN_CUSTOMER_BALANCE + ") VALUES ('Mathew', 'mat@outlook.com', 7000.00);");
            db.execSQL("INSERT INTO " + CUSTOMER_TABLE + "(" + COLUMN_CUSTOMER_NAME + ", " + COLUMN_CUSTOMER_EMAIL + ", " +
                    COLUMN_CUSTOMER_BALANCE + ") VALUES ('Parth', 'parth@gmail.com', 7000.00);");
            db.execSQL("INSERT INTO " + CUSTOMER_TABLE + "(" + COLUMN_CUSTOMER_NAME + ", " + COLUMN_CUSTOMER_EMAIL + ", " +
                    COLUMN_CUSTOMER_BALANCE + ") VALUES ('Reena', 'reena@hotmail.com', 7000.00);");
            db.execSQL("INSERT INTO " + CUSTOMER_TABLE + "(" + COLUMN_CUSTOMER_NAME + ", " + COLUMN_CUSTOMER_EMAIL + ", " +
                    COLUMN_CUSTOMER_BALANCE + ") VALUES ('Bhaumik', 'bh@gmail.com', 7000.00);");
            db.execSQL("INSERT INTO " + CUSTOMER_TABLE + "(" + COLUMN_CUSTOMER_NAME + ", " + COLUMN_CUSTOMER_EMAIL + ", " +
                    COLUMN_CUSTOMER_BALANCE + ") VALUES ('Harshad', 'harshad@gmail.com', 7000.00);");
            db.execSQL("INSERT INTO " + CUSTOMER_TABLE + "(" + COLUMN_CUSTOMER_NAME + ", " + COLUMN_CUSTOMER_EMAIL + ", " +
                    COLUMN_CUSTOMER_BALANCE + ") VALUES ('Praket', 'praket@yahoo.com', 7000.00);");
            db.execSQL("INSERT INTO " + CUSTOMER_TABLE + "(" + COLUMN_CUSTOMER_NAME + ", " + COLUMN_CUSTOMER_EMAIL + ", " +
                    COLUMN_CUSTOMER_BALANCE + ") VALUES ('Manisha', 'manisha@gmail.com', 7000.00);");
            db.execSQL("INSERT INTO " + CUSTOMER_TABLE + "(" + COLUMN_CUSTOMER_NAME + ", " + COLUMN_CUSTOMER_EMAIL + ", " +
                    COLUMN_CUSTOMER_BALANCE + ") VALUES ('Meha', 'Meha@yahoo.com', 7000.00);");
            db.execSQL("INSERT INTO " + CUSTOMER_TABLE + "(" + COLUMN_CUSTOMER_NAME + ", " + COLUMN_CUSTOMER_EMAIL + ", " +
                    COLUMN_CUSTOMER_BALANCE + ") VALUES ('Harsh', 'harsh@gmail.com', 7000.00);");

        }
        cursor.close();

        cursor = db.rawQuery("SELECT * FROM " + TRANSACTION_TABLE, null);
        if (!cursor.moveToFirst()) {
            db.execSQL("INSERT INTO " + TRANSACTION_TABLE + "(" + COLUMN_TRANSACTION_TO + ", " + COLUMN_TRANSACTION_FROM + ", " +
                    COLUMN_TRANSACTION_AMOUNT + ") VALUES (1, 3, 70.00);");
            db.execSQL("INSERT INTO " + TRANSACTION_TABLE + "(" + COLUMN_TRANSACTION_TO + ", " + COLUMN_TRANSACTION_FROM + ", " +
                    COLUMN_TRANSACTION_AMOUNT + ") VALUES (4, 6, 83.00);");
            db.execSQL("INSERT INTO " + TRANSACTION_TABLE + "(" + COLUMN_TRANSACTION_TO + ", " + COLUMN_TRANSACTION_FROM + ", " +
                    COLUMN_TRANSACTION_AMOUNT + ")VALUES (2, 8, 999.99);");
            db.execSQL("INSERT INTO " + TRANSACTION_TABLE + "(" + COLUMN_TRANSACTION_TO + ", " + COLUMN_TRANSACTION_FROM + ", " +
                    COLUMN_TRANSACTION_AMOUNT + ") VALUES (9, 5, 100.50);");
            db.execSQL("INSERT INTO " + TRANSACTION_TABLE + "(" + COLUMN_TRANSACTION_TO + ", " + COLUMN_TRANSACTION_FROM + ", " +
                    COLUMN_TRANSACTION_AMOUNT + ") VALUES (5, 3, 100.40);");
        }
        cursor.close();
        db.close();
    }

    public ArrayList<CustomerModel> selectAllCustomer() {
        ArrayList<CustomerModel> resultSet = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + CUSTOMER_TABLE + ";";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                float balance = cursor.getFloat(3);
                resultSet.add(new CustomerModel(id, name, email, balance));
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return resultSet;
    }

    public CustomerModel selectCustomer(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_CUSTOMER_ID + " = " + id + ";";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            float balance = cursor.getFloat(3);
            db.close();
            cursor.close();
            return new CustomerModel(id, name, email, balance);
        }
        return null;
    }

    public CustomerModel selectCustomerName(String name){
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_CUSTOMER_NAME + " = \'" + name + "\';";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        return new CustomerModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                cursor.getFloat(3));
    }

    public boolean addNewCustomer(CustomerModel customer) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CUSTOMER_NAME, customer.getName());
        cv.put(COLUMN_CUSTOMER_EMAIL, customer.getEmail());
        cv.put(COLUMN_CUSTOMER_BALANCE, customer.getBalance());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);

        return insert != -1;
    }

    public ArrayList<TransactionModel> selectAllTransaction() {
        ArrayList<TransactionModel> resultSet = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TRANSACTION_TABLE + ";";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String from = selectCustomer(cursor.getInt(1)).getName();
                String to = selectCustomer(cursor.getInt(2)).getName();
                float amount = cursor.getFloat(3);
                resultSet.add(new TransactionModel(id, from, to , amount));
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return resultSet;
    }

    public ArrayList<TransactionModel> selectCustomerTransaction(int customerId) {
        ArrayList<TransactionModel> resultSet = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TRANSACTION_TABLE + " WHERE " + COLUMN_TRANSACTION_TO + " = " + customerId +
                " OR " + COLUMN_TRANSACTION_FROM + " = " + customerId  + ";";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                int id  = cursor.getInt(0);
                String from = selectCustomer(cursor.getInt(1)).getName();
                String to = selectCustomer(cursor.getInt(2)).getName();
                float amount = cursor.getFloat(3);
                resultSet.add(new TransactionModel(id, from, to, amount));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return resultSet;
    }

    public boolean addNewTransaction(TransactionModel transaction){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TRANSACTION_FROM, selectCustomerName(transaction.getFrom()).getId());
        cv.put(COLUMN_TRANSACTION_TO, selectCustomerName(transaction.getTo()).getId());
        cv.put(COLUMN_TRANSACTION_AMOUNT, transaction.getAmount());

        long insert = db.insert(TRANSACTION_TABLE, null, cv);
        reduceBalance(selectCustomerName(transaction.getFrom()), selectCustomerName(transaction.getTo()),
                transaction.getAmount());
        return insert != -1;
    }

    public void reduceBalance(CustomerModel customerModel, CustomerModel customerModel2, float amount) {
        SQLiteDatabase db  = getWritableDatabase();
        String query = "UPDATE " + CUSTOMER_TABLE + " SET " + COLUMN_CUSTOMER_BALANCE + " = " + COLUMN_CUSTOMER_BALANCE +
                " - " + amount + " WHERE " + COLUMN_CUSTOMER_ID + " = " + customerModel.getId() + ";";
        String query2 = "UPDATE " + CUSTOMER_TABLE + " SET " + COLUMN_CUSTOMER_BALANCE + " = " + COLUMN_CUSTOMER_BALANCE +
                " + " + amount + " WHERE " + COLUMN_CUSTOMER_ID + " = " + customerModel2.getId() + ";";
        db.execSQL(query);
        db.execSQL(query2);
    }

}