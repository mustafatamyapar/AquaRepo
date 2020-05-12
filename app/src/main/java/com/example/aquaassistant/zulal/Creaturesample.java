package com.example.aquaassistant.zulal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aquaassistant.R;

public class Creaturesample extends AppCompatActivity {
    //variables
    ImageView creatureImage;
    TextView nameOfCreature;
    Button removeCreature;
    Button goEncyclopedia;
    Button changeName;
    String nameOfTheCreature;
    String idOfTheCreature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creaturesample);
        creatureImage =findViewById(R.id.creature_image);
        nameOfCreature = findViewById(R.id.creature_name);
        removeCreature =findViewById(R.id.remove_creature);
        goEncyclopedia = findViewById(R.id.go_encyclopedia);
        changeName = findViewById(R.id.change_name);
        Intent intent = getIntent();
        nameOfTheCreature = intent.getStringExtra("nameOfCreature");
        idOfTheCreature = intent.getStringExtra("idOfTheCreature");
        nameOfCreature.setText(nameOfTheCreature);


    }
    public void changeName(View view){

    }
    public void goEncyclopedia(View view){

    }
    public void removeCreature( View view){

    }
    public void imageClicked(View view){

    }
}
