package com.zechocapic.myandroidgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by zechocapic on 30/07/13.
 */
public class AndroidVoiture {
    private static String TAG = AndroidVoiture.class.getSimpleName();

    private int xPos;
    private int yPos;
    private Paint voiturePaint;

    public AndroidVoiture () {
        this.xPos = 150;
        this.yPos = 1000;
        this.voiturePaint = new Paint();
        voiturePaint.setColor(Color.WHITE);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(xPos - 32, yPos - 32, xPos + 32, yPos +32, voiturePaint);
        Log.d(TAG, "x = " + xPos + " ; y = " + yPos);
    }

    public void move() {
        if (xPos < 384) {
            this.xPos = 618;
        } else {
            this.xPos = 150;
        }
    }

}
