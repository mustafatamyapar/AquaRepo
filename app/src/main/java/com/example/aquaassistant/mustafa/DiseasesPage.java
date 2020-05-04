package com.example.aquaassistant.mustafa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.request.RequestOptions;
import com.example.aquaassistant.MainActivity;
import com.example.aquaassistant.R;
import com.example.aquaassistant.mustafa.RecyclerViewAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

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


        listDisease.add(new Disease("Cotton Mouth","he ","ost of the columnaris infections are external and present first as white or grayish spots or patches on the head and around the fins or gills. ",R.drawable.disease1));
        listDisease.add(new Disease("Dropsy","descriptionnnnnnnnnnnnnnnnnnnnnnn","Symptommomomss",R.drawable.disease2));
        listDisease.add(new Disease("Fin/Tail Rot","descriptiiiiiiiiinnnnn","Symptommms",R.drawable.disease3));
        listDisease.add(new Disease("Hole in the Head","description","symptom",R.drawable.disease4));
        listDisease.add(new Disease("Fish Fungus","description","symptom",R.drawable.disease5));
        listDisease.add(new Disease("Camallanus Worms","description","symptom",R.drawable.disease6));
        listDisease.add(new Disease("Ammonia Poisoning","description","symptom",R.drawable.disease7));
        listDisease.add(new Disease("Vorticella","description","symptom",R.drawable.disease8));




    }

}
