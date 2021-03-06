package com.andromap33.mcms;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.AdapterView;

import java.util.ArrayList;

public class StudentList extends BaseActivity {

    private Button pre_button;
    private Boolean do_search = false;
    private String room_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residents_list);
        Button btn1 = (Button) findViewById(R.id.block1);
        pre_button = btn1;
        show_list(btn1);
    }

    public void show_list(View view) {
        DBHelper resDbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase mydb = resDbHelper.getReadableDatabase();
        Cursor c;
        c = mydb.rawQuery("SELECT * FROM " + StudentDBContract.ResidentEntry1.TABLE_NAME + " ;", null);

        ArrayList<String> resident_array_list = new ArrayList<String>();
        ArrayList<Integer> resident_roll_nos = new ArrayList<Integer>();

        Boolean inside_loop = false;
        while (c.moveToNext()) {
            inside_loop = true;
            String str;
            String name = c.getString(c.getColumnIndexOrThrow(StudentDBContract.ResidentEntry1.COLUMN_NAME_NAME));
            int blockNo = c.getInt(c.getColumnIndexOrThrow(StudentDBContract.ResidentEntry1.COLUMN_NAME_BLOCK));
            int roomNo = c.getInt(c.getColumnIndexOrThrow(StudentDBContract.ResidentEntry1.COLUMN_NAME_ROOM));
            String section = c.getString(c.getColumnIndexOrThrow(StudentDBContract.ResidentEntry1.COLUMN_NAME_SECTION));
            String mobNo = c.getString(c.getColumnIndexOrThrow(StudentDBContract.ResidentEntry1.COLUMN_NAME_PHONE));
            int rollNo = c.getInt(c.getColumnIndexOrThrow(StudentDBContract.ResidentEntry1.COLUMN_NAME_ROLLNO));
            resident_roll_nos.add(rollNo);
            str = name + "  " + blockNo + "/" + roomNo + section + "  " + mobNo + "  " + rollNo;
            resident_array_list.add(str);
        }

        final ArrayList<Integer> roll_nos_list = resident_roll_nos;
        final ArrayAdapter<String> resident_adapter = new ArrayAdapter<String>(this,
                R.layout.list_item_resident,
                R.id.list_item_resident_textview,
                resident_array_list);
        ListView resident_listview = (ListView) findViewById(R.id.listview_resident);

        if (!inside_loop && do_search) {
            Toast.makeText(this, "Resident for entered room no. does not exists ...", Toast.LENGTH_SHORT).show();
        } else {
            resident_listview.setAdapter(resident_adapter);
        }
        do_search = false;
        resident_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String resident = resident_adapter.getItem(pos);
                int roll_no = roll_nos_list.get(pos);
                Intent intent = new Intent(getApplicationContext(), StudentDetailedActivity.class)/*.putExtra(Intent . EXTRA_TEXT , resident)*/;
                intent.putExtra(Intent.EXTRA_TEXT, roll_no + "");
                startActivity(intent);
            }
        });
        c.close();
    }

    public void add_new_resident(View view) {
        Intent intent = new Intent(this, AddNewStudent.class);
        startActivity(intent);
    }

    public void search_resident(View view) {
        EditText search_room = (EditText) findViewById(R.id.search_roomNo);
        room_no = search_room.getText().toString();
        if (room_no.length() == 0) {
            Toast.makeText(this, "Please enter the room no. to search .", Toast.LENGTH_SHORT).show();
        } else {
            do_search = true;
            show_list(pre_button);
        }
    }
}