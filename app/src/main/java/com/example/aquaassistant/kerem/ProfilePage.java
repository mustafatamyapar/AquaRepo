package com.example.aquaassistant.kerem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aquaassistant.R;
import com.example.aquaassistant.sena.SettingsMain;
import com.example.aquaassistant.zulal.Faqactivity;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class ProfilePage extends AppCompatActivity {
    private Button commentsButton;
    private Button logOutButton;
    private Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        commentsButton= findViewById(R.id.commentsButton);
        logOutButton = findViewById(R.id.logOutButton);
        settingsButton = findViewById(R.id.settingsButton);
        Slidr.attach(this);
    }
    public void openSettings(View view){
        Intent intent = new Intent( this, SettingsMain.class);
        startActivity(intent);
    }

}
