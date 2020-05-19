package com.example.aquaassistant.kerem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.aquaassistant.kerem.Ranks;

import es.dmoral.toasty.Toasty;


/**
 * Main Page - The hub for all the buttons and also shows some information
 *
 * @author Kerem Tayhan
 * @version 1.0 (May 19, 2020) - completed
 */
public class MainPage extends AppCompatActivity {
    private Button tanksButton;
    private Button profileButton;
    private Button settingButton;
    private Button encyclopediaButton;
    private Button toDoListButton;
    private TextView usernameDisplay;
    private ImageButton imageButtonProfilePicture;
    private TextView rankDisplay;
    private long backPressedTime;
    private Toast backToast;
    public static SQLiteDatabase experienceDatabase;

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
            imageButtonProfilePicture.setImageResource(R.drawable.emptypicture);
        }
        experienceDatabase = MainPage.this.openOrCreateDatabase("Experience", MODE_PRIVATE, null);
        experienceDatabase.execSQL("CREATE TABLE IF NOT EXISTS experience (id INTEGER PRIMARY KEY , experience VARCHAR)");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        toDoListButton = findViewById(R.id.openToDoList);
        tanksButton = findViewById(R.id.tankButton);
        profileButton = findViewById(R.id.profileButton);
        settingButton = findViewById(R.id.settingsButton);
        encyclopediaButton = findViewById(R.id.encyclopedia);
        usernameDisplay = findViewById(R.id.textView8);
        imageButtonProfilePicture = findViewById(R.id.imageButton5);

        experienceDatabase = MainPage.this.openOrCreateDatabase("Experience", MODE_PRIVATE, null);
        experienceDatabase.execSQL("CREATE TABLE IF NOT EXISTS experience (id INTEGER PRIMARY KEY , experience VARCHAR)");
        String sqlString = "INSERT INTO experience (experience) VALUES (?)";
        SQLiteStatement sqLiteStatement = experienceDatabase.compileStatement(sqlString);
        sqLiteStatement.bindString(1, "0");
        sqLiteStatement.execute();

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
            imageButtonProfilePicture.setImageResource(R.drawable.emptypicture);
        }

        Slidr.attach(this);

    }

    public void onBackPressed () {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toasty.info(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    /**
     * this method is responsible for the button for opening the settings
     */
    public void openSettings(View view) {
        Intent intent = new Intent(this, SettingsMain.class);
        startActivity(intent);
    }

    /**
     * this method is responsible for the button for opening the tank page
     */
    public void openTanks(View view) {
        Intent intent = new Intent(this, TanksPageActivity.class);
        startActivity(intent);
    }

    /**
     * this method is responsible for the button for opening the encyclopedia page
     */
    public void openEncyclopedia(View view) {
        Intent intent = new Intent(this, EncyclopediaPage.class);
        startActivity(intent);
    }

    /**
     * this method is responsible for the button for opening the profile page
     */
    public void openProfile(View view) {
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
    }

    /**
     * this method is responsible for the button for opening the map page
     */
    public void goMapPage(View view) {
        Intent intent = new Intent(this, FavouritePlacesActivity.class);
        startActivity(intent);
    }

    /**
     * this method is responsible for the button for opening the to-do list
     */
    public void openToDoList(View view) {
        Intent intent = new Intent(this, ToDoListPage.class);
        startActivity(intent);
    }

    /**
     * this method is responsible for the button for opening the creature page
     */
    public void goCreatures(View view) {
        Intent intent = new Intent(MainPage.this, Creature.class);
        startActivity(intent);
    }

}