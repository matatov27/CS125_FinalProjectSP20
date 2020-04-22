package com.example.cs125_finalproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;

public class GameplayScene implements Scene {
    private RectPlayer player;
    private Point playerPoint;
    private ObstacleManager obstacleManager;
    private boolean movingPlayer = false;
    private boolean gameOver = false;
    private long gameOverTime;
    private Rect r = new Rect();
    private MediaPlayer mediaPlayer;

    public GameplayScene() {
        player = new RectPlayer(new Rect(100, 100,200, 200),
                Color.rgb(255, 0,0));
        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, 3 * Constants.SCREEN_HEIGHT / 4);
        player.update(playerPoint);
        obstacleManager = new ObstacleManager(200, 350,75, Color.BLUE, ObstacleManager.highScore);
    }
    public void reset() {
        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, 3 * Constants.SCREEN_HEIGHT / 4);
        player.update(playerPoint);
        obstacleManager = new ObstacleManager(200, 350,75, Color.BLUE, ObstacleManager.highScore);
        movingPlayer = false;
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;


    }

    @Override
    public void recieveTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(MainActivity.startGame == 0) {
                    movingPlayer = true;
                    MainActivity.startGame++;
                    break;
                }
                if (!gameOver && player.getRectangle().contains((int) event.getX(),
                        (int) event.getY())) {
                    movingPlayer = true;
                    break;
                }
                if(gameOver && System.currentTimeMillis() - gameOverTime >= 500) {
                    reset();
                    gameOver = false;
                    MainActivity.startGame = 0;
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

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        player.draw(canvas);
        obstacleManager.draw(canvas);
        if (gameOver) {
            Paint paint =  new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.BLACK);
            drawCenterText(canvas, paint, "GAME OVER");
        }
    }

    @Override
    public void update() {
        if (!gameOver) {
            player.update(playerPoint);
            if (MainActivity.startGame > 0) {
                obstacleManager.update();
            }
            if (obstacleManager.playerCollide(player)) {
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
            }
        }

    }
    private void drawCenterText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f - r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }
}
