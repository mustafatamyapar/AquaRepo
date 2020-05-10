package com.example.aquaassistant.sena;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.aquaassistant.R;
import com.r0adkll.slidr.Slidr;

public class ChangeEmail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        Slidr.attach(this);
    }

    public void saveNewEmail(View view) {
        //yeni girilen phone/email kullanıcıya kaydedilecek butona basılınca
    }
}
