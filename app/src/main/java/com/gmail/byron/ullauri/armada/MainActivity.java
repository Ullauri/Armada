package com.gmail.byron.ullauri.armada;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(3000);

            Intent menu = new Intent(MainActivity.this, Menu.class);
            startActivity(menu);

            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

