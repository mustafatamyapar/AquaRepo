package com.example.aquaassistant.sena;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aquaassistant.R;

public class SettingsMain extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsmain);
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
