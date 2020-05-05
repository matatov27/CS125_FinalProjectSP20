package com.example.cs125_finalproject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private Button aboutUsButton;
    private Button gameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        gameOver = findViewById(R.id.test);
        gameOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testGameOver();
            }
        });

    }
    public void openGameActivity() {
        Intent gameIntent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(gameIntent);
        finish();
    }
    public void testGameOver() {
        Intent gameIntent = new Intent(MainActivity.this, GameOverActivity.class);
        startActivity(gameIntent);
        finish();
    }
    public void openAboutUsActivity() {
        Intent aboutUsIntent = new Intent(MainActivity.this, AboutUsActivity.class);
        startActivity(aboutUsIntent);
    }
}
