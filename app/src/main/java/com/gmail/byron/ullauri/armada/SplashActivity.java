package com.gmail.byron.ullauri.armada;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    /*

    Displays fullscreen background found in /drawable/screen_splash.xml
    using SplashTheme found in /values/styles

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(3000);
            startActivity(new Intent(SplashActivity.this, MenuActivity.class));
            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}