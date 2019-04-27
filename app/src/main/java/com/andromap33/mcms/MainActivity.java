package com.andromap33.mcms;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AppSharedPreference.setFlag(getApplicationContext(), true);
        String menu = AppSharedPreference.getTodayMenu(getApplicationContext());
        String price = AppSharedPreference.getPrice(getApplicationContext());
        TextView menu_txt = (TextView) findViewById(R.id.today_menu_text);
        menu_txt.setText("Today's menu : \n" + menu);
        TextView price_txt = (TextView) findViewById(R.id.price_text);
        price_txt.setText("Price : " + price);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        createListView();
    }

    public void list_residents(View view) {
        Intent intent1 = new Intent(this, StudentList.class);
        startActivity(intent1);
    }

    public void view_orders(View view) {
        Intent intent2 = new Intent(this, ViewOrders.class);
        startActivity(intent2);
    }

    public void add_today_menu(View view) {
        Intent intent3 = new Intent(this, AddTodayMenu.class);
        startActivity(intent3);
    }

    public void rate_us(View view) {

    }


    private void createListView(){
        DBHelper resDbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase mydb = resDbHelper.getReadableDatabase();
        Cursor c = mydb.rawQuery("SELECT * FROM " + StudentDBContract.Menus.TABLE_NAME +" ; ", null);
        final ArrayList<Menu> arrayList = new ArrayList<>();
        while (c.moveToNext()) {
            Menu m = new Menu();
            m.setMenuName(c.getString(c.getColumnIndexOrThrow(StudentDBContract.Menus.COLUMN_NAME)));
            m.setPrice(c.getString(c.getColumnIndexOrThrow(StudentDBContract.Menus.COLUMN_PRICE)));
            arrayList.add(m);
        }
        ListView list;
        CustomList listAdapter = new
                CustomList(MainActivity.this,  arrayList);
        list=(ListView)findViewById(R.id.listView);
        list.setAdapter(listAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                alertTwoButtons(arrayList.get(i));
            }
        });
    }

    public class CustomList extends ArrayAdapter<Menu> {
        private final Activity context;
        private ArrayList<Menu> arrayList;

        public CustomList(Activity context,ArrayList<Menu> arrayList) {
            super(context, R.layout.menu_item,arrayList);
            this.context = context;
            this.arrayList = arrayList;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView= inflater.inflate(R.layout.menu_item, null, true);
            TextView txtTitle = (TextView) rowView.findViewById(R.id.today_menu_text);
            TextView price = (TextView) rowView.findViewById(R.id.price_text);
            txtTitle.setText("Item: " + arrayList.get(position).getMenuName());
            price.setText("Price: "+ arrayList.get(position).getPrice());
            return rowView;
        }
    }

    public void alertTwoButtons(final Menu menu) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(menu.getMenuName())
                .setMessage("Order "+ menu.getMenuName() + " for: "+menu.getPrice())
                .setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                addDiet(menu);
                                Toast.makeText(MainActivity.this,"Thank you! You're awesome too!",Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private void addDiet(Menu menu){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        DBHelper resDbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase mydb = resDbHelper.getWritableDatabase();
        // Creating a new map
        ContentValues values = new ContentValues();
        values.put(StudentDBContract.Diet.COLUMN_NAME_USERNAME, prefs.getString(StudentDBContract.Users.COLUMN_NAME , "Rizwan"));
        values.put(StudentDBContract.Diet.COLUMN_NAME_DIET, menu.getMenuName());
        values.put(StudentDBContract.Diet.COLUMN_NAME_PRICE, menu.getPrice());

        //Inserting a new row
        long newRowID = mydb.insert(
                StudentDBContract.Diet.TABLE_NAME,
                StudentDBContract.Diet.COLUMN_NAME_PRICE,
                values
        );
    }
}