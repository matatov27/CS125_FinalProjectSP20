package com.example.cs125_finalproject;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    private MediaPlayer mediaPlayer;

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

        // this is isn't from the tutorial, I just decided to try adding music
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.saftey);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
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
        if(mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.saftey);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }
}
