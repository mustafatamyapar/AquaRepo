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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiseasesPage extends AppCompatActivity {

    private String JSON_URL = "https://gist.githubusercontent.com/mustafatamyapar/e9f87d0be83918469c7ed111bcb766bd/raw/e429ebc0dbc4904fe25bf2963986c6c3f3cda358/Disease.json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Disease> listDisease = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases_page);

        recyclerView = findViewById(R.id.recyclerwiewid);
        jsonrequest();


    }
    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject;

                for ( int i = 0; i < response.length(); i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Disease disease = new Disease();

                        disease.setName(jsonObject.getString("name"));
                        disease.setDescription(jsonObject.getString("description"));
                        disease.setSymptoms(jsonObject.getString("symptoms"));
                        disease.setImageUrl(jsonObject.getString("img"));
                        listDisease.add(disease);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                setUpRecyclerView(listDisease);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(DiseasesPage.this);
        requestQueue.add(request);


    }

    private void setUpRecyclerView(List<Disease> listDisease) {

        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,listDisease);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myAdapter);

    }
}
