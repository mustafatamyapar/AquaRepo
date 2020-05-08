package com.example.aquaassistant.sena;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.aquaassistant.R;
import com.r0adkll.slidr.Slidr;

public class ChangeProfilePicture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile_picture);
        Slidr.attach(this);
    }

    public void chooseNewPicture(View view) {
        //choosePicture adlı butona basılınca telefonun galerisi
        //açılacak ve fotoğraf seçilebilecek
    }

    //bir de fotoğraf seçilince ekranda görünen imageView'da o fotoğrafın görünmesi lazım

    public void saveNewProfilePicture(View view) {
        //seçilmiş olan resim kullanıcının yeni profil resmi
        //olarak kaydedilecek butona basılınca
    }
}
