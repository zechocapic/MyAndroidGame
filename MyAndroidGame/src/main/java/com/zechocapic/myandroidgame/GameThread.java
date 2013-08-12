package com.zechocapic.myandroidgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by zechocapic on 28/07/13.
 */
public class GameThread extends Thread {
    private static final String TAG = GameThread.class.getSimpleName();

    private final static int MAX_FPS = 30;
    private final static int MAX_FRAME_SKIPS = 5;
    private final static int FRAME_PERIOD = 1000 / MAX_FPS;

    private long beginTime;
    private long timeDiff;
    private int sleepTime;
    private int frameSkipped;
    private boolean running;

    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private GameSurfaceView gameSurfaceView;
    private GameEngine gameEngine;

    public GameThread(SurfaceHolder surfaceHolder, GameSurfaceView gameSurfaceView, GameEngine gameEngine) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameSurfaceView = gameSurfaceView;
        this.gameEngine = gameEngine;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {

        while (running) {
            beginTime = System.currentTimeMillis();
            frameSkipped = 0;

            // update game
            gameEngine.Update();

            // render game
            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    if ( canvas != null) {
                        gameEngine.Draw(canvas);
                    }
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

            // if late only update
            while (sleepTime < 0 && frameSkipped < MAX_FRAME_SKIPS) {
                for (int i = 0; i < 5; i++) {
                    gameEngine.Update();

                    sleepTime += FRAME_PERIOD;
                    frameSkipped++;
                }
            }
        }
    }

}
