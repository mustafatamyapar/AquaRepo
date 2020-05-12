package com.example.aquaassistant.kerem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aquaassistant.R;
import com.example.aquaassistant.mustafa.EncyclopediaPage;
import com.example.aquaassistant.sena.ToDoListPage;
import com.example.aquaassistant.sena.SettingsMain;
import com.example.aquaassistant.zeynep.FavouritePlacesActivity;
import com.example.aquaassistant.zeynep.TanksPageActivity;
import com.example.aquaassistant.zulal.Creature;
import com.example.aquaassistant.zulal.Faqactivity;
import com.r0adkll.slidr.Slidr;


public class MainPage extends AppCompatActivity {

    private Button tanksButton;
    private Button profileButton;
    private Button settingButton;
    private Button encyclopediaButton;
    private Button notifTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        notifTest = findViewById(R.id.notifTest);
        tanksButton= findViewById(R.id.tankButton);
        profileButton = findViewById(R.id.profileButton);
        settingButton = findViewById(R.id.settingsButton);
        encyclopediaButton = findViewById(R.id.encyclopedia);
        Slidr.attach(this);

    }
    public void openSettings(View view){
        Intent intent = new Intent( this, SettingsMain.class);
        startActivity(intent);
    }
    public void openTanks(View view){
        Intent intent = new Intent( this, TanksPageActivity.class);
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
    public void goMapPage(View view){
        Intent intent = new Intent( this , FavouritePlacesActivity.class);
        startActivity(intent);
    }

//    public void openNotif (View view) {
//        Intent intent = new Intent(this, NotificationsPage.class);
//        startActivity(intent);
//    }
    public void goCreatures(View view)
    {
        Intent intent = new Intent( MainPage.this, Creature.class);
        startActivity(intent);
    }
}
