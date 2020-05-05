package com.example.cs125_finalproject;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

//https://www.youtube.com/watch?v=Gs5OTAldsMc
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
        Bitmap mBitmap = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.reducedpic);
        Bitmap mBitmap1 = Bitmap.createBitmap(mBitmap, 0, 0, rectangle.width(), rectangle.height());
        canvas.drawBitmap(mBitmap1, rectangle.left, rectangle.top, null);
        Bitmap mBitmap2 = Bitmap.createBitmap(mBitmap, rectangle.width() + rectangle2.left, 0, rectangle2.width(), rectangle2.height());
        canvas.drawBitmap(mBitmap2, rectangle2.left, rectangle2.top, null);

    }
}
