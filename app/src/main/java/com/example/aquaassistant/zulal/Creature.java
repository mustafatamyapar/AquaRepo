package com.example.aquaassistant.zulal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.aquaassistant.R;

import java.util.ArrayList;

public class Creature extends AppCompatActivity {
    GridView creatureGrid;
    ArrayList<Integer> creatureId;
    GridViewAdapter gridViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creature);
        creatureGrid =findViewById(R.id.grid_creatures);
        creatureId = new ArrayList<Integer>();
        gridViewAdapter = new GridViewAdapter(creatureId,Creature.this);
        gridViewAdapter.notifyDataSetChanged();
        creatureGrid.setAdapter(gridViewAdapter);

    }

}
