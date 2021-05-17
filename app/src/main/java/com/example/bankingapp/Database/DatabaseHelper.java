package com.example.bankingapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
                "FOREIGN KEY(" + COLUMN_TRANSACTION_TO + ") REFERENCES " + CUSTOMER_TABLE +"("+ COLUMN_CUSTOMER_ID +")," +
                "FOREIGN KEY(" + COLUMN_TRANSACTION_TO + ") REFERENCES " + CUSTOMER_TABLE +"("+ COLUMN_CUSTOMER_ID +"));";

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
                    COLUMN_TRANSACTION_FROM + ") VALUES (1, 3, 70.00);");
            db.execSQL("INSERT INTO "  + TRANSACTION_TABLE + "(" + COLUMN_TRANSACTION_TO + ", " + COLUMN_TRANSACTION_FROM + ", " +
                    COLUMN_TRANSACTION_FROM + ") VALUES (4, 6, 83.00);");
            db.execSQL("INSERT INTO " + TRANSACTION_TABLE + "(" + COLUMN_TRANSACTION_TO + ", " + COLUMN_TRANSACTION_FROM + ", " +
                    COLUMN_TRANSACTION_FROM + ")VALUES (2, 8, 999.99);");
            db.execSQL("INSERT INTO " + TRANSACTION_TABLE + "(" + COLUMN_TRANSACTION_TO + ", " + COLUMN_TRANSACTION_FROM + ", " +
                    COLUMN_TRANSACTION_FROM + ") VALUES (9, 5, 100.50);");
            db.execSQL("INSERT INTO " + TRANSACTION_TABLE + "(" + COLUMN_TRANSACTION_TO + ", " + COLUMN_TRANSACTION_FROM + ", " +
                    COLUMN_TRANSACTION_FROM + ") VALUES (5, 3, 100.40);");
        }
        cursor.close();
    }
}
