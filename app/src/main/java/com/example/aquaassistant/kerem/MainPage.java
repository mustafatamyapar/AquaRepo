package com.example.aquaassistant.kerem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aquaassistant.R;
import com.example.aquaassistant.mustafa.EncyclopediaPage;
import com.example.aquaassistant.sena.SettingsMain;
import com.example.aquaassistant.zeynep.TankPageActivity;
import com.example.aquaassistant.zulal.Faqactivity;


public class MainPage extends AppCompatActivity {

    private Button tanksButton;
    private Button profileButton;
    private Button settingButton;
    private Button encyclopediaButton;
    private Button remindersButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        tanksButton= findViewById(R.id.tankButton);
        profileButton = findViewById(R.id.profileButton);
        settingButton = findViewById(R.id.settingsButton);
        encyclopediaButton = findViewById(R.id.encyclopedia);
        remindersButton = findViewById(R.id.button7);

    }
    public void openSettings(View view){
        Intent intent = new Intent( this, SettingsMain.class);
        startActivity(intent);
    }
    public void openTanks(View view){
        Intent intent = new Intent( this, TankPageActivity.class);
        startActivity(intent);
    }
    public void openEncyclopedia(View view){
        Intent intent = new Intent( this, EncyclopediaPage.class);
        startActivity(intent);
    }
    public void openProfile(View view){
        Intent intent = new Intent( this, ProfilePage.class);
        startActivity(intent);
    }
}
