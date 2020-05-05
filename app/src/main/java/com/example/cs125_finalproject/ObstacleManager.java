package com.example.cs125_finalproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class ObstacleManager {
    //higherIndex = lower on screen = higher y values
    private ArrayList<Obstacle> obstacles;
    private int playerGap;
    private int obstacleGap;
    private int obstacleHeight;
    private long startTime;
    private long initTime;
    private int score = 0;
    public static int highScore;


    public ObstacleManager(int playerGap, int obstacleGap, int obstacleHeight, int highScore) {
        this.playerGap = playerGap;
        this.obstacleGap = obstacleGap;
        this.obstacleHeight = obstacleHeight;
        this.highScore = highScore;
        startTime = initTime = System.currentTimeMillis();
        obstacles = new ArrayList<>();
        populateObstacles();
    }

    public boolean playerCollide(RectPlayer player) {
        for(Obstacle ob: obstacles) {
            if(ob.playerCollide(player)) {
                return true;
            }
        }
        return false;
    }

    private void populateObstacles() {
        int currentY = -5 * Constants.SCREEN_HEIGHT / 4;
        while(currentY < 0) {
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(new Obstacle(obstacleHeight, xStart, currentY, playerGap));
            currentY += obstacleHeight + obstacleGap;
        }
    }

    public void update() {
        if(startTime < Constants.INIT_TIME)
            startTime = Constants.INIT_TIME;
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = ((float) Math.sqrt(1 + (startTime - initTime) / 10000.0) * Constants.SCREEN_HEIGHT / 10000.0f);
        for (Obstacle ob : obstacles) {
            ob.incrementY(speed * elapsedTime);
        }
        if (obstacles.get(obstacles.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(0, new Obstacle(obstacleHeight, xStart,
                    obstacles.get(0).getRectangle().top - obstacleHeight - obstacleGap,
                    playerGap));
            obstacles.remove(obstacles.size() - 1);
            score++;
        }
        if (score > highScore) {
            highScore = score;
        }
    }
    public void draw(Canvas canvas) {
        for(Obstacle ob : obstacles) {
            ob.draw(canvas);
            Paint paint = new Paint();
            paint.setTextSize(50);
            paint.setColor(Color.BLACK);
            canvas.drawText("" + score, 50, 100, paint);
            canvas.drawText("High Score: " + highScore, (2 * Constants.SCREEN_WIDTH / 3),
                    100, paint);
        }
    }
}
