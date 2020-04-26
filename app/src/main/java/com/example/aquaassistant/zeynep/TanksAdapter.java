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
import com.example.aquaassistant.zulal.AquariumContainer;

import java.util.ArrayList;

public class TanksAdapter extends ArrayAdapter<AquariumContainer> {
    private ArrayList <AquariumContainer> tankContainer;
    private Activity context;
    public TanksAdapter(ArrayList <AquariumContainer> tankContainer, Activity context) {
        super(context, R.layout.tanks_view, (ArrayList<AquariumContainer>) tankContainer);
        this.tankContainer = tankContainer;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView( int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View tanksView = layoutInflater.inflate(R.layout.tanks_view,null,true);

        TextView nameOfTank =  tanksView.findViewById(R.id.textView);
        TextView timeCond = tanksView.findViewById(R.id.timeCond);
        TextView timeWater= tanksView.findViewById(R.id.timeWater);
        TextView numOfFish = tanksView.findViewById(R.id.numOfFishes);
        TextView numOfSnail = tanksView.findViewById(R.id.numOfSnail);
        TextView numOfPlant = tanksView.findViewById(R.id.numOfPlants);

        nameOfTank.setText(tankContainer.get(position).getName());
        timeCond.setText( "Time until feeding: " + tankContainer.get(position).getTimeToFeed()) ;
        timeWater.setText("Time until water check: " + tankContainer.get(position).getWaterCheck());
        numOfFish.setText("Fish Count: " + tankContainer.get(position).allFishes());
        numOfPlant.setText("Plant Count: " + tankContainer.get(position).allPlants());
        numOfSnail.setText("Other Creatures Count: " + tankContainer.get(position).allOthers());

        return tanksView;
    }
}
