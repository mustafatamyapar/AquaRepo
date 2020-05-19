package com.example.aquaassistant.mustafa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zeynep.FavouritePlacesActivity;
import com.example.aquaassistant.zulal.Faqactivity;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;
/**
 * Encyclopedia page which has buttons for all categories
 * @author Mustafa Efe Tamyapar
 * @version 1.0 27.04.2020
 * @version 2.0 18.05.2020 Style of the page is changed.
 */
public class EncyclopediaPage extends AppCompatActivity {
    private Button fishButton;
    private Button snailButton;
    private Button plantsButton;
    private Button diseasesButton;
    private Button faqButton;
    private TextView txtt;

    /**
     * This methods creates encyclopedia page.
     * @param savedInstanceState
     * @return void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedia_page);
        fishButton = findViewById(R.id.fishButton);
        snailButton = findViewById(R.id.snailButton);
        plantsButton = findViewById(R.id.plantsButton);
        diseasesButton = findViewById(R.id.diseasesButton);
        faqButton = findViewById(R.id.faqButton);
        txtt = findViewById(R.id.txtt);
        txtt.setPaintFlags(txtt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Slidr.attach(this);
    }

    /**
     * This method is used for directing user to fish page when button is clicked.
     * @param view
     * @return void
     */
    public void openFishPage(View view) {
        Intent intent = new Intent(this, FishPage.class);
        startActivity(intent);
    }

    /**
     * This method is used for directing user to other creature page when button is clicked.
     * @param view
     * @return void
     */
    public void openSnailPage(View view) {
        Intent intent = new Intent(this, SnailPage.class);
        startActivity(intent);
    }

    /**
     * This method is used for directing user to plants page when button is clicked.
     * @param view
     * @return void
     */
    public void openPlantsPage(View view) {
        Intent intent = new Intent(this, PlantsPage.class);
        startActivity(intent);
    }

    /**
     * This method is used for directing user to diseases page when button is clicked.
     * @param view
     * @return void
     */
    public void openDiseases(View view) {
        Intent intent = new Intent(this, DiseasesPage.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Hope Your fish is okay. Aquassitant Team", Toast.LENGTH_SHORT).show();
    }

    /**
     * This method is used for directing user to frequently asked questions page when button is clicked.
     * @param view
     * @return void
     */
    public void openFaq(View view) {
        Intent intent = new Intent(this, Faqactivity.class);
        startActivity(intent);
    }

    /**
     * This method is used for directing user to maps page when textView is clicked.
     * @param view
     * @return void
     */
    public void goMapPage(View view) {
        Intent intent = new Intent(this, FavouritePlacesActivity.class);
        startActivity(intent);
    }

}
