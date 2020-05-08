package com.example.aquaassistant.zulal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.aquaassistant.R;

public class Creature extends AppCompatActivity {
    ImageButton searchBut;
    ImageView header ;
    GridView creatureGrid;
    EditText searchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creature);
        searchBut =findViewById(R.id.searchButton);
        header = findViewById(R.id.header_creature);
        creatureGrid =findViewById(R.id.grid_creatures);
        searchBox = findViewById(R.id.serachBox);

    }
    public void searchCreature( View view){

    }
}
