package com.zechocapic.myandroidgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by zechocapic on 30/07/13.
 */
public class GameCar {
    private static String TAG = GameCar.class.getSimpleName();

    public final static int MOVE_CAR_LEFT = -1;
    public final static int MOVE_CAR_RIGHT = 1;
    public final static int MOVE_CAR_NOT = 0;

    private int xPos;
    private int yPos;
    private int lateralSpeed;
    private Paint carPaint;

    public GameCar(int lateralSpeed) {
        this.xPos = GameSurfaceView.GAME_WIDTH / 2;
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
        canvas.drawRect(xPos - 32, yPos - 48, xPos + 32, yPos + 48, carPaint);
        Log.d(TAG, "x = " + xPos + " ; y = " + yPos);
    }

    public void move(int movement) {
        if (movement == MOVE_CAR_LEFT) {
            if (this.xPos > 166) {
                this.xPos -= lateralSpeed;
            }
        } else if  (movement == MOVE_CAR_RIGHT) {
            if (this.xPos < 602) {
                this.xPos += lateralSpeed;
            }
        }
    }

}
