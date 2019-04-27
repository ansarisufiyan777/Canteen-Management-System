package com.andromap33.mcms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyOrders extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);
        show_entries();
    }

    public void show_entries() {
        DBHelper resDbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase mydb = resDbHelper.getReadableDatabase();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyOrders.this);

        Cursor c = mydb.rawQuery("SELECT * FROM " + StudentDBContract.Diet.TABLE_NAME
                +" WHERE " +  StudentDBContract.Diet.COLUMN_NAME_USERNAME +" = '" + prefs.getString(StudentDBContract.Users.COLUMN_NAME,"Rizwan")+ "' ;", null);
        ArrayList<String> entries_list = new ArrayList<String>();
        int total_bill = 0;
        String username = "Rizwan";
        while (c.moveToNext()) {
            String str;
            String diet_Taken = c.getString(c.getColumnIndexOrThrow(StudentDBContract.Diet.COLUMN_NAME_DIET));
            username = c.getString(c.getColumnIndexOrThrow(StudentDBContract.Diet.COLUMN_NAME_USERNAME));
            int diet_Price = c.getInt(c.getColumnIndexOrThrow(StudentDBContract.Diet.COLUMN_NAME_PRICE));
            total_bill = total_bill + diet_Price;
            str = "Diet taken : " + diet_Taken + "\nPrice : " + diet_Price+ "\nUsername : " + username;
            entries_list.add(str);
        }

        final ArrayAdapter<String> entries_adapter = new ArrayAdapter<String>(this,
                R.layout.list_item_entry,
                R.id.list_item_entry_textview,
                entries_list);
        ListView entries_listview = (ListView) findViewById(R.id.listview_entries);
        entries_listview.setAdapter(entries_adapter);
        TextView totalBill = (TextView) findViewById(R.id.total_bill_textview);
        totalBill.setText("Total bill : " + total_bill);
        final String finalUsername = username;
        entries_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                alertOneButton(finalUsername);
            }
        });
    }

    /*
     * AlertDialog with one button.
     */
    public void alertOneButton(String username) {
        DBHelper resDbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase mydb = resDbHelper.getReadableDatabase();
        Cursor c;
        c = mydb.rawQuery("SELECT * FROM " + StudentDBContract.ResidentEntry1.TABLE_NAME
                + " WHERE " + StudentDBContract.ResidentEntry1.COLUMN_NAME_USERNAME + " = '" + username + "' LIMIT 1 ; ", null);

        String str = "";

        while (c.moveToNext()) {
            str = "Name: ";
            str += c.getString(c.getColumnIndexOrThrow(StudentDBContract.ResidentEntry1.COLUMN_NAME_NAME));
            str += "\nBlock No. ";
            str += c.getInt(c.getColumnIndexOrThrow(StudentDBContract.ResidentEntry1.COLUMN_NAME_BLOCK));
            str += "\nRoom No. ";
            str += c.getInt(c.getColumnIndexOrThrow(StudentDBContract.ResidentEntry1.COLUMN_NAME_ROOM));
            str += "\nSection: ";
            str += c.getString(c.getColumnIndexOrThrow(StudentDBContract.ResidentEntry1.COLUMN_NAME_SECTION));
            str += "\nPhone: ";
            str += c.getString(c.getColumnIndexOrThrow(StudentDBContract.ResidentEntry1.COLUMN_NAME_PHONE));
            str += "\nROll No: ";
            str += c.getInt(c.getColumnIndexOrThrow(StudentDBContract.ResidentEntry1.COLUMN_NAME_ROLLNO));
        }

        new AlertDialog.Builder(MyOrders.this)
                .setTitle(username)
                .setMessage(str)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                }).show();
    }




}
