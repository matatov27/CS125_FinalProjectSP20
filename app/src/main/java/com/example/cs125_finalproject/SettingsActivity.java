package com.example.cs125_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    private Button backButton;
    private Button setCharacter1;
    private Button setCharacter2;

    //this activity allows you to choose music? and player

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        setCharacter1 = findViewById(R.id.setCharacter1);
        setCharacter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.WHICH_PLAYER = 1;
            }
        });
        setCharacter2 = findViewById(R.id.setCharacter2);
        setCharacter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.WHICH_PLAYER = 2;
            }
        });
    }
    public void openMainActivity() {
        Intent aboutUsIntent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(aboutUsIntent);
        finish();
    }
}