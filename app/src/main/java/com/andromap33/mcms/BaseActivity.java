package com.andromap33.mcms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Asna on 1/27/2019.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        MenuItem viewOrders = menu.findItem(R.id.viewOrders);
        MenuItem viewUsers = menu.findItem(R.id.viewUsers);
        MenuItem addMenu = menu.findItem(R.id.addMenu);
        MenuItem myOrders = menu.findItem(R.id.myOrders);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = prefs.getString(StudentDBContract.Users.COLUMN_NAME, "Rizwan");
        if(userName.equals("Rizwan") || userName.equals("Anas")){
            viewOrders.setVisible(true);
            viewUsers.setVisible(true);
            addMenu.setVisible(true);
        }else{
            addMenu.setVisible(false);
            viewOrders.setVisible(false);
            viewUsers.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                Intent i = new Intent(this, LoginScreenActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                AppSharedPreference.setFlag(this, false);
                startActivity(i);
                finish();
                return true;
            case R.id.info:
                startActivity(new Intent(this, Introduction.class));
                return true;
            case R.id.viewOrders:
                Intent intent2 = new Intent(this, ViewOrders.class);
                startActivity(intent2);
                return true;
            case R.id.addMenu:
                Intent intent3 = new Intent(this, AddTodayMenu.class);
                startActivity(intent3);
                return true;
            case R.id.viewUsers:
                Intent intent1 = new Intent(this, StudentList.class);
                startActivity(intent1);
                return true;
            case R.id.myOrders:
                Intent intent4 = new Intent(this, MyOrders.class);
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
