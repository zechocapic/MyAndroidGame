package com.zechocapic.myandroidgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by zechocapic on 03/08/13.
 */
public class GameOver {
    private Paint gameOverPaint;

    public GameOver() {
        gameOverPaint = new Paint();
        gameOverPaint.setColor(Color.WHITE);
        gameOverPaint.setTextSize(100);
        gameOverPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void draw (Canvas canvas) {
        canvas.drawColor(Color.RED);
        int xPos = canvas.getWidth() / 2;
        int yPos = (int) ((canvas.getHeight() /2) - ((gameOverPaint.descent() + gameOverPaint.ascent()) / 2));
        canvas.drawText("Game Over", xPos, yPos, gameOverPaint);
    }

}
