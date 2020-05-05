package com.example.cs125_finalproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;

public class RectPlayer implements GameObject {
    private Rect rectangle;

    private Animation idle;
    private Animation walkRight;
    private Animation walkLeft;
    private AnimationManager animManager;

    public Rect getRectangle() {
        return rectangle;
    }

    public RectPlayer(Rect rectangle) {
        this.rectangle = rectangle;
        Bitmap idleImg = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.facemask1);
        Bitmap walk1 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.facemaskwalk1);
        Bitmap walk2 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.facemaskwalk2);;
        if (Constants.WHICH_PLAYER == 2) {
            idleImg = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.aliengreen);
            walk1 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.aliengreenwalk1);
            walk2 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.aliengreenwalk2);
        }


        idle = new Animation(new Bitmap[]{idleImg}, 2);
        walkRight = new Animation(new Bitmap[]{walk1, walk2}, 0.5f);

        Matrix m = new Matrix();
        m.preScale(-1, 1);
        walk1 = Bitmap.createBitmap(walk1, 0, 0, walk1.getWidth(), walk1.getHeight(), m, false);
        walk2 = Bitmap.createBitmap(walk2, 0, 0, walk2.getWidth(), walk2.getHeight(), m, false);

        walkLeft = new Animation(new Bitmap[]{walk1, walk2}, 0.5f);

        animManager = new AnimationManager(new Animation[]{idle, walkRight, walkLeft});
    }

    @Override
    public void draw(Canvas canvas) {
        animManager.draw(canvas, rectangle);
    }

    @Override
    public void update() {
        animManager.update();
    }

    public void update(Point point) {
        float oldLeft = rectangle.left;

        rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height() / 2,
                point.x + rectangle.width() / 2, point.y + rectangle.height() / 2);

        int state = 0;
        if(rectangle.left - oldLeft > 5)
            state = 1;
        else if(rectangle.left - oldLeft < -5)
            state = 2;

        animManager.playAnim(state);
        animManager.update();

    }
}
