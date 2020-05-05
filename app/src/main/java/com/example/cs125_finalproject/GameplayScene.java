package com.example.cs125_finalproject;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

public class GameplayScene extends GameActivity implements Scene {
    private RectPlayer player;
    private Point playerPoint;
    private ObstacleManager obstacleManager;
    private boolean movingPlayer = false;

    public static boolean gameOver = false;
    private long gameOverTime;
    private long frameTime;

    private Rect r = new Rect();

    public GameplayScene() {
        player = new RectPlayer(new Rect(100, 100,200, 200));
        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, 3 * Constants.SCREEN_HEIGHT / 4);
        player.update(playerPoint);
        obstacleManager = new ObstacleManager(300, 450,300, ObstacleManager.highScore);
        frameTime = System.currentTimeMillis();
    }
    public void reset() {
        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, 3 * Constants.SCREEN_HEIGHT / 4);
        player.update(playerPoint);
        obstacleManager = new ObstacleManager(300, 450,300, ObstacleManager.highScore);
        movingPlayer = false;
    }

    public void recieveTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(GameActivity.startGame == 0) {
                    movingPlayer = true;
                    GameActivity.startGame++;
                    break;
                }
                if (!gameOver && player.getRectangle().contains((int) event.getX(),
                        (int) event.getY())) {
                    movingPlayer = true;
                    break;
                }
                if(gameOver) {
                    reset();
                    gameOver = false;
                }
            case MotionEvent.ACTION_MOVE:
                if (movingPlayer && !gameOver) {
                    playerPoint.set((int) event.getX(), (int) event.getY());
                    break;
                }
            case MotionEvent.ACTION_UP:
                movingPlayer = false;
                break;
        }

    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        player.draw(canvas);
        obstacleManager.draw(canvas);
        if (gameOver) {
            Paint paint =  new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.BLACK);
            drawCenterText(canvas, paint);
            paint.setTextSize(50);
            paint.setTextAlign(Paint.Align.LEFT);
            canvas.getClipBounds(r);
            int cHeight = r.height();
            int cWidth = r.width();
            String text = "keep your distance...";
            paint.getTextBounds(text, 0, text.length(), r);
            float x = cWidth / 2f - r.width() / 2f - r.left;
            float y = cHeight / 2f - r.height() / 2f - r.bottom;
            canvas.drawText(text, x, y + 100, paint);
        }
    }

    public void update() {
        if (!gameOver) {
            if(frameTime < Constants.INIT_TIME)
                frameTime = Constants.INIT_TIME;
            int elapsedTime = (int)(System.currentTimeMillis() - frameTime);
            frameTime = System.currentTimeMillis();
            if(playerPoint.x < 0)
                playerPoint.x = 0;
            else if(playerPoint.x > Constants.SCREEN_WIDTH)
                playerPoint.x = Constants.SCREEN_WIDTH;
            if(playerPoint.y < 0)
                playerPoint.y = 0;
            else if(playerPoint.y > Constants.SCREEN_HEIGHT)
                playerPoint.y = Constants.SCREEN_HEIGHT;

            player.update(playerPoint);
            obstacleManager.update();
            if (obstacleManager.playerCollide(player)) {
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
            }
        }

    }
    private void drawCenterText(Canvas canvas, Paint paint) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds("GAME OVER", 0, "GAME OVER".length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f - r.height() / 2f - r.bottom;
        canvas.drawText("GAME OVER", x, y, paint);
    }
}