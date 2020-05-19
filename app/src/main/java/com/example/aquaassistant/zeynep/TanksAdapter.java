package com.example.aquaassistant.zeynep;

import android.annotation.SuppressLint;
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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.aquaassistant.R;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * TanksAdapter Class - the adapter that shows the information of the tanks in the listview
 * @author Zeynep Berber
 * @version 1.0 (May 18, 2020) - completed
 */
public class TanksAdapter extends ArrayAdapter<Integer> {
    private ArrayList <Integer> idArray;
    private Activity context;
    private SQLiteDatabase tanksDatabase;
    private Calendar calendar = Calendar.getInstance();
    private int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
    private int minute = calendar.get(Calendar.MINUTE);

    TanksAdapter(ArrayList<Integer> idArray, Activity context) {
        super(context, R.layout.tanks_view, idArray);
        this.idArray = idArray;
        this.context = context;
        //open the database of tanks
        tanksDatabase = context.openOrCreateDatabase("Tanks" , Context.MODE_PRIVATE, null);
    }

    @SuppressLint({"ViewHolder", "SetTextI18n","InflateParams"})
    @NonNull
    @Override
    /**
     * This method gets the information of the tank from database and shows them on the listview.
     * If the hour is 23.59 the remaining days to water check is decreasing
     * @param position the position of the grid
     * @param convertView is the view
     * @param parent the parent view
     * @return the tanksView that inflates a view of the tank info
     */
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

        //get the data where id is specified by the position of listview
        Cursor cursor = tanksDatabase.rawQuery("SELECT * FROM tanks WHERE id = ?", new String[]{String.valueOf(idArray.get(position))});

        //get the column indexes of the info
        int tankNameIndex = cursor.getColumnIndex("tankname");
        int timeFeedIndex = cursor.getColumnIndex("timetofeed");
        int timeWaterIndex = cursor.getColumnIndex("watercheck");
        int numOfFishIndex = cursor.getColumnIndex("numoffish");
        int numOfPlantIndex = cursor.getColumnIndex("numofplant");
        int numOfOtherIndex = cursor.getColumnIndex("numofother");
        while(cursor.moveToNext()) {
            //set the texts by getting the data from tanks database
            nameOfTank.setText(cursor.getString(tankNameIndex));
            timeFeed.setText("Time until feeding: " + cursor.getString(timeFeedIndex) + " day" );
            timeWater.setText("Time until water check: " + cursor.getString(timeWaterIndex) + " days");
            numOfFish.setText("Fish Count: " + cursor.getString(numOfFishIndex));
            numOfPlant.setText("Plant Count: " + cursor.getString(numOfPlantIndex));
            numOfOthers.setText("Other Creatures Count: " + cursor.getString(numOfOtherIndex));
        }

        //update the database day by day
        if (hourOfDay == 23 && minute == 59) {
            String currentWaterCheck = cursor.getString(timeWaterIndex);
            String updateWater = "UPDATE tanks SET watercheck = ? WHERE id = "  + idArray.get(position) ;
            SQLiteStatement updateWaterT = tanksDatabase.compileStatement(updateWater);
            String newWaterTime = String.valueOf(Integer.parseInt(currentWaterCheck) - 1);
            updateWaterT.bindString(1, newWaterTime);
            updateWaterT.execute();
        }
        cursor.close();
        return tanksView;
    }
}
