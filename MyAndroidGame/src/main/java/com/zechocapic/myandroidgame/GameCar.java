package com.zechocapic.myandroidgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.res.Resources;
import android.graphics.Canvas;

/**
 * Created by zechocapic on 30/07/13.
 */
public class GameCar {
    //private static String TAG = GameCar.class.getSimpleName();

    public final static int MOVE_CAR_LEFT = -1;
    public final static int MOVE_CAR_RIGHT = 1;
    public final static int MOVE_CAR_NOT = 0;

    private int xPos;
    private int yPos;
    private int lateralSpeed;
    private Bitmap bitmap;

    public GameCar(Resources resources, int lateralSpeed) {
        this.xPos = GameSurfaceView.GAME_WIDTH / 2;
        this.yPos = 1000;
        this.lateralSpeed = lateralSpeed;
        this.bitmap = BitmapFactory.decodeResource(resources, R.drawable.gamecar);
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, xPos - (bitmap.getWidth() / 2), yPos -(bitmap.getHeight() / 2), null);
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
