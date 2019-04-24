package com.andromap33.mcms;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.widget.EditText;

public class AddNewEntry extends BaseActivity {

    public String roll_NO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_entry);
        Intent detailedIntent = this.getIntent();
        if (detailedIntent != null && detailedIntent.hasExtra(Intent.EXTRA_TEXT)) {
            roll_NO = detailedIntent.getStringExtra(Intent.EXTRA_TEXT);
        }
        EditText e_diet = (EditText) findViewById(R.id.diet_taken);
        EditText e_price = (EditText) findViewById(R.id.diet_price);
        AppSharedPreference.setFlag(getApplicationContext(), true);
        String diet = AppSharedPreference.getTodayMenu(getApplicationContext());
        String price = AppSharedPreference.getPrice(getApplicationContext());
        e_diet.setText(diet);
        e_price.setText(price);
    }

    public void add_new_entry(View view) {
        EditText dietTaken = (EditText) findViewById(R.id.diet_taken);
        EditText dietPrice = (EditText) findViewById(R.id.diet_price);

        String diet_Taken = dietTaken.getText().toString();
        String diet_Price = dietPrice.getText().toString();

        if (diet_Taken.length() == 0 || diet_Price.length() == 0) {
            Toast.makeText(this, "Please enter both fields .", Toast.LENGTH_SHORT).show();
        } else {
            DBHelper resDbHelper = new DBHelper(getApplicationContext());
            SQLiteDatabase mydb = resDbHelper.getWritableDatabase();
            // Creating a new map
            ContentValues values = new ContentValues();
            values.put(ResidentDBContract.Diet.COLUMN_NAME_ROLLNO, roll_NO);
            values.put(ResidentDBContract.Diet.COLUMN_NAME_DIET, diet_Taken);
            values.put(ResidentDBContract.Diet.COLUMN_NAME_PRICE, diet_Price);

            //Inserting a new row
            long newRowID = mydb.insert(
                    ResidentDBContract.Diet.TABLE_NAME,
                    ResidentDBContract.Diet.COLUMN_NAME_PRICE,
                    values
            );
            Toast.makeText(this, "Entry added .", Toast.LENGTH_SHORT).show();
            Intent back = new Intent(this, ResidentDetailedActivity.class).putExtra(Intent.EXTRA_TEXT, roll_NO);
            back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(back);
            finish();
        }
    }
}