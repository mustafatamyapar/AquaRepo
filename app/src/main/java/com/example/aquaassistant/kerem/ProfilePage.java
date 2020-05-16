package com.example.aquaassistant.kerem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aquaassistant.R;
import com.example.aquaassistant.sena.ChangeProfilePicture;
import com.example.aquaassistant.sena.SettingsMain;
import com.example.aquaassistant.zulal.Faqactivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProfilePage extends AppCompatActivity {
    private Button commentsButton;
    private Button logOutButton;
    private Button settingsButton;
    private TextView usernameDisplay;
    private ImageView profilePicture;

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user.getDisplayName() != null) {
            usernameDisplay.setText(user.getDisplayName().toString());
        } else {
            usernameDisplay.setText("user");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        commentsButton = findViewById(R.id.commentsButton);
        logOutButton = findViewById(R.id.logOutButton);
        settingsButton = findViewById(R.id.settingsButton);
        usernameDisplay = findViewById(R.id.textView6);
        profilePicture = findViewById(R.id.imageView2);
        //section below is to display the username
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user.getDisplayName() != null) {
            usernameDisplay.setText(user.getDisplayName().toString());
        } else {
            usernameDisplay.setText("user");
        }

        Slidr.attach(this);
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, SettingsMain.class);
        startActivity(intent);
    }
}
