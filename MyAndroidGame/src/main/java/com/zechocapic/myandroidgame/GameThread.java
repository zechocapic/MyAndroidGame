package com.zechocapic.myandroidgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.util.Log;

/**
 * Created by zechocapic on 28/07/13.
 */
public class GameThread extends Thread {
    private static final String TAG = GameThread.class.getSimpleName();

    private final static int MAX_FPS = 30;
    private final static int MAX_FRAME_SKIPS = 5;
    private final static int FRAME_PERIOD = 1000 / MAX_FPS;

    private final static int GAME_OK = 1;
    private final static int GAME_OVER = -1;

    private boolean running;
    private SurfaceHolder surfaceHolder;
    private GameSurfaceView gameSurfaceView;
    private AndroidCar androidCar;
    private AndroidObstacle androidObstacle;

    public GameThread(SurfaceHolder surfaceHolder, GameSurfaceView gameSurfaceView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameSurfaceView = gameSurfaceView;
        this.androidCar = new AndroidCar(5);
        this.androidObstacle = new AndroidObstacle(150, 10);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        Canvas canvas;

        int gameState = GAME_OK;

        long beginTime;
        long timeDiff;
        int sleepTime;
        int frameSkipped;

        Log.d(TAG,"Starting game mega loop !");

        while (running) {
            beginTime = System.currentTimeMillis();
            frameSkipped = 0;

            // update game
            if (gameState == GAME_OK) {
                androidObstacle.move();
                if (detectCollision()) {
                    gameState = GAME_OVER;
                }
                Log.d(TAG,"update done, game ok");
            } else {
                Log.d(TAG, "update done, game over");
            }

            // render game
            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    canvasDraw(canvas, gameState);
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            // sleep until next frame
            timeDiff = System.currentTimeMillis() - beginTime;
            sleepTime = (int)(FRAME_PERIOD - timeDiff);
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime);
                }
                catch (InterruptedException e) {
                }
            }

            // only update if late
            while (sleepTime < 0 && frameSkipped < MAX_FRAME_SKIPS) {
                androidObstacle.move();
                Log.d(TAG,"frames skipped = " + frameSkipped);

                sleepTime += FRAME_PERIOD;
                frameSkipped++;
            }
        }
        Log.d(TAG, "game loop ended !");
    }

    public void canvasDraw (Canvas canvas, int gameState) {
        if (gameState == GAME_OK) {
            canvas.drawColor(Color.BLACK);
            androidObstacle.draw(canvas);
            androidCar.draw(canvas);
        } else {
            canvas.drawColor(Color.RED);
        }
    }

    public void manageEvents(int event) {
        androidCar.move(event);
    }

    public boolean detectCollision() {
        return  (androidCar.getxPos() == androidObstacle.getxPos() && androidCar.getyPos() == androidObstacle.getyPos());
    }
}
