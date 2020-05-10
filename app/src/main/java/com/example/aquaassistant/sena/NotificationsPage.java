package com.example.aquaassistant.sena;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zeynep.TanksPageActivity;

import java.util.ArrayList;


public class NotificationsPage extends AppCompatActivity {

    public static SQLiteDatabase notifDatabase;
    ListView listViewNotif;
    ArrayAdapter notifAdapter;
    ArrayList<String> notifArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        try {
            listViewNotif = findViewById(R.id.listViewNotif);
            notifAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notifArray);
            listViewNotif.setAdapter(notifAdapter);
            notifAdapter.notifyDataSetChanged();

            notifDatabase = this.openOrCreateDatabase("Notifs", MODE_PRIVATE, null);
            notifDatabase.execSQL("CREATE TABLE IF NOT EXISTS notifs (id INTEGER PRIMARY KEY, notifText VARCHAR, tankName VARCHAR)");

            Cursor cursor = notifDatabase.rawQuery("SELECT * FROM notifs", null);
            int notifTextIndex = cursor.getColumnIndex("notifText");
            int tankNameIndex = cursor.getColumnIndex("tankName");
            while (cursor.moveToNext()) {
                notifArray.add(cursor.getString(notifTextIndex) + cursor.getString(tankNameIndex));
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

