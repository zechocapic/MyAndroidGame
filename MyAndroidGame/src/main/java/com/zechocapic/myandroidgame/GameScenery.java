package com.zechocapic.myandroidgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

/**
 * Created by zechocapic on 03/08/13.
 */
public class GameScenery {
    private static String TAG = GameScenery.class.getSimpleName();

    private int speed;
    private int yPos;
    private Paint roadPaint;
    private Paint treePaint;
    private Paint grassPaint;
    private Paint linePaint;

    public GameScenery(int speed) {
        this.speed = speed;
        this.yPos = 0;
        this.roadPaint = new Paint();
        this.roadPaint.setColor(Color.GRAY);
        this.treePaint = new Paint();
        this.treePaint.setColor(Color.BLUE);
        this.grassPaint = new Paint();
        this.grassPaint.setColor(Color.GREEN);
        this.linePaint = new Paint();
        this.linePaint.setARGB(255, 255, 255, 255);
        this.linePaint.setStyle(Paint.Style.STROKE);
        this.linePaint.setStrokeWidth(5);
        this.linePaint.setPathEffect(new DashPathEffect(new float[] {100, 300}, 0));
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(0, 0, 133, GameSurfaceView.GAME_HEIGHT, grassPaint);
        canvas.drawRect(134, 0, 634,  GameSurfaceView.GAME_HEIGHT, roadPaint);
        canvas.drawRect(635, 0, 768,  GameSurfaceView.GAME_HEIGHT, grassPaint);
        canvas.drawLine(234, yPos, 234, GameSurfaceView.GAME_HEIGHT + 100, linePaint);
        canvas.drawLine(334, yPos, 334, GameSurfaceView.GAME_HEIGHT + 100, linePaint);
        canvas.drawLine(434, yPos, 434, GameSurfaceView.GAME_HEIGHT + 100, linePaint);
        canvas.drawLine(534, yPos, 534, GameSurfaceView.GAME_HEIGHT + 100, linePaint);
        Log.d(TAG, "Scenery drawn");
    }

    public void move() {
        if (yPos < 400) {
            this.yPos += this.speed;
        }
        else {
            yPos = 0;
        }
    }

}
