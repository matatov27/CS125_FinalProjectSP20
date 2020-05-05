package com.example.cs125_finalproject;

import android.graphics.Canvas;
import android.graphics.Rect;
//https://www.youtube.com/watch?v=l8-1ZtxA29Y

public class AnimationManager {
    private Animation[] animArray;
    private int animIndex = 0;

    public AnimationManager(Animation[] animations) {
        this.animArray = animations;
    }

    public void playAnim(int index) {
        for(int i = 0; i < animArray.length; i++) {
            if(i == index) {
                if(!animArray[index].isPlaying())
                    animArray[i].play();
            } else
                animArray[i].stop();
        }
        animIndex = index;
    }

    public void draw(Canvas canvas, Rect rect) {
        if(animArray[animIndex].isPlaying())
            animArray[animIndex].draw(canvas, rect);
    }

    public void update() {
        if(animArray[animIndex].isPlaying())
            animArray[animIndex].update();
    }
}
