package com.zechocapic.myandroidgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by zechocapic on 30/07/13.
 */
public class AndroidObstacle {
    private static String TAG = AndroidObstacle.class.getSimpleName();

    private int xPos;
    private int yPos;
    private Paint obstaclePaint;

    public AndroidObstacle(int xPos) {
        this.xPos = xPos;
        this.yPos = 100;
        this.obstaclePaint= new Paint();
        obstaclePaint.setColor(Color.RED);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(xPos - 32, yPos - 32, xPos + 32, yPos +32, obstaclePaint);
        Log.d(TAG, "x = " + xPos + " ; y = " + yPos);
    }

    public void move() {
        this.yPos++;
    }

}
