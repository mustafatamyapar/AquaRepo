package com.example.aquaassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aquaassistant.mustafa.EncyclopediaPage;
import com.example.aquaassistant.zeynep.TanksPageActivity;

public class MainActivity extends AppCompatActivity {
    Button goTanks;
    Button goEncyclopedia;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    goTanks = findViewById(R.id.button);
    goTanks.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, TanksPageActivity.class);
            startActivity(intent);
        }

        goEncyclopedia = findViewById(R.id.button2);
    goEncyclopedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EncyclopediaPage.class);
                startActivity(intent);
            }

    });
}
}
