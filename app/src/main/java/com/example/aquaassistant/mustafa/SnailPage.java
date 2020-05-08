package com.example.aquaassistant.mustafa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zulal.Fish;

import java.util.ArrayList;
import java.util.List;

public class SnailPage extends AppCompatActivity {
    private List<Fish> listCreature = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snail_page);

        recyclerView = findViewById(R.id.recycler_snail);
        RecyclerViewAdapter2 recyclerViewAdapter2 = new RecyclerViewAdapter2(getApplicationContext(),listCreature);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(recyclerViewAdapter2);

        listCreature.add(new Fish("snail","hahahahahah","Difficulty: 5.00",R.drawable.disease7));
    }
}
