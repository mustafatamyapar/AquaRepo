package com.example.aquaassistant.zeynep;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.aquaassistant.R;
import java.util.ArrayList;

public class FavouritePlacesActivity extends AppCompatActivity {
    ListView favouritesList;
    static ArrayList<String> placeNames = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_places);
        arrayAdapter = new ArrayAdapter(FavouritePlacesActivity.this, android.R.layout.simple_list_item_1, placeNames);
        favouritesList = findViewById(R.id.favouriteListView);
        favouritesList.setAdapter(arrayAdapter);

        MapsActivity.favouritesDatabase = this.openOrCreateDatabase("Places", MODE_PRIVATE, null);
        MapsActivity.favouritesDatabase.execSQL("CREATE TABLE IF NOT EXISTS places (id INTEGER PRIMARY KEY , name VARCHAR , latitude VARCHAR , longitude VARCHAR)");

        //add the favourite places to places array
        Cursor cursor = MapsActivity.favouritesDatabase.rawQuery("SELECT * FROM places", null);
        while (cursor.moveToNext()){
            placeNames.add(cursor.getString(cursor.getColumnIndex("name")));
        }
        cursor.close();

        //if the user click on one of the favourite ones
        favouritesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FavouritePlacesActivity.this, MapsActivity.class);
                intent.putExtra("condition" , "show fav");
                intent.putExtra("placeName" , placeNames.get(position));
                startActivity(intent);
            }
        });
    }
    public void addNewPlace(View view){
        Intent intent2 = new Intent(FavouritePlacesActivity.this, MapsActivity.class);
        intent2.putExtra("condition", "add fav");
        startActivity(intent2);
    }
}
