package com.zechocapic.myandroidgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by zechocapic on 30/07/13.
 */
public class AndroidCar {
    private static String TAG = AndroidCar.class.getSimpleName();

    public final static int MOVE_CAR_LEFT = -1;
    public final static int MOVE_CAR_RIGHT = 1;

    private int xPos;
    private int yPos;
    private int lateralSpeed;
    private Paint carPaint;

    public AndroidCar(int lateralSpeed) {
        this.xPos = 150;
        this.yPos = 1000;
        this.lateralSpeed = lateralSpeed;
        this.carPaint = new Paint();
        carPaint.setColor(Color.WHITE);
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(xPos - 32, yPos - 32, xPos + 32, yPos +32, carPaint);
        Log.d(TAG, "x = " + xPos + " ; y = " + yPos);
    }

    public void move(int movement) {
        if (movement == MOVE_CAR_LEFT) {
            this.xPos -= lateralSpeed;
        } else {
            this.xPos += lateralSpeed;
        }
    }

}
