package com.zechocapic.myandroidgame;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.util.Log;

/**
 * Created by zechocapic on 28/07/13.
 */
public class AndroidGameThread extends Thread {
    private static final String TAG = AndroidGameThread.class.getSimpleName();

    private boolean running;
    private SurfaceHolder androidSurfaceHolder;
    private AndroidGameSurfaceView androidGameSurfaceView;
    private Paint rectPaint;

    public AndroidGameThread(SurfaceHolder surfaceHolder, AndroidGameSurfaceView androidGameSurfaceView) {
        super();
        this.androidSurfaceHolder = surfaceHolder;
        this.androidGameSurfaceView = androidGameSurfaceView;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        Canvas canvas;
        Log.d(TAG,"Starting game mega loop !");
        while (running) {
            // update game
            // render game
            canvas = null;
            try {
                canvas = androidSurfaceHolder.lockCanvas();
                synchronized (androidSurfaceHolder) {
                    canvas.drawColor(Color.BLACK);
                    canvas.drawBitmap(BitmapFactory.decodeResource(androidGameSurfaceView.getResources(), R.drawable.ic_launcher), (androidGameSurfaceView.getWidth()/2)-64, 800, null);
                }
            } finally {
                if (canvas != null) {
                    androidSurfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
