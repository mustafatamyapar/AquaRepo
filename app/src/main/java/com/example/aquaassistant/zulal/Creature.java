package com.example.aquaassistant.zulal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zeynep.RemoveCreatureActivity;

import java.util.ArrayList;

public class Creature extends AppCompatActivity {
    GridView creatureGrid;
    ArrayList<Integer> creatureId;
    GridViewAdapter gridViewAdapter;
    SQLiteDatabase creatureDatabase;
    String tankName;
    String tankId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creature);
        creatureGrid = findViewById(R.id.grid_creatures);
        creatureId = new ArrayList<>();
        gridViewAdapter = new GridViewAdapter(creatureId, Creature.this);
        gridViewAdapter.notifyDataSetChanged();
        creatureGrid.setAdapter(gridViewAdapter);
        creatureDatabase =Creature.this.openOrCreateDatabase("Creatures" , MODE_PRIVATE, null);

//        Intent intent = getIntent();
//        tankId = intent.getStringExtra("tankId");
//        SQLiteDatabase tanksDatabase = Creature.this.openOrCreateDatabase("Tanks" ,MODE_PRIVATE, null);
//        Cursor tankCursor = tanksDatabase.rawQuery("SELECT tankname FROM tanks WHERE id=?", new String[]{tankId});
//        while (tankCursor.moveToNext()){
//            tankName = tankCursor.getString(tankCursor.getColumnIndex("tankname"));
//        }
//        tankCursor.close();

        Cursor creatureCursor = creatureDatabase.rawQuery("SELECT * FROM creatures" , null);
        int idIndex = creatureCursor.getColumnIndex("id");
        if (creatureCursor.getCount() != 0) {
            while (creatureCursor.moveToNext()) {
                creatureId.add(creatureCursor.getInt(idIndex));
            }
        }
        creatureCursor.close();
    }

}
