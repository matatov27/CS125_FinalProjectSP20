package com.example.cs125_finalproject;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.MainThread;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);

    }

    @Override
    public void surfaceChange(SurfaceHolder holder, int format, int width, int height) { }

    @Override
    public void SurfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void Surface
}
