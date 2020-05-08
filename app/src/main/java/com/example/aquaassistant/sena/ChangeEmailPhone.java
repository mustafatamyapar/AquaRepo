package com.example.aquaassistant.sena;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.aquaassistant.R;
import com.r0adkll.slidr.Slidr;

public class ChangeEmailPhone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email_phone);
        Slidr.attach(this);
    }

    public void saveNewEmailPhoneNumber(View view) {
        //yeni girilen phone/email kullanıcıya kaydedilecek butona basılınca
    }
}
