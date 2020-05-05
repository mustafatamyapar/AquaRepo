package com.example.aquaassistant.mustafa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zulal.Faqactivity;

public class EncyclopediaPage extends AppCompatActivity {
private Button fishButton;
private Button snailButton;
private Button plantsButton;
private Button diseasesButton;
private Button faqButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedia_page);
        fishButton = findViewById(R.id.fishButton);
        snailButton = findViewById(R.id.snailButton);
        plantsButton = findViewById(R.id.plantsButton);
        diseasesButton = findViewById(R.id.diseasesButton);
        faqButton = findViewById(R.id.faqButton);
    }
    public void openFishPage(View view){
        Intent intent = new Intent( this, FishPage.class);
        startActivity(intent);
    }
    public void openSnailPage(View view){
        Intent intent = new Intent( this, SnailPage.class);
        startActivity(intent);
    }
    public void openPlantsPage(View view){
        Intent intent = new Intent( this, PlantsPage.class);
        startActivity(intent);
    }
    public void openDiseases(View view){
        Intent intent = new Intent( this, DiseasesPage.class);
        startActivity(intent);
    }
    public void openFaq(View view){
        Intent intent = new Intent( this, Faqactivity.class);
        startActivity(intent);
    }
}
