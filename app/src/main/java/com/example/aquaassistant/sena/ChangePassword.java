package com.example.aquaassistant.sena;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.aquaassistant.R;
import com.r0adkll.slidr.Slidr;

public class ChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Slidr.attach(this);
    }

    public void saveNewPassword(View view) {
        //the newly entered password will be saved as
        //the user new password when the button is clicked
    }
}
