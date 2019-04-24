package com.andromap33.mcms;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mcms.db";
    public static final String SQL_CREATE_ENTRIES1 =
            "CREATE TABLE IF NOT EXISTS " + ResidentDBContract.ResidentEntry1.TABLE_NAME + " (" +
                    ResidentDBContract.ResidentEntry1.COLUMN_NAME_NAME + " VARCHAR(50) ," +
                    ResidentDBContract.ResidentEntry1.COLUMN_NAME_BLOCK + " INTEGER ," +
                    ResidentDBContract.ResidentEntry1.COLUMN_NAME_ROOM + " INTEGER ," +
                    ResidentDBContract.ResidentEntry1.COLUMN_NAME_SECTION + " VARCHAR(1) ," +
                    ResidentDBContract.ResidentEntry1.COLUMN_NAME_PHONE + " VARCHAR(10) ," +
                    ResidentDBContract.ResidentEntry1.COLUMN_NAME_USERNAME + " VARCHAR(20) ," +
                    ResidentDBContract.ResidentEntry1.COLUMN_NAME_PASSWORD + " VARCHAR(50) ," +
                    ResidentDBContract.ResidentEntry1.COLUMN_NAME_ROLLNO + " INTEGER " + " ) ;";

    public static final String SQL_CREATE_ENTRIES2 =
            "CREATE TABLE IF NOT EXISTS " + ResidentDBContract.Diet.TABLE_NAME + " (" +
                    ResidentDBContract.Diet.COLUMN_NAME_ROLLNO + " INTEGER ," +
                    ResidentDBContract.Diet.COLUMN_NAME_DIET + " VARCHAR(500) ," +
                    ResidentDBContract.Diet.COLUMN_NAME_PRICE + " INTEGER " + " ) ;";


    public static final String SQL_CREATE_USER =
            "CREATE TABLE IF NOT EXISTS " + ResidentDBContract.Users.TABLE_NAME+ " (" +
                    ResidentDBContract.Users.COLUMN_NAME + " VARCHAR(50) ," +
                    ResidentDBContract.Users.COLUMN_PASSWORD+ " VARCHAR(50) " + " ) ;";

    private static final String SQL_DELETE_ENTRIES1 =
            "DROP TABLE IF EXISTS " + ResidentDBContract.ResidentEntry1.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES2 =
            "DROP TABLE IF EXISTS " + ResidentDBContract.Diet.TABLE_NAME;

    private static final String SQL_DELETE_USERS =
            "DROP TABLE IF EXISTS " + ResidentDBContract.Users.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES1);
        db.execSQL(SQL_CREATE_ENTRIES2);
        db.execSQL(SQL_CREATE_USER);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES1);
        db.execSQL(SQL_DELETE_ENTRIES2);
        db.execSQL(SQL_DELETE_USERS);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}