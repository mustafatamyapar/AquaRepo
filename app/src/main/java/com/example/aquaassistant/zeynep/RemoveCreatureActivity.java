package com.example.aquaassistant.zeynep;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.GridView;
import com.example.aquaassistant.R;
import com.r0adkll.slidr.Slidr;
import java.util.ArrayList;
/**
 * RemoveCreatureActivity Class - the activity that sends the creature database info via an arraylist
 * to the creaturesAdapter to be able to show and remove.
 * @author Zeynep Berber
 * @version 1.0 (May 18, 2020) - completed
 */
public class RemoveCreatureActivity extends AppCompatActivity {
    String tankId ;
    ArrayList<Integer> creaturesId;
    CreaturesAdapter creaturesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Slidr.attach(this);
        setContentView(R.layout.activity_remove_creature);
        final GridView gridView = findViewById(R.id.gridView);
        creaturesId = new ArrayList<>();
        creaturesAdapter = new CreaturesAdapter(creaturesId, RemoveCreatureActivity.this);
        creaturesAdapter.notifyDataSetChanged();
        gridView.setAdapter(creaturesAdapter);


        //get the tank name
        Intent intent = getIntent();
        tankId = intent.getStringExtra("tankId");
        SQLiteDatabase creaturesDatabase = RemoveCreatureActivity.this.openOrCreateDatabase("Creatures" , MODE_PRIVATE, null);
        creaturesDatabase.execSQL("CREATE TABLE IF NOT EXISTS creatures (id INTEGER PRIMARY KEY , type VARCHAR , creaturename VARCHAR, tankId VARCHAR , image BLOB)");

        //put the creatures' ids
        Cursor creatureCursor = creaturesDatabase.rawQuery("SELECT * FROM creatures WHERE tankId= ?" , new String[]{tankId});
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
