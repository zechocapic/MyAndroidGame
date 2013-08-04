package com.zechocapic.myandroidgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Created by zechocapic on 04/08/13.
 */
public class GameScore {
    private Paint gameScorePaint;

    public GameScore() {
        gameScorePaint = new Paint();
        gameScorePaint.setColor(Color.BLACK);
        gameScorePaint.setTextSize(40);
        gameScorePaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        gameScorePaint.setTextAlign(Paint.Align.RIGHT);
    }

    public void draw(Canvas canvas, int score) {
        canvas.drawText("Score : " + score, canvas.getWidth(), 0 + gameScorePaint.getTextSize(), gameScorePaint);
    }
}
