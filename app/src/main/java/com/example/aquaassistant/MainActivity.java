package com.example.aquaassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aquaassistant.sena.SettingsMain;
import com.example.aquaassistant.zeynep.TanksPageActivity;

public class MainActivity extends AppCompatActivity {
    Button goTanks;
    //Button settingsTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goTanks = findViewById(R.id.button);
        //settingsTest = findViewById(R.id.settingsTest);

        goTanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TanksPageActivity.class);
                startActivity(intent);
            }
        });

        /* settings sayfasına gitmeye çalışırken çöküp kapanıyo :(
        settingsTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsMain.class);
                startActivity(intent);
            }
        });
        */
    }

}
