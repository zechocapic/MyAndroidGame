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
        Random random = new Random();
        int color = random.nextInt(6);
        if (color == 0) {
            this.bitmap = BitmapFactory.decodeResource(resources, R.drawable.gamecarblue);
        } else if (color == 1) {
            this.bitmap = BitmapFactory.decodeResource(resources, R.drawable.gamecarbrown);
        } else if (color == 2) {
            this.bitmap = BitmapFactory.decodeResource(resources, R.drawable.gamecargreen);
        } else if (color == 3) {
            this.bitmap = BitmapFactory.decodeResource(resources, R.drawable.gamecarlightblue);
        } else if (color == 4) {
            this.bitmap = BitmapFactory.decodeResource(resources, R.drawable.gamecarorange);
        } else if (color == 5) {
            this.bitmap = BitmapFactory.decodeResource(resources, R.drawable.gamecaryellow);
        }
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public static int getNbObstaclesAvoided() {
        return nbObstaclesAvoided;
    }

    public static void setNbObstaclesAvoided(int nbObstaclesAvoided) {
        GameObstacle.nbObstaclesAvoided = nbObstaclesAvoided;
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
