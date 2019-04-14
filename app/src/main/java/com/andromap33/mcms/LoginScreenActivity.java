package com.andromap33.mcms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
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
        if (userName.equals("rizwan") && password.equals("0000")) {
            Intent main = new Intent(this, MainActivity.class);
            main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(main);
            finish();
        }else{
            Toast.makeText(this,"Incorrect username or password",Toast.LENGTH_LONG).show();
        }

    }
}