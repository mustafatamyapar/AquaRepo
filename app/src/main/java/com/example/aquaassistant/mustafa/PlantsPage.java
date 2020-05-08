package com.example.aquaassistant.mustafa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aquaassistant.R;

import java.util.ArrayList;
import java.util.List;

import com.r0adkll.slidr.Slidr;

public class PlantsPage extends AppCompatActivity {
    private List<Disease> listPlants = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants_page);

        recyclerView = findViewById(R.id.recycler_plants);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(),listPlants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        listPlants.add(new Disease("Rose","description","redredred",R.drawable.disease5));
        Slidr.attach(this);
        //setContentView(R.layout.activity_plants_page);
    }
}
