package com.zechocapic.myandroidgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.util.Log;

/**
 * Created by zechocapic on 28/07/13.
 */
public class AndroidGameThread extends Thread {
    private static final String TAG = AndroidGameThread.class.getSimpleName();

    private final static int MAX_FPS = 30;
    private final static int MAX_FRAME_SKIPS = 5;
    private final static int FRAME_PERIOD = 1000 / MAX_FPS;

    private boolean running;
    private SurfaceHolder androidSurfaceHolder;
    private AndroidGameSurfaceView androidGameSurfaceView;
    private AndroidVoiture androidVoiture;
    private AndroidObstacle androidObstacle;

    public AndroidGameThread(SurfaceHolder surfaceHolder, AndroidGameSurfaceView androidGameSurfaceView) {
        super();
        this.androidSurfaceHolder = surfaceHolder;
        this.androidGameSurfaceView = androidGameSurfaceView;
        this.androidVoiture = new AndroidVoiture();
        this.androidObstacle = new AndroidObstacle(150);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        Canvas canvas;
        Log.d(TAG,"Starting game mega loop !");

        long beginTime;
        long timeDiff;
        int sleepTime = 0;
        int frameSkipped;

        while (running) {
            beginTime = System.currentTimeMillis();
            frameSkipped = 0;

            // update game
            androidObstacle.move();
            Log.d(TAG,"update done");

            // render game
            canvas = null;
            try {
                canvas = androidSurfaceHolder.lockCanvas();
                synchronized (androidSurfaceHolder) {
                    canvasDraw(canvas);
                }
            } finally {
                if (canvas != null) {
                    androidSurfaceHolder.unlockCanvasAndPost(canvas);
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
    }

    public void canvasDraw (Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        androidObstacle.draw(canvas);
        this.androidVoiture.draw(canvas);
    }

    public void manageEvents() {
        this.androidVoiture.move();
    }
}
