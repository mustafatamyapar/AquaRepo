package com.example.aquaassistant.kerem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aquaassistant.R;
import com.example.aquaassistant.kaan.MainActivity;
import com.example.aquaassistant.sena.ChangeProfilePicture;
import com.example.aquaassistant.sena.SettingsMain;
import com.example.aquaassistant.zulal.Faqactivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;
import com.example.aquaassistant.kerem.Ranks;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProfilePage extends AppCompatActivity {
    private Button logOutButton;
    private Button settingsButton;
    private TextView usernameDisplay;
    private ImageView profilePicture;
    private TextView rankDisplay;

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user.getDisplayName() != null) {
            usernameDisplay.setText(user.getDisplayName().toString());
        } else {
            usernameDisplay.setText("user");
        }
        if (user.getPhotoUrl() != null) {
            String uid = user.getUid();
            StorageReference ref = FirebaseStorage.getInstance().getReference()
                    .child("profilePictures/" + uid + ".jpeg");
            ref.getDownloadUrl()
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Glide.with(getApplicationContext()).load(task.getResult()).into(profilePicture);
                            }
                        }
                    });
        } else {
            profilePicture.setImageResource(R.drawable.emptypicture);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        logOutButton = findViewById(R.id.logOutButton);
        settingsButton = findViewById(R.id.settingsButton);
        usernameDisplay = findViewById(R.id.textView6);
        profilePicture = findViewById(R.id.imageView2);
        rankDisplay = findViewById(R.id.textView11);

        rankDisplay.setText(Ranks.RANK);

        //section below is to display the username
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user.getDisplayName() != null) {
            usernameDisplay.setText(user.getDisplayName().toString());
        } else {
            usernameDisplay.setText("user");
        }
        if (user.getPhotoUrl() != null) {
            String uid = user.getUid();
            StorageReference ref = FirebaseStorage.getInstance().getReference()
                    .child("profilePictures/" + uid + ".jpeg");
            ref.getDownloadUrl()
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Glide.with(getApplicationContext()).load(task.getResult()).into(profilePicture);
                            }
                        }
                    });
        } else {
            profilePicture.setImageResource(R.drawable.emptypicture);
        }
        Slidr.attach(this);
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, SettingsMain.class);
        startActivity(intent);

    }
    public void signOutClicked(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intentToMain = new Intent ( ProfilePage.this, MainActivity.class);
        startActivity(intentToMain);
    }
}
