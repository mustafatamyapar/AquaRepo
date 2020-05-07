package com.example.aquaassistant.zeynep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.aquaassistant.R;

import java.util.ArrayList;

public class RemoveCreatureActivity extends AppCompatActivity {
    String tankId;
    String tankName;
    ArrayList<Integer> creaturesId;
    CreaturesAdapter creaturesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_creature);
        final GridView gridView = findViewById(R.id.gridView);
        creaturesId = new ArrayList<Integer>();
        creaturesAdapter = new CreaturesAdapter(creaturesId, RemoveCreatureActivity.this);
        creaturesAdapter.notifyDataSetChanged();
        gridView.setAdapter(creaturesAdapter);


        //get the tank name
        Intent intent = getIntent();
        tankId = intent.getStringExtra("tankId");
        SQLiteDatabase tanksDatabase = RemoveCreatureActivity.this.openOrCreateDatabase("Tanks" ,MODE_PRIVATE, null);
        Cursor tankCursor = tanksDatabase.rawQuery("SELECT tankname FROM tanks WHERE id=?", new String[]{tankId});
        while (tankCursor.moveToNext()){
            tankName = tankCursor.getString(tankCursor.getColumnIndex("tankname"));
        }
        tankCursor.close();

        SQLiteDatabase creaturesDatabase = RemoveCreatureActivity.this.openOrCreateDatabase("Creatures" , MODE_PRIVATE, null);


        //put the creatures' ids
        Cursor creatureCursor = creaturesDatabase.rawQuery("SELECT * FROM creatures WHERE tankname = ?" , new String[]{tankName});
        int idIndex = creatureCursor.getColumnIndex("id");
        if (creatureCursor.getCount() != 0) {
            while (creatureCursor.moveToNext()) {
                creaturesId.add(creatureCursor.getInt(idIndex));
            }
        }
        creatureCursor.close();

        //put the tank id as extra
        Intent intent1 = new Intent( RemoveCreatureActivity.this , CreaturesAdapter.class);
        intent1.putExtra("tankId" , tankId);
    }
}
