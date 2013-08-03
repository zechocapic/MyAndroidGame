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
        gameOverPaint.setTextSize(60);
    }

    public void draw (Canvas canvas) {
        canvas.drawText("Game Over", 300, 600, gameOverPaint);
    }

}
