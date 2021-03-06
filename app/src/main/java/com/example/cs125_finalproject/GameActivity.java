package com.example.cs125_finalproject;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

//this activity starts the game
public class GameActivity extends Activity {

    private MediaPlayer mediaPlayer;
    public static int startGame = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        //starts the music when the game starts, the MUSIC_SETTINGS constant could be used to make audio options in the future
        if (Constants.MUSIC_SETTINGS) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.saftey);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
        setContentView(new GamePanel(this));
    }
    //pauses the music when the app is exited
    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
    //resumes the music when app is entered again
    @Override
    protected void onResume() {
        super.onResume();
        if(mediaPlayer == null && Constants.MUSIC_SETTINGS) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.saftey);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }
}
