package com.example.aquaassistant.sena;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.aquaassistant.R;

public class NotificationsPage extends AppCompatActivity {

    String feedingNotif;
    String waterChangeNotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        feedingNotif = "It is time to feed your creatures in ";
        waterChangeNotif = "It is time to change the water of ";


    }


}
