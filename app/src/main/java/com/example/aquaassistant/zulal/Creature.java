package com.example.aquaassistant.zulal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zeynep.RemoveCreatureActivity;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;

public class Creature extends AppCompatActivity {
    GridView creatureGrid;
    ArrayList<Integer> creatureId;
    static GridViewAdapter gridViewAdapter;
    SQLiteDatabase creatureDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creature);
        Slidr.attach(this);
        creatureGrid = findViewById(R.id.grid_creatures);
        creatureId = new ArrayList<>();
        gridViewAdapter = new GridViewAdapter(creatureId, Creature.this);
        gridViewAdapter.notifyDataSetChanged();
        creatureGrid.setAdapter(gridViewAdapter);
        creatureDatabase =Creature.this.openOrCreateDatabase("Creatures" , MODE_PRIVATE, null);
        Cursor creatureCursor = creatureDatabase.rawQuery("SELECT * FROM creatures" , null);
        int idIndex = creatureCursor.getColumnIndex("id");
        if (creatureCursor.getCount() != 0) {
            while (creatureCursor.moveToNext()) {
                creatureId.add(creatureCursor.getInt(idIndex));
            }
        }
        creatureCursor.close();
        creatureGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Creature.this, Creaturesample.class);
                intent.putExtra("creatureId", String.valueOf(creatureId.get(position)));
                startActivity(intent);

            }
        });
    }

}
