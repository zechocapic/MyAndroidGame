package com.zechocapic.myandroidgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;

/**
 * Created by zechocapic on 03/08/13.
 */
public class GameScenery {
    //private static String TAG = GameScenery.class.getSimpleName();

    private int speed;
    private int yPos;
    private Bitmap treeBitmap;
    private Paint roadPaint;
    private Paint grassPaint;
    //private Paint linePaint;

    public GameScenery(Resources resources, int speed) {
        this.speed = speed;
        this.yPos = 0;
        this.roadPaint = new Paint();
        this.roadPaint.setColor(Color.GRAY);
        this.grassPaint = new Paint();
        this.treeBitmap = BitmapFactory.decodeResource(resources, R.drawable.tree);
        this.grassPaint.setColor(Color.GREEN);
        /*this.linePaint = new Paint();
        this.linePaint.setARGB(255, 255, 255, 255);
        this.linePaint.setStyle(Paint.Style.STROKE);
        this.linePaint.setStrokeWidth(5);
        this.linePaint.setPathEffect(new DashPathEffect(new float[] {100, 300}, 0));*/
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void draw(Canvas canvas) {
        int height = canvas.getHeight();
        canvas.drawRect(0, 0, 133, height, grassPaint);
        canvas.drawRect(134, 0, 634,  height, roadPaint);
        canvas.drawRect(635, 0, 768,  height, grassPaint);
        canvas.drawBitmap(treeBitmap, 66 - (treeBitmap.getWidth() / 2), yPos -(treeBitmap.getHeight() / 2), null);
        canvas.drawBitmap(treeBitmap, 702 - (treeBitmap.getWidth() / 2), yPos -(treeBitmap.getHeight() / 2), null);
        /*canvas.drawCircle(66, yPos, 32, treePaint);
        canvas.drawCircle(702, yPos, 32, treePaint);
        canvas.drawCircle(66, yPos + 400, 32, treePaint);
        canvas.drawCircle(702, yPos + 400, 32, treePaint);
        canvas.drawCircle(66, yPos + 800, 32, treePaint);
        canvas.drawCircle(702, yPos + 800, 32, treePaint);
        canvas.drawLine(234, yPos, 234, height + 100, linePaint);
        canvas.drawLine(334, yPos, 334, height + 100, linePaint);
        canvas.drawLine(434, yPos, 434, height + 100, linePaint);
        canvas.drawLine(534, yPos, 534, height + 100, linePaint);*/
    }

    public void move() {
        if (yPos < 1200) {
            this.yPos += this.speed;
        }
        else {
            yPos = 0;
        }
    }

}
