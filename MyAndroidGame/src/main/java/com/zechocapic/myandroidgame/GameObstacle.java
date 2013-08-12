package com.zechocapic.myandroidgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by zechocapic on 30/07/13.
 */
public class GameObstacle {
    //private static String TAG = GameObstacle.class.getSimpleName();

    private static int nbObstaclesAvoided = 0;

    private int xPos;
    private int yPos;
    private int speed;
    private Bitmap bitmap;

    public GameObstacle(Resources resources, int xPos, int speed) {
        this.xPos = xPos;
        this.yPos = 0;
        this.speed = speed;
        this.bitmap = BitmapFactory.decodeResource(resources, R.drawable.gameobstacle);
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public static int getNbObstaclesAvoided() {
        return nbObstaclesAvoided;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, xPos - (bitmap.getWidth() / 2), yPos -(bitmap.getHeight() / 2), null);
    }

    public void move() {
        if (yPos < 1200) {
            yPos += speed;
        } else {
            Random random = new Random();
            this.speed = random.nextInt(10) + 1;
            yPos = 0;
            nbObstaclesAvoided++;
        }

    }

}
