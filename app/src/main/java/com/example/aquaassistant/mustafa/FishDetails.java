package com.example.aquaassistant.mustafa;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aquaassistant.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;
/**
 * Detailed information page for fish and other creatures.
 * @author Mustafa Efe Tamyapar
 * @version 1.0 09.05.2020 Created activity
 * @version 2.0 11.05.2020 Solved problems about getExtra method.
 * @version 3.0 17.05.2020 Added a scrollview to show all detailed information.
 */
public class FishDetails extends AppCompatActivity {
    private TextView fishTitle;
    private TextView fishDetails;
    private ImageView fishImg;

    /**
     * This method creates detailed information page for fish and other creatues.
     * @param savedInstanceState
     * @return void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fish_details);
        Slidr.attach(this);

        fishTitle = (TextView) findViewById(R.id.detailsFishName);
        fishDetails = (TextView) findViewById(R.id.FishDetails);
        fishImg = (ImageView) findViewById(R.id.detailsFishImage);
        //Gets information from list in the main page.
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Name");
        String Description = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Image");

        fishTitle.setText(Title);
        fishDetails.setText(Description);
        fishImg.setImageResource(image);
        //For scrollView
        fishDetails.setMovementMethod(new ScrollingMovementMethod());


    }
}
