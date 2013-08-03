package com.zechocapic.myandroidgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.util.Log;

import java.util.Random;

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
    private GameCar gameCar;
    private GameObstacle[] gameObstacles;
    private GameScenery gameScenery;
    private GameOver gameOver;

    public GameThread(SurfaceHolder surfaceHolder, GameSurfaceView gameSurfaceView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameSurfaceView = gameSurfaceView;
        this.gameCar = new GameCar(5);
        this.gameObstacles = new GameObstacle[5];
        Random random = new Random();
        for (int i=0; i<5; i++) {
            gameObstacles[i] = new GameObstacle(184 + ( i * 100), 1 + random.nextInt(10));
        }
        this.gameScenery = new GameScenery(10);
        this.gameOver = new GameOver();
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
                gameScenery.move();
                for (int i = 0; i < 5; i++) {
                    gameObstacles[i].move();
                    if (detectCollision()) {
                        gameState = GAME_OVER;
                        Log.d(TAG,"update done, game over");
                    } else {
                        Log.d(TAG, "update done, game ok");
                    }
                }
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
                for (int i = 0; i < 5; i++) {
                    gameObstacles[i].move();
                    Log.d(TAG,"frames skipped = " + frameSkipped);

                    sleepTime += FRAME_PERIOD;
                    frameSkipped++;

                }
            }
        }
        Log.d(TAG, "game loop ended !");
    }

    public void canvasDraw (Canvas canvas, int gameState) {
        if (gameState == GAME_OK) {
            //canvas.drawColor(Color.BLACK);
            gameScenery.draw(canvas);
            gameCar.draw(canvas);
            for (int i = 0; i < 5; i++) {
                gameObstacles[i].draw(canvas);
            }
        } else {
            canvas.drawColor(Color.RED);
            gameOver.draw(canvas);
        }
    }

    public void manageEvents(int event) {
        gameCar.move(event);
    }

    public boolean detectCollision() {
        for (int i = 0; i < 5; i++) {
            /*if (gameCar.getxPos() == gameObstacles[i].getxPos() && gameCar.getyPos() == gameObstacles[i].getyPos()) {
                return true;
            }*/
            if (Math.abs(gameObstacles[i].getxPos() - gameCar.getxPos()) < 64 && Math.abs(gameCar.getyPos() - gameObstacles[i].getyPos()) < 96) {
                return true;
            }
        }
        return false;
    }
}
