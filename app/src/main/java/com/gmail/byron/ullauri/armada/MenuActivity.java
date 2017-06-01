package com.gmail.byron.ullauri.armada;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void startActivity(View v) {
        switch (v.getId()) {
            case R.id.playButton:
                startActivity(new Intent(this, MainGameActivity.class));
                break;
            case R.id.scoreBoardButton:

                break;
            case R.id.optionsButton:

                break;
            case R.id.aboutButton:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            default:
                System.exit(1);
        }
    }

}
