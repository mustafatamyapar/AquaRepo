package com.example.aquaassistant.zeynep;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.database.sqlite.SQLiteStatement;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zulal.AquariumContainer;

import java.util.ArrayList;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class TanksAdapter extends ArrayAdapter<String> {
    private ArrayList <String> idArray;
    private Activity context;
    SQLiteDatabase tanksDatabase;
    public TanksAdapter (ArrayList <String> idArray, Activity context) {
        super(context, R.layout.tanks_view, (ArrayList<String>) idArray);
        this.idArray = idArray;
        this.context = context;
        tanksDatabase = context.openOrCreateDatabase("Tanks" , Context.MODE_PRIVATE, null);
    }

    @NonNull
    @Override
    public View getView( int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the tanks view
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View tanksView = layoutInflater.inflate(R.layout.tanks_view,null,true);

        //find the layout components and set their texts
        TextView nameOfTank =  tanksView.findViewById(R.id.nameOfTank);
        TextView timeFeed = tanksView.findViewById(R.id.timeFeed);
        TextView timeWater= tanksView.findViewById(R.id.timeWater);
        TextView numOfFish = tanksView.findViewById(R.id.numOfFishes);
        TextView numOfOthers = tanksView.findViewById(R.id.numOfOthers);
        TextView numOfPlant = tanksView.findViewById(R.id.numOfPlants);
        //open the tanks database
        //tanksDatabase = openOrCreateDatabase("Tanks" , null);
        //get the data
        Cursor cursor = tanksDatabase.rawQuery("SELECT * FROM tanks WHERE id = ?", new String[]{String.valueOf(idArray.get(position))});

            //get the indexes

            int tankNameIndex = cursor.getColumnIndex("tankname");
            int timeFeedIndex = cursor.getColumnIndex("timetofeed");
            int timeWaterIndex = cursor.getColumnIndex("watercheck");
            int numOfFishIndex = cursor.getColumnIndex("numoffish");
            int numOfPlantIndex = cursor.getColumnIndex("numofplant");
            int numOfOtherIndex = cursor.getColumnIndex("numofother");
            while(cursor.moveToNext()) {
            //set the texts by getting the data from tanks database
                nameOfTank.setText(cursor.getString(tankNameIndex));
                timeFeed.setText("Time until feeding: " + cursor.getString(timeFeedIndex));
                timeWater.setText("Time until water check: " + cursor.getString(timeWaterIndex));
                numOfFish.setText("Fish Count: " + cursor.getString(numOfFishIndex));
                numOfPlant.setText("Plant Count: " + cursor.getString(numOfPlantIndex));
                numOfOthers.setText("Other Creatures Count: " + cursor.getString(numOfOtherIndex));
        }
        cursor.close();
        //notifyDataSetChanged();
        return tanksView;
    }
}
