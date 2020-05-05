package com.example.cs125_finalproject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

//this activity has the main screen, about us button, click player to start, and the green player

public class MainActivityPlayer2 extends AppCompatActivity {
    private Button startButton;
    private Button aboutUsButton;
    private Button settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_player2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActivity();
            }
        });
        aboutUsButton = findViewById(R.id.aboutUsButton);
        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutUsActivity();
            }
        });
        settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettingsActivity();
            }
        });
    }
    public void openGameActivity() {
        Intent gameIntent = new Intent(MainActivityPlayer2.this, GameActivity.class);
        startActivity(gameIntent);
        finish();
    }
    public void openAboutUsActivity() {
        Intent aboutUsIntent = new Intent(MainActivityPlayer2.this, AboutUsActivity.class);
        startActivity(aboutUsIntent);
    }
    public void openSettingsActivity() {
        Intent settingsIntent = new Intent(MainActivityPlayer2.this, SettingsActivity.class);
        startActivity(settingsIntent);
    }
}
