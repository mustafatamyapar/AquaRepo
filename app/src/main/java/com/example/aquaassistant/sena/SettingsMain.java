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
    Button sign_out;
    Button deleteAccountButton;
    private FirebaseAuth firebaseAuth;
    private SlidrInterface slidr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsmain);
        sign_out = findViewById(R.id.sign_out5);
        deleteAccountButton = findViewById(R.id.deleteAccountButton);
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

    public void lockSlide(View v) {
        slidr.lock();
    }

    public void unlockSlide(View v) {
        slidr.unlock();
    }
}
