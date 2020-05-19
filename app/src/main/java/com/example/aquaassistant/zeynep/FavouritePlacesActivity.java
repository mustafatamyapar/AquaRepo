package com.example.aquaassistant.zeynep;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.aquaassistant.R;
import java.util.ArrayList;
/**
 * FavouritePlacesActivity Class - the activity that lists the favourite places added by user
 * and enable user to see these places on the map, remove them or add a new one
 * @author Zeynep Berber
 * @version 1.0 (May 13, 2020) - completed
 */
public class FavouritePlacesActivity extends AppCompatActivity {
    ListView favouritesList;
    static ArrayList<String> placeNames = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    ArrayList<Integer> placesIdArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_places);
        favouritesList = findViewById(R.id.favouriteListView);
        placesIdArray = new ArrayList<> ();
        arrayAdapter = new ArrayAdapter(FavouritePlacesActivity.this, android.R.layout.simple_list_item_1, placeNames);

        MapsActivity.favouritesDatabase = this.openOrCreateDatabase("Places", MODE_PRIVATE, null);
        MapsActivity.favouritesDatabase.execSQL("CREATE TABLE IF NOT EXISTS places (id INTEGER PRIMARY KEY , name VARCHAR , latitude VARCHAR , longitude VARCHAR)");

        placeNames.clear();
        //add the favourite places to places array
        Cursor cursor = MapsActivity.favouritesDatabase.rawQuery("SELECT * FROM places", null);
        while (cursor.moveToNext()){
            FavouritePlacesActivity.placeNames.add(cursor.getString(cursor.getColumnIndex("name")));
        }
        cursor.close();

        favouritesList.setAdapter(arrayAdapter);
        //if the user click on one of the favourite ones
        favouritesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder choose = new AlertDialog.Builder(FavouritePlacesActivity.this);
                choose.setMessage("What would you like to do?");
                choose.setPositiveButton("SHOW ON THE MAP", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(FavouritePlacesActivity.this, MapsActivity.class);
                        intent.putExtra("condition" , "show fav");
                        intent.putExtra("placeName" , placeNames.get(position));
                        startActivity(intent);
                    }
                });
                choose.setNegativeButton("REMOVE FROM THE FAVOURITES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getId();
                        String deleteStr = "DELETE FROM places WHERE id = " + placesIdArray.get(position);
                        SQLiteStatement deleteSt = MapsActivity.favouritesDatabase.compileStatement(deleteStr);
                        deleteSt.execute();
                        placeNames.remove(placeNames.get(position));
                        arrayAdapter.notifyDataSetChanged();
                        System.out.println(placeNames.size());
                    }
                });
                choose.show();
            }
        });
    }
    /**
     * This method directs the user to the map page to add a new place
     * @param view ensures that this method used by a view
     * */
    public void addNewPlace(View view){
        Intent intent2 = new Intent(FavouritePlacesActivity.this, MapsActivity.class);
        intent2.putExtra("condition", "add fav");
        startActivity(intent2);
    }
    /**
     * This method gets the id of the places from the database and put them into an arraylist
     * */
    public void getId() {
        MapsActivity.favouritesDatabase = FavouritePlacesActivity.this.openOrCreateDatabase("Places", MODE_PRIVATE, null);
        Cursor idCur = MapsActivity.favouritesDatabase.rawQuery("SELECT * FROM places", null);
        if (idCur.getCount() != 0) {
            while (idCur.moveToNext()) {
                placesIdArray.add(idCur.getInt(idCur.getColumnIndex("id")));
            }
        }
        idCur.close();
    }
}
