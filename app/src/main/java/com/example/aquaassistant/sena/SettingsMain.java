package com.example.aquaassistant.sena;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aquaassistant.kaan.MainActivity;
import com.example.aquaassistant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import es.dmoral.toasty.Toasty;

/**
 * SettingsMain Class - settings page
 * @author Fatma Sena Genç
 * @version 1.0 (April 30, 2020) - partially complete, buttons available
 * @version 2.0 (May 16, 2020) - profile update functions working
 * @version 3.0 (May 19, 2020) - completed
 */

public class SettingsMain extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private SlidrInterface slidr;

    @Override
    /**
     * onCreate - called when the activity is started
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsmain);
        firebaseAuth = FirebaseAuth.getInstance();
        slidr = Slidr.attach(this);
    }

    //following moments are for navigation

    /**
     * changeUsernamePage - navigates to the activity for changing username
     * @param view
     */
    public void changeUsernamePage(View view) {
        Intent intent = new Intent(getApplicationContext(), ChangeUsername.class);
        startActivity(intent);
    }

    /**
     * changePasswordPage - navigates to the activity for changing password
     * @param view
     */
    public void changePasswordPage(View view) {
        Intent intent = new Intent(getApplicationContext(), ChangePassword.class);
        startActivity(intent);
    }

    /**
     * changeEmailPage - navigates to the activity for changing e-mail
     * @param view
     */
    public void changeEmailPage(View view) {
        Intent intent = new Intent(getApplicationContext(), ChangeEmail.class);
        startActivity(intent);
    }

    /**
     * changeProfilePicturePage - navigates to the activity for changing profile picture
     * @param view
     */
    public void changeProfilePicturePage(View view) {
        Intent intent = new Intent(getApplicationContext(), ChangeProfilePicture.class);
        startActivity(intent);
    }

    /**
     * deleteAccountPage - navigated to the activity for deleting account
     * @param view
     */
    public void deleteAccountPage (View view) {
        Intent intent = new Intent(getApplicationContext(), DeleteAccount.class);
        startActivity(intent);
    }

    /**
     * openCredits - opens an alert dialog that includes credits information
     * @param view
     */
    public void openCredits(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(SettingsMain.this);
        alert.setTitle("Credits");
        alert.setMessage("ENCYCLOPEDIA\n*Mom.com (https://mom.com/momlife/17983-10-non-fish-aquarium-pets)\n"
                + "*Aquarium Creatures (https://www.aquariumcreation.com/blogs/news/freshwater-shrimp-diseases)\n*Aquarium Adviser (https://aquariumadviser.com/10-best-plants-for-freshwater-aquarium/)\n"
                + "*Fishlore (https://www.fishlore.com/Disease.htm)\n*ModestFish (https://modestfish.com/fish-disease-guide/)\n"
                + "*Fishkeeping World (https://www.fishkeepingworld.com/17-most-popular-freshwater-fish/)\n\nOPENING MUSIC\nOcean Wave Sound Effect (https://www.partnersinrhyme.com/soundfx/water_sounds/water_ocean-wave4_wav.shtml)\n\n"
                + "QUESTIONS & ANSWERS AT FAQ SECTION\nhttps://aquariuminfo.org/faq.html\n\nThank you, Şukufe.");

        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }
        );
        alert.show();
    }

    /**
     * lockSlide - disables slide feature
     * @param v
     */
    public void lockSlide(View v) {
        slidr.lock();
        Toasty.success(SettingsMain.this, "Slider feature is locked!", Toast.LENGTH_LONG).show();
    }

    /**
     * unlockSlide - enables slide feature
     * @param v
     */

    public void unlockSlide(View v) {
        slidr.unlock();
        Toasty.success(SettingsMain.this, "Slider feature is unlocked!", Toast.LENGTH_LONG).show();

    }
}
