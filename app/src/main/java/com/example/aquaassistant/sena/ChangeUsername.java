package com.example.aquaassistant.sena;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.aquaassistant.R;
import com.r0adkll.slidr.Slidr;

public class ChangeUsername extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_username);
        Slidr.attach(this);
    }

    public void saveNewUsername(View view) {
        //burada editTextUsername'den gelen yeni username
        //kullanıcının username'ine atanacak butona basılınca
    }
}
