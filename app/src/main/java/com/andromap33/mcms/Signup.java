package com.andromap33.mcms;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
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
            values.put(StudentDBContract.Users.COLUMN_NAME, userName);
            values.put(StudentDBContract.Users.COLUMN_PASSWORD, password);

            //Inserting a new row
            long newRowID = mydb.insert(
                    StudentDBContract.Users.TABLE_NAME,
                    StudentDBContract.Users.COLUMN_NAME,
                    values
            );
            Toast.makeText(this, "User created successfully.", Toast.LENGTH_SHORT).show();
            Intent back = new Intent(this, MainActivity.class);
            back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(back);
            finish();
        }
    }


    public void add_resident(View view) {
        EditText editname = (EditText) findViewById(R.id.name);
        EditText editblock = (EditText) findViewById(R.id.block_no);
        EditText editroom = (EditText) findViewById(R.id.room_no);
        EditText editsection = (EditText) findViewById(R.id.section);
        EditText editmob = (EditText) findViewById(R.id.mob_no);
        EditText editroll = (EditText) findViewById(R.id.roll_no);
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);

        String eName = editname.getText().toString();
        String eBlock = editblock.getText().toString();
        String eRoom = editroom.getText().toString();
        String eSection = editsection.getText().toString();
        String ePhone = editmob.getText().toString();
        String eRollNo = editroll.getText().toString();
        String eUsername = username.getText().toString();
        String ePassword = password.getText().toString();

        if (eName.length() == 0 || eBlock.length() == 0 || eRoom.length() == 0 || eSection.length() == 0 || ePhone.length() == 0 || eRollNo.length() == 0) {
            Toast.makeText(this, "Please fill all the fields .", Toast.LENGTH_SHORT).show();
        } else {
            DBHelper resDbHelper = new DBHelper(getApplicationContext());
            SQLiteDatabase mydb = resDbHelper.getWritableDatabase();

            // Creating a new map
            ContentValues values = new ContentValues();
            values.put(StudentDBContract.ResidentEntry1.COLUMN_NAME_NAME, eName);
            values.put(StudentDBContract.ResidentEntry1.COLUMN_NAME_BLOCK, eBlock);
            values.put(StudentDBContract.ResidentEntry1.COLUMN_NAME_ROOM, eRoom);
            values.put(StudentDBContract.ResidentEntry1.COLUMN_NAME_SECTION, eSection);
            values.put(StudentDBContract.ResidentEntry1.COLUMN_NAME_PHONE, ePhone);
            values.put(StudentDBContract.ResidentEntry1.COLUMN_NAME_ROLLNO, eRollNo);
            values.put(StudentDBContract.ResidentEntry1.COLUMN_NAME_USERNAME, eUsername);
            values.put(StudentDBContract.ResidentEntry1.COLUMN_NAME_PASSWORD, ePassword);

            //Inserting a new row
            long newRowID = mydb.insert(
                    StudentDBContract.ResidentEntry1.TABLE_NAME,
                    StudentDBContract.ResidentEntry1.COLUMN_NAME_NAME,
                    values
            );
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(StudentDBContract.Users.COLUMN_NAME, eUsername);
            editor.commit();

            Toast.makeText(this, "User created successfully.", Toast.LENGTH_SHORT).show();
            Intent back = new Intent(this, MainActivity.class);
            back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(back);
            finish();
        }
    }
}
