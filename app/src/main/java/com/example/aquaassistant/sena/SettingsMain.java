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

public class SettingsMain extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private SlidrInterface slidr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsmain);
        firebaseAuth = FirebaseAuth.getInstance();
        slidr = Slidr.attach(this);
    }

    public void changeUsernamePage(View view) {
        Intent intent = new Intent(getApplicationContext(), ChangeUsername.class);
        startActivity(intent);
    }
    public void changePasswordPage(View view) {
        Intent intent = new Intent(getApplicationContext(), ChangePassword.class);
        startActivity(intent);
    }
    public void changeEmailPage(View view) {
        Intent intent = new Intent(getApplicationContext(), ChangeEmail.class);
        startActivity(intent);
    }
    public void changeProfilePicturePage(View view) {
        Intent intent = new Intent(getApplicationContext(), ChangeProfilePicture.class);
        startActivity(intent);
    }
    public void deleteAccountPage (View view) {
        Intent intent = new Intent(getApplicationContext(), DeleteAccount.class);
        startActivity(intent);
    }

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

    public void lockSlide(View v) {
        slidr.lock();
    }

    public void unlockSlide(View v) {
        slidr.unlock();
    }
}
