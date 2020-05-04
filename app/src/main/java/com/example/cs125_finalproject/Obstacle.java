package com.example.cs125_finalproject;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    public Rect getRectangle2() {
        return rectangle2;
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
        /*Bitmap mBitmap = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.corona2);
        mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, getRectangle().width(), getRectangle().height());
        canvas.drawBitmap(mBitmap, rectangle.left, rectangle.top, null);
        Bitmap mBitmap2 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.corona2);
        mBitmap = Bitmap.createBitmap(mBitmap2, getRectangle().width() + getRectangle2().left, 0, getRectangle2().width(), getRectangle2().height());
        canvas.drawBitmap(mBitmap, rectangle2.left, rectangle2.top, null);

    }*/
        if (rectangle.width() < Constants.SCREEN_WIDTH / 8) {
            Drawable drawable = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.coronavirus_shorter);
            drawable.mutate();
            drawable.setBounds(rectangle);
            drawable.draw(canvas);
            Drawable drawable2 = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.coronavirus);
            drawable2.setBounds(rectangle2);
            drawable2.draw(canvas);
        } else if (rectangle.width() < Constants.SCREEN_WIDTH / 4) {
            Drawable drawable = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.coronavirus_short);
            drawable.mutate();
            drawable.setBounds(rectangle);
            drawable.draw(canvas);
            Drawable drawable2 = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.coronavirus);
            drawable2.setBounds(rectangle2);
            drawable2.draw(canvas);
        } else if (rectangle2.width() < Constants.SCREEN_WIDTH / 8) {
            Drawable drawable = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.coronavirus_shorter);
            drawable.mutate();
            drawable.setBounds(rectangle2);
            drawable.draw(canvas);
            Drawable drawable2 = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.coronavirus);
            drawable2.setBounds(rectangle);
            drawable2.draw(canvas);
        } else if (rectangle2.width() < Constants.SCREEN_WIDTH / 4) {
            Drawable drawable = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.coronavirus_short);
            drawable.mutate();
            drawable.setBounds(rectangle2);
            drawable.draw(canvas);
            Drawable drawable2 = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.coronavirus);
            drawable2.setBounds(rectangle);
            drawable2.draw(canvas);
        } else {
            Drawable drawable = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.coronavirus);
            drawable.mutate();
            drawable.setBounds(rectangle);
            drawable.draw(canvas);
            Drawable drawable2 = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.coronavirus);
            drawable2.setBounds(rectangle2);
            drawable2.draw(canvas);
        }
    }
}
