package com.example.cs125_finalproject;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class Obstacle implements GameObject {
    private Rect rectangle;
    private Rect rectangle2;


    public Obstacle(int rectHeight, int startX, int startY, int playerGap) {
        rectangle = new Rect(0, startY, startX, startY + rectHeight);
        rectangle2 = new Rect(startX + playerGap, startY, Constants.SCREEN_WIDTH, startY + rectHeight);
    }

    public Rect getRectangle() {
        return rectangle;
    }

    public void incrementY(float y) {
        rectangle.top += y;
        rectangle.bottom += y;
        rectangle2.top += y;
        rectangle2.bottom += y;
    }

    public boolean playerCollide(RectPlayer player) {
        return Rect.intersects(rectangle, player.getRectangle()) ||
                Rect.intersects(rectangle2, player.getRectangle());
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        Drawable drawable = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.coronavirus);
        drawable.mutate();
        drawable.setBounds(rectangle);
        drawable.draw(canvas);
        Drawable drawable2 = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.coronavirus);
        drawable2.setBounds(rectangle2);
        drawable2.draw(canvas);
    }
}
