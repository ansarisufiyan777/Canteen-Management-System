package com.andromap33.mcms;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //Hide Action Bar
        getSupportActionBar().hide();

    }

    public void createUser(View view) {
        String userName = ((EditText) findViewById(R.id.username)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        if (userName.length() <= 4 || password.length() <= 4) {
            Toast.makeText(this, "Please enter both fields .", Toast.LENGTH_SHORT).show();
        } else {
            DBHelper resDbHelper = new DBHelper(getApplicationContext());
            SQLiteDatabase mydb = resDbHelper.getWritableDatabase();
            // Creating a new map
            ContentValues values = new ContentValues();
            values.put(ResidentDBContract.Users.COLUMN_NAME, userName);
            values.put(ResidentDBContract.Users.COLUMN_PASSWORD, password);

            //Inserting a new row
            long newRowID = mydb.insert(
                    ResidentDBContract.Users.TABLE_NAME,
                    ResidentDBContract.Users.COLUMN_NAME,
                    values
            );
            Toast.makeText(this, "User created successfully.", Toast.LENGTH_SHORT).show();
            Intent back = new Intent(this, MainActivity.class);
            back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(back);
            finish();
        }

    }
}
