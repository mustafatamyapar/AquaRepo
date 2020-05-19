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

/**This activity creates a detailed information page. Also when user clicks on any item this
 * page will be shown. For plants and disease items.
 * @author Mustafa Efe Tamyapar
 * @version 1.0 05.05.2020  Created activity.
 * @version 2.0 11.05.2020 Solved some problems about keys in getExtra methods
 * @version 3.0 17.05.2020 Added scrollView to show all information.
 */
public class DetailsActivity extends AppCompatActivity {

    private TextView diseaseTitle;
    private TextView diseaseInfo;
    private ImageView img;
    /**
     * This method creates activity. And gets information from List in the mainPage.
     * @param savedInstanceState
     * @return void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disease_details);
        Slidr.attach(this);

        diseaseTitle = (TextView) findViewById(R.id.DiseaseNameBig);
        diseaseInfo = (TextView) findViewById(R.id.DetailedInfo);
        img = (ImageView) findViewById(R.id.bigDiseasePic);
        //Getting information
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Image");

        diseaseTitle.setText(Title);
        diseaseInfo.setText(Description);
        img.setImageResource(image);
        //For scrollView
        diseaseInfo.setMovementMethod(new ScrollingMovementMethod());
    }

}
