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
import android.widget.TextView;
import android.widget.Toast;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zeynep.RemoveCreatureActivity;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;
/**
*This activity lists all creatures and their photos with the help of SQLite Database
 * @author Zülal Nur Hıdıroğlu
 * @version 1.0 (May 17, 2020) - completed
 */

public class Creature extends AppCompatActivity {
    GridView creatureGrid;
    ArrayList<Integer> creatureId;
    static GridViewAdapter gridViewAdapter;
    SQLiteDatabase creatureDatabase;
    TextView header;
    EditText searchBox;
    ImageButton searchButton;
    String boxContent;
    String idCreature;
    /**
     *In this method, tools are listed. Also the ids of the creatures are added to Arraylist
     * with the help of Cursor of Database. And clickListener is added to items in gridVew.
     * @param savedInstanceState this method is used by a view.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creature);
        Slidr.attach(this);
        creatureGrid = findViewById(R.id.grid_creatures);
        creatureId = new ArrayList<>();
        header =findViewById(R.id.header);
        searchBox = findViewById(R.id.searchBox);
        searchButton = findViewById(R.id.searchButton);
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
    /**
     * With this method, when user clicks on the search button in Creature activity,
     * it takes the content of the TextView which is a name of a creature and finds the
     * id of the creature in Creature database. This method sends this id to Creaturesample class
     * via intent.
     * @param view this method is used by a view.
     */
    public void searchBut(View view){
        boxContent = searchBox.getText().toString();
        if(!boxContent.matches("")) {
            SQLiteDatabase searchData = Creature.this.openOrCreateDatabase("Creatures", MODE_PRIVATE, null);
            Cursor cursor = searchData.rawQuery("SELECT * FROM creatures WHERE creaturename = ?", new String[]{boxContent});
            while (cursor.moveToNext()) {
                idCreature = cursor.getString(cursor.getColumnIndex("id"));
            }
            cursor.close();
            if(idCreature!=null) {
                Intent intent1 = new Intent(Creature.this, Creaturesample.class);
                intent1.putExtra("creatureId", idCreature);
                startActivity(intent1);
            }
            else if(idCreature==null){
                Toast.makeText(getApplicationContext(),"There is no such a creature in the tank", Toast.LENGTH_LONG).show();            }
        }
        else if(boxContent.matches("")){
            Toast.makeText(getApplicationContext(),"Please write name of the creature", Toast.LENGTH_LONG).show();
        }

    }
}
