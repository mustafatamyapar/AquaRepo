package com.example.aquaassistant.mustafa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.aquaassistant.R;

public class EncyclopediaPage extends AppCompatActivity {
private  Button FishButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedia_page);

        FishButton = (Button)findViewById(R.id.FishButton);
        //FishButton.setOnClickListener(openFishPage(); );

    }
    public void openFishPage(){
        Intent intent = new Intent( this, FishPage.class);
        startActivity(intent);
    }
}
