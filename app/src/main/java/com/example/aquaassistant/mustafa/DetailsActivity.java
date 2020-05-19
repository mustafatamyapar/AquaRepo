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

/**This activity creates a detailed information page
 *when user clicks on item. Items such as fish,creature,plant and disease.
 * @author Mustafa Efe Tamyapar
 * @version 1.0 I tried to use JSoup in here, i created a website to get information but it did not work.
 * @version 2.0 I used intent Getextra to get information from other activity.
 */
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
        int image = intent.getExtras().getInt("Image");

        diseaseTitle.setText(Title);
        diseaseInfo.setText(Description);
        img.setImageResource(image);

        diseaseInfo.setMovementMethod(new ScrollingMovementMethod());
    }

}
