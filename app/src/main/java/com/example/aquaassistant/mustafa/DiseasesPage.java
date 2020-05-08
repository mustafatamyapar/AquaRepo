package com.example.aquaassistant.mustafa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aquaassistant.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;
import java.util.List;

public class DiseasesPage extends AppCompatActivity {

    private List<Disease> listDisease = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases_page);

        recyclerView = findViewById(R.id.recyclerwiewid);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(),listDisease);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
        Slidr.attach(this);


        listDisease.add(new Disease("Cotton Mouth","","Cottony growth near jaws and loss of appetite",R.drawable.disease1));
        listDisease.add(new Disease("Dropsy","","Bloat, scales stick out",R.drawable.disease2));
        listDisease.add(new Disease("Fin/Tail Rot","","Erosion at edges of fins",R.drawable.disease3));
        listDisease.add(new Disease("Hole in the Head","","Pale ulcerated area around head",R.drawable.disease4));
        listDisease.add(new Disease("Fish Fungus","","Whitish, fur-like growths\t",R.drawable.disease5));
        listDisease.add(new Disease("Camallanus Worms","","Red or pink worm protruding from the anus, fish may become listless and bloated, fish refuse to eat",R.drawable.disease6));
        listDisease.add(new Disease("Ammonia Poisoning","","Red or inflamed gills, fish are gasping for air at the surface",R.drawable.disease7));
        listDisease.add(new Disease("Vorticella","","Cilia like white organisms on mouth of the shrimp",R.drawable.disease8));




    }

}
