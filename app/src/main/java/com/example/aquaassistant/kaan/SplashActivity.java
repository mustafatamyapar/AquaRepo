package com.example.aquaassistant.kaan;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aquaassistant.R;

/**
 * Splash Activity - Created to salute user with a simple photograph and sound.
 *
 * @author Kaan Özkan
 * @version 1.0 (May 19, 2020) - completed
 */

public class SplashActivity extends AppCompatActivity {

    MediaPlayer mysong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mysong = MediaPlayer.create(SplashActivity.this, R.raw.wave);
        mysong.start();
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
