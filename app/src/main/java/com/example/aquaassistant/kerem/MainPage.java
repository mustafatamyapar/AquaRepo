package com.example.aquaassistant.kerem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aquaassistant.R;
import com.example.aquaassistant.mustafa.EncyclopediaPage;
import com.example.aquaassistant.sena.ToDoListPage;
import com.example.aquaassistant.sena.SettingsMain;
import com.example.aquaassistant.zeynep.FavouritePlacesActivity;
import com.example.aquaassistant.zeynep.TanksPageActivity;
import com.example.aquaassistant.zulal.Creature;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.r0adkll.slidr.Slidr;


public class MainPage extends AppCompatActivity {

    private Button tanksButton;
    private Button profileButton;
    private Button settingButton;
    private Button encyclopediaButton;
    private Button notifTest;
    private TextView usernameDisplay;
    private ImageButton imageButtonProfilePicture;

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user.getDisplayName() != null) {
            String setString = "Welcome back, " + user.getDisplayName() + "!";
            usernameDisplay.setText(setString);
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
                                Glide.with(getApplicationContext()).load(task.getResult()).into(imageButtonProfilePicture);
                            }
                        }
                    });
        } else {
            imageButtonProfilePicture.setImageResource(R.drawable.profilepicture);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        notifTest = findViewById(R.id.openToDoList);
        tanksButton = findViewById(R.id.tankButton);
        profileButton = findViewById(R.id.profileButton);
        settingButton = findViewById(R.id.settingsButton);
        encyclopediaButton = findViewById(R.id.encyclopedia);
        usernameDisplay = findViewById(R.id.textView8);
        imageButtonProfilePicture = findViewById(R.id.imageButton5);
        //section below is to display the chosen username
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user.getDisplayName() != null) {
            String setString = "Welcome back, " + user.getDisplayName() + "!";
            usernameDisplay.setText(setString);
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
                                Glide.with(getApplicationContext()).load(task.getResult()).into(imageButtonProfilePicture);
                            }
                        }
                    });
        } else {
            imageButtonProfilePicture.setImageResource(R.drawable.profilepicture);
        }

        Slidr.attach(this);

    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, SettingsMain.class);
        startActivity(intent);
    }

    public void openTanks(View view) {
        Intent intent = new Intent(this, TanksPageActivity.class);
        startActivity(intent);
    }

    public void openEncyclopedia(View view) {
        Intent intent = new Intent(this, EncyclopediaPage.class);
        startActivity(intent);
    }

    public void openProfile(View view) {
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
    }

    public void goMapPage(View view) {
        Intent intent = new Intent(this, FavouritePlacesActivity.class);
        startActivity(intent);
    }

    public void openToDoList (View view) {
        Intent intent = new Intent(this, ToDoListPage.class);
        startActivity(intent);
    }
    public void goCreatures(View view) {
        Intent intent = new Intent(MainPage.this, Creature.class);
        startActivity(intent);
    }
}


