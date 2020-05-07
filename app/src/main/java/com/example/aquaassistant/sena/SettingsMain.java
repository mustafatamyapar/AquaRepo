package com.example.aquaassistant.sena;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aquaassistant.zulal.MainActivity;
import com.example.aquaassistant.R;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsMain extends AppCompatActivity {
    Button sign_out;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsmain);
        sign_out = findViewById(R.id.sign_out);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void signOutClicked(View view){
        firebaseAuth.signOut();
        Intent intentToMain = new Intent ( SettingsMain.this, MainActivity.class);
        startActivity(intentToMain);

    }

    public void changeUsernamePage(View view) {
        Intent intent = new Intent(getApplicationContext(), ChangeUsername.class);
        startActivity(intent);
    }
    public void changePasswordPage(View view) {
        Intent intent = new Intent(getApplicationContext(), ChangePassword.class);
        startActivity(intent);
    }
    public void changeEmailPhoneNumberPage(View view) {
        Intent intent = new Intent(getApplicationContext(), ChangeEmailPhone.class);
        startActivity(intent);
    }
    public void changeProfilePicturePage(View view) {
        Intent intent = new Intent(getApplicationContext(), ChangeProfilePicture.class);
        startActivity(intent);
    }
}
