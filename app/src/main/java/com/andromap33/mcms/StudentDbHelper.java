package com.andromap33.mcms;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mcms.db";
    public static final String SQL_CREATE_ENTRIES1 =
            "CREATE TABLE IF NOT EXISTS " + StudentDBContract.ResidentEntry1.TABLE_NAME + " (" +
                    StudentDBContract.ResidentEntry1.COLUMN_NAME_NAME + " VARCHAR(50) ," +
                    StudentDBContract.ResidentEntry1.COLUMN_NAME_BLOCK + " INTEGER ," +
                    StudentDBContract.ResidentEntry1.COLUMN_NAME_ROOM + " INTEGER ," +
                    StudentDBContract.ResidentEntry1.COLUMN_NAME_SECTION + " VARCHAR(1) ," +
                    StudentDBContract.ResidentEntry1.COLUMN_NAME_PHONE + " VARCHAR(10) ," +
                    StudentDBContract.ResidentEntry1.COLUMN_NAME_USERNAME + " VARCHAR(20) ," +
                    StudentDBContract.ResidentEntry1.COLUMN_NAME_PASSWORD + " VARCHAR(50) ," +
                    StudentDBContract.ResidentEntry1.COLUMN_NAME_ROLLNO + " INTEGER " + " ) ;";

    public static final String SQL_CREATE_ENTRIES2 =
            "CREATE TABLE IF NOT EXISTS " + StudentDBContract.Diet.TABLE_NAME + " (" +
                    StudentDBContract.Diet.COLUMN_NAME_USERNAME + " INTEGER ," +
                    StudentDBContract.Diet.COLUMN_NAME_DIET + " VARCHAR(500) ," +
                    StudentDBContract.Diet.COLUMN_NAME_PRICE + " INTEGER " + " ) ;";

    private static final String SQL_DELETE_ENTRIES1 =
            "DROP TABLE IF EXISTS " + StudentDBContract.ResidentEntry1.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES2 =
            "DROP TABLE IF EXISTS " + StudentDBContract.Diet.TABLE_NAME;

    public StudentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES1);
        db.execSQL(SQL_CREATE_ENTRIES2);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES1);
        db.execSQL(SQL_DELETE_ENTRIES2);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}