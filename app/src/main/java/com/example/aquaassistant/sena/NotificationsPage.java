package com.example.aquaassistant.sena;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aquaassistant.R;

import java.util.ArrayList;

public class NotificationsPage extends AppCompatActivity {

    ListView listViewNotif;
    ArrayAdapter notifAdapter;
    public static ArrayList<String> notifArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        notifAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,notifArray);
        listViewNotif.setAdapter(notifAdapter);

        notifAdapter.notifyDataSetChanged();
    }


}
