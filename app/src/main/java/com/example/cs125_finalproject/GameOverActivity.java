package com.example.cs125_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

//this activity starts when the game is over and resets the game but keeps the highscore

public class GameOverActivity extends AppCompatActivity {
    private Button backButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        backButton = findViewById(R.id.resetButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameplayScene.gameOver = false;
                openGameActivity();
            }
        });
    }
    public void openGameActivity() {
        Intent reset = new Intent(this, GameActivity.class);
        startActivity(reset);
    }
}
