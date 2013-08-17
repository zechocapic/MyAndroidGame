package com.zechocapic.myandroidgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Created by zechocapic on 17/08/13.
 */
public class GameLevel {
    private int level;
    private Paint gameLevelPaint;

    public GameLevel() {
        level = 1;
        gameLevelPaint = new Paint();
        gameLevelPaint.setColor(Color.BLACK);
        gameLevelPaint.setTextSize(40);
        gameLevelPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        gameLevelPaint.setTextAlign(Paint.Align.LEFT);
    }

    public int getLevel() {
        return level;
    }

    public void update() {
        level = 1 + GameObstacle.getNbObstaclesAvoided() / 10;
    }

    public void draw(Canvas canvas) {
        canvas.drawText("Level : " + level, 0, gameLevelPaint.getTextSize(), gameLevelPaint);
    }
}
