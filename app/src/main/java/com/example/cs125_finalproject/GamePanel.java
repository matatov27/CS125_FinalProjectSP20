package com.example.cs125_finalproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private SceneManager manager;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);

        manager = new SceneManager();

        setFocusable(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(true) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch(Exception e) {e.printStackTrace(); }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        manager.recieveTouch(event);

        return true;
        //return super.onTouchEvent(event);
    }

    public void update(){
        manager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        manager.draw(canvas);

        super.draw(canvas);

        /*if (MainActivity.startGame == 0) {
            Paint paint =  new Paint();
            paint.setTextSize(80);
            paint.setColor(Color.BLACK);
            drawCenterText(canvas, paint, "TAP TO START");
        }*/
    }

}
