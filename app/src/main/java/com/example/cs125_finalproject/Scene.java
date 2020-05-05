package com.example.cs125_finalproject;

import android.graphics.Canvas;
import android.view.MotionEvent;
//https://www.youtube.com/watch?v=AL92LrbDNY0
public interface Scene {
    void update();
    void draw(Canvas canvas);
    void recieveTouch(MotionEvent event);
}
