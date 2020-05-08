package com.example.aquaassistant.mustafa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zulal.Fish;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;
import java.util.List;

public class FishPage extends AppCompatActivity {
    private List<Fish> listFish = new ArrayList<>();
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_page);
        Slidr.attach(this);

        recyclerView = findViewById(R.id.recyclerwiew2id);
        RecyclerViewAdapter2 recyclerViewAdapter2 = new RecyclerViewAdapter2(getApplicationContext(),listFish);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(recyclerViewAdapter2);

        listFish.add(new Fish("beta","hahahahahah","Difficulty: 5.00",R.drawable.disease8));
        listFish.add(new Fish("beta","hahahahahah","Difficulty: 5.00",R.drawable.disease8));
        listFish.add(new Fish("beta","hahahahahah","Difficulty: 5.00",R.drawable.disease8));
        listFish.add(new Fish("beta","hahahahahah","Difficulty: 5.00",R.drawable.disease8));
        listFish.add(new Fish("beta","hahahahahah","Difficulty: 5.00",R.drawable.disease8));
        listFish.add(new Fish("beta","hahahahahah","Difficulty: 5.00",R.drawable.disease8));
        listFish.add(new Fish("beta","hahahahahah","Difficulty: 5.00",R.drawable.disease8));
        listFish.add(new Fish("beta","hahahahahah","Difficulty: 5.00",R.drawable.disease8));
        listFish.add(new Fish("beta","hahahahahah","Difficulty: 5.00",R.drawable.disease8));
    }
}
