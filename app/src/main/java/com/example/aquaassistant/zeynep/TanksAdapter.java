package com.example.aquaassistant.zeynep;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zeynep.Tank;

import java.util.ArrayList;

public class TanksAdapter extends ArrayAdapter<Tank> {
    private ArrayList <Tank> tankContainer;
    private Activity context;
    public TanksAdapter(ArrayList <Tank> tankContainer, Activity context) {
        super(context, R.layout.tanks_view, (ArrayList<Tank>) tankContainer);
        this.tankContainer = tankContainer;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView( int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View tanksView = layoutInflater.inflate(R.layout.tanks_view,null,true);

        TextView nameView = tanksView.findViewById(R.id.tankName);
        TextView timeCond = tanksView.findViewById(R.id.timeCond);
        TextView timeWater= tanksView.findViewById(R.id.timeWater);
        TextView numOfFish = tanksView.findViewById(R.id.numOfFishes);
        TextView numOfSnail = tanksView.findViewById(R.id.numOfSnail);
        TextView numOfPlant = tanksView.findViewById(R.id.numOfPlants);

        nameView.setText(tankContainer.get(position).getName());
        timeCond.setText( "Time until condition check: " + tankContainer.get(position).getCondCheck()) ;
        timeWater.setText("Time until water check: " + tankContainer.get(position).getWaterCheck());
        numOfFish.setText("Fish Count: " + tankContainer.get(position).getNumOfFish());
        numOfSnail.setText("Snail Count: " + tankContainer.get(position).getNumOfSnail());
        numOfPlant.setText("Plant count: " + tankContainer.get(position).getNumOfPlant());
        return tanksView;
    }
}
