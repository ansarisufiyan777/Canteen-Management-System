package com.andromap33.mcms;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewOrders extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);
        show_entries();
    }

    public void show_entries() {
        ResidentDbHelper resDbHelper = new ResidentDbHelper(getApplicationContext());
        SQLiteDatabase mydb = resDbHelper.getReadableDatabase();
        Cursor c = mydb.rawQuery("SELECT * FROM " + ResidentDBContract.ResidentEntry2.TABLE_NAME+";", null);
        ArrayList<String> entries_list = new ArrayList<String>();
        int total_bill = 0;
        while (c.moveToNext()) {
            String str;
            String diet_Taken = c.getString(c.getColumnIndexOrThrow(ResidentDBContract.ResidentEntry2.COLUMN_NAME_DIET));
            int diet_Price = c.getInt(c.getColumnIndexOrThrow(ResidentDBContract.ResidentEntry2.COLUMN_NAME_PRICE));
            total_bill = total_bill + diet_Price;
            str = "Diet taken : " + diet_Taken + "\nPrice : " + diet_Price;
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
    }
}
