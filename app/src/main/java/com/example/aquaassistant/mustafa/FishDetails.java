package com.example.aquaassistant.mustafa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aquaassistant.R;

public class FishDetails extends AppCompatActivity{
    private TextView fishTitle;
    private TextView fishDetails;
    private ImageView fishImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fish_details);

        fishTitle = (TextView) findViewById(R.id.detailsFishName);
        fishDetails = (TextView) findViewById(R.id.FishDetails);
        fishImg = (ImageView) findViewById(R.id.detailsFishImage);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Name");
        String Description = intent.getExtras().getString("Descriptionn");
        int image = intent.getExtras().getInt("Images ") ;

        fishTitle.setText(Title);
        fishDetails.setText(Description);
        fishImg.setImageResource(image);


    }
}
