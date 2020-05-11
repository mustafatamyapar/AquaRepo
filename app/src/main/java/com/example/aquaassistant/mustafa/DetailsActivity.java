package com.example.aquaassistant.mustafa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aquaassistant.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class DetailsActivity extends AppCompatActivity {

    private TextView diseaseTitle;
    private TextView diseaseInfo;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disease_details);
        Slidr.attach(this);

        diseaseTitle = (TextView) findViewById(R.id.DiseaseNameBig);
        diseaseInfo = (TextView) findViewById(R.id.DetailedInfo);
        img = (ImageView) findViewById(R.id.bigDiseasePic);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Image") ;

        diseaseTitle.setText(Title);
        diseaseInfo.setText(Description);
        img.setImageResource(image);


    }

}
