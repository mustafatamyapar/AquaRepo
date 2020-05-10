package com.example.aquaassistant.zeynep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.aquaassistant.R;

import java.util.ArrayList;

public class FavouritePlacesActivity extends AppCompatActivity {
    ListView favouritesList;
    static ArrayList<String> placeNames = new ArrayList<>();
    static  ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_places);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, placeNames);
        favouritesList = findViewById(R.id.favouriteListView);
        favouritesList.setAdapter(arrayAdapter);
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
