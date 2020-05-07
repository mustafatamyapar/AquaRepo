package com.example.aquaassistant.zeynep;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.aquaassistant.R;

import java.util.ArrayList;

import static android.content.Intent.getIntent;
import static androidx.core.content.ContextCompat.startActivities;
import static androidx.core.content.ContextCompat.startActivity;

public class CreaturesAdapter extends ArrayAdapter<Integer> {
    private ArrayList<Integer> creatureIdArray;
    private Activity context;
    private SQLiteDatabase creaturesDatabase, tanksDatabase;
    private String tankId, numOfFish, numOFPlant, numOfOther;


    public CreaturesAdapter(ArrayList<Integer> creatureIdArray, Activity context) {
        super(context, R.layout.tanks_view, creatureIdArray);
        this.context = context;
        this.creatureIdArray = creatureIdArray;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the tanks view
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View creatureRemoveView = layoutInflater.inflate(R.layout.creatures_remove_view, null, true);
        ImageButton creatureImageBut = creatureRemoveView.findViewById(R.id.creatureImage);
        TextView creatureNameText = creatureRemoveView.findViewById(R.id.creatureName);

        try {
            creaturesDatabase = context.openOrCreateDatabase("Creatures", Context.MODE_PRIVATE, null);
            tanksDatabase = context.openOrCreateDatabase("Tanks", Context.MODE_PRIVATE, null);

            //get the creatures' count
            Intent intent = context.getIntent();
            tankId = intent.getStringExtra("tankId");
            Cursor tanksCursor = tanksDatabase.rawQuery("SELECT * FROM tanks WHERE id= ?", new String[]{tankId});
            while (tanksCursor.moveToNext()) {
                int fishIndex = tanksCursor.getColumnIndex("numoffish");
                int plantIndex = tanksCursor.getColumnIndex("numofplant");
                int otherIndex = tanksCursor.getColumnIndex("numofother");
                numOfFish = tanksCursor.getString(fishIndex);
                numOFPlant = tanksCursor.getString(plantIndex);
                numOfOther = tanksCursor.getString(otherIndex);
            }
            tanksCursor.close();

            final Cursor creaturesCursor = creaturesDatabase.rawQuery("SELECT * FROM creatures WHERE id = ? ", new String[]{String.valueOf(creatureIdArray.get(position))});
            int creatureNameIndex = creaturesCursor.getColumnIndex("creaturename");
            int creatureImageIndex = creaturesCursor.getColumnIndex("image");
            while (creaturesCursor.moveToNext()) {
                creatureNameText.setText(creaturesCursor.getString(creatureNameIndex));
                if (creaturesCursor.getBlob(creatureImageIndex) != null) {
                    byte[] bytes = creaturesCursor.getBlob(creatureImageIndex);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    creatureImageBut.setImageBitmap(bitmap);
                }
            }
            creaturesCursor.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        creatureImageBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder sure = new AlertDialog.Builder(context);
                    sure.setTitle("Are you sure?");
                    sure.setMessage("This creature will be removed!");
                    sure.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                creaturesDatabase = context.openOrCreateDatabase("Creatures", Context.MODE_PRIVATE, null);
                                tanksDatabase = context.openOrCreateDatabase("Tanks", Context.MODE_PRIVATE, null);
                                Cursor cursor = creaturesDatabase.rawQuery("SELECT * FROM creatures WHERE id = ? ", new String[]{String.valueOf(creatureIdArray.get(position))});
                                int typeIndex = cursor.getColumnIndex("type");
                                while (cursor.moveToNext()) {
                                    if (cursor.getString(typeIndex).matches("fish")) {
                                        //decrease the number of fish in the tank
                                        String decrease = "UPDATE tanks SET numoffish= ? WHERE id = " + tankId;
                                        SQLiteStatement decreaseFishNum = tanksDatabase.compileStatement(decrease);
                                        final String newFishNum = String.valueOf(Integer.parseInt(numOfFish) - 1);
                                        decreaseFishNum.bindString(1, newFishNum);
                                        decreaseFishNum.execute();
                                    } else if (cursor.getString(typeIndex).matches("plant")) {
                                        //decrease the number of plant in the tank
                                        String decrease = "UPDATE tanks SET numofplant= ? WHERE id =" + tankId;
                                        SQLiteStatement decreasePlantNum = tanksDatabase.compileStatement(decrease);
                                        final String newPlantNum = String.valueOf(Integer.parseInt(numOFPlant) - 1);
                                        decreasePlantNum.bindString(1, newPlantNum);
                                        decreasePlantNum.execute();
                                    } else if (cursor.getString(typeIndex).matches("other")) {
                                        //decrease the number of fish in the tank
                                        String decrease = "UPDATE tanks SET numofother= ? WHERE id = " + tankId;
                                        SQLiteStatement decreaseOtherNum = tanksDatabase.compileStatement(decrease);
                                        final String newOtherNum = String.valueOf(Integer.parseInt(numOfOther) - 1);
                                        decreaseOtherNum.bindString(1, newOtherNum);
                                        decreaseOtherNum.execute();
                                    }
                                }
                                cursor.close();

                                String delete = "DELETE FROM creatures WHERE id = " + creatureIdArray.get(position);
                                SQLiteStatement deleteStatement = creaturesDatabase.compileStatement(delete);
                                deleteStatement.execute();

                                creatureIdArray.remove(creatureIdArray.get(position));
                                //go back to tanks page
                                Intent intent1 = new Intent(context, TanksPageActivity.class);
                                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivities(context, new Intent[]{intent1});
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    sure.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    sure.show();
                }
            });
        return creatureRemoveView;

    }
}

