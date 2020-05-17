package com.example.aquaassistant.zulal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.aquaassistant.R;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter<Integer> {
    private ArrayList <Integer> creatureId;
    private Activity activity;
    private SQLiteDatabase creatureData;

    GridViewAdapter( ArrayList <Integer> creatureId,Activity activity) {
        super(activity, R.layout.creature_gridview, creatureId);
        this.creatureId = creatureId;
        this.activity = activity;
    }

    @NonNull
    int creatureNameNo;
    int creatureImageNo;
    int creatureIdNo;
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View customView = layoutInflater.inflate(R.layout.creature_gridview, null, true);
        ImageView creatureImage = customView.findViewById(R.id.creatureImages);
        TextView creatureName = customView.findViewById(R.id.creatureNames);
        try {
            creatureData = activity.openOrCreateDatabase("Creatures", Context.MODE_PRIVATE, null);
//            tankData = activity.openOrCreateDatabase("Tanks", Context.MODE_PRIVATE, null);
//            Intent intent3 = activity.getIntent();
//            tankId = intent3.getStringExtra("tankId");
            final Cursor creaturesCursor = creatureData.rawQuery("SELECT * FROM creatures WHERE id = ? ", new String[]{String.valueOf(creatureId.get(position))});
             creatureNameNo = creaturesCursor.getColumnIndex("creaturename");
             creatureImageNo = creaturesCursor.getColumnIndex("image");
             creatureIdNo = creaturesCursor.getColumnIndex("id");
            while (creaturesCursor.moveToNext()) {
                creatureName.setText(creaturesCursor.getString(creatureNameNo));
                if (creaturesCursor.getBlob(creatureImageNo) != null) {
                    byte[] bytes = creaturesCursor.getBlob(creatureImageNo);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    creatureImage.setImageBitmap(bitmap);
                }
            }
            creaturesCursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
//        creatureImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentCreatureInfo = new Intent(activity, Creaturesample.class);
//                intentCreatureInfo.putExtra("creatureId",creatureIdNo);
//                //GridViewAdapter.this.startActivity(intentCreatureInfo);
//            }
//        });

        return customView;
    }
}

