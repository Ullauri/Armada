package com.gmail.byron.ullauri.armada;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playButton:
                Intent game = new Intent(Menu.this, MainGameActivity.class);
                startActivity(game);
                break;
            case R.id.scoreBoardButton:

                break;
            case R.id.optionsButton:

                break;

            case R.id.aboutButton:

                break;
            default:
                System.exit(1);
        }

        finish();
    }

}
