package com.example.aquaassistant.sena;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aquaassistant.R;
import com.example.aquaassistant.kerem.ProfilePage;
import com.r0adkll.slidr.Slidr;

public class ChangeUsername extends AppCompatActivity {

    EditText editTextUsername;
    Button saveUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_username);
        Slidr.attach(this);

        editTextUsername = findViewById(R.id.editTextUsername);
        saveUsername = findViewById(R.id.saveUsername);
    }

    //this method does not update on database!
    public void saveNewUsername(View view) {

    }
}
