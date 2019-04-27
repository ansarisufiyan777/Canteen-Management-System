package com.andromap33.mcms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Rizwan on 11/3/17.
 */

public class LoginScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        if (AppSharedPreference.getFlag(getApplicationContext()) == true) {
            Intent main = new Intent(this, MainActivity.class);
            main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(main);
            finish();
        }
        //Hide Action Bar
        getSupportActionBar().hide();

    }

    public void isValidUser(View view) {
        String userName = ((EditText) findViewById(R.id.username)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
//        if (userName.equals("rizwan") && password.equals("0000")) {
//            Intent main = new Intent(this, MainActivity.class);
//            main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(main);
//            finish();
//        } else {
//            Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_LONG).show();
//        }

        validate();
    }

    private void validate() {

        String userName = ((EditText) findViewById(R.id.username)).getText().toString().trim();
        String password = ((EditText) findViewById(R.id.password)).getText().toString().trim();
        if (userName.length() < 1 || password.length() < 1) {
            Toast.makeText(this, "Please enter both fields .", Toast.LENGTH_SHORT).show();
        } else {

            DBHelper resDbHelper = new DBHelper(getApplicationContext());
            SQLiteDatabase mydb = resDbHelper.getReadableDatabase();
            Cursor c = mydb.rawQuery("SELECT * FROM " + StudentDBContract.ResidentEntry1.TABLE_NAME +
                    " WHERE " + StudentDBContract.ResidentEntry1.COLUMN_NAME_USERNAME + " = '" + userName +
                    "' AND " + StudentDBContract.ResidentEntry1.COLUMN_NAME_PASSWORD + " = '" + password + "' ;", null);
            while (c.moveToNext()) {
                String name = c.getString(c.getColumnIndexOrThrow(StudentDBContract.Users.COLUMN_NAME));
                if (name.length() > 0) {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(StudentDBContract.Users.COLUMN_NAME, userName);
                    editor.commit();
                    Intent main = new Intent(this, MainActivity.class);
                    main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(main);
                    finish();
                    Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_LONG).show();
                }
            }


        }

    }

    public void openSignUp(View view) {
        startActivity(new Intent(this, Signup.class));

    }
}