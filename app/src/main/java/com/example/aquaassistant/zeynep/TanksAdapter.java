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

        nameOfTank.setText(tankContainer.get(position).getTankName());
        timeFeed.setText( "Time until feeding: " + tankContainer.get(position).getTimeToFeed()) ;
        timeWater.setText("Time until water check: " + tankContainer.get(position).getWaterCheck());
        numOfFish.setText("Fish Count: " + tankContainer.get(position).allFishes());
        numOfPlant.setText("Plant Count: " + tankContainer.get(position).allPlants());
        numOfOthers.setText("Other Creatures Count: " + tankContainer.get(position).allOthers());

        return tanksView;
    }
}
