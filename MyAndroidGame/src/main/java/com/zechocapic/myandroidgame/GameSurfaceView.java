package com.zechocapic.myandroidgame;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;
import android.util.Log;


/**
 * Created by zechocapic on 28/07/13.
 */
public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = GameSurfaceView.class.getSimpleName();

    private GameThread gameThread;

    public GameSurfaceView(Context context) {
        super(context);

        // getting the holder and adding callback to him
        getHolder().addCallback(this);

        // create the game loop thread
        gameThread = new GameThread(getHolder(), this);
        Log.d(TAG, "Surface instantiated and thread created");

        // make this SurfaceView focusable
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // launch the android game thread
        gameThread.setRunning(true);
        gameThread.start();
        Log.d(TAG, "Surface created and thread started");
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        gameThread.setRunning(false);
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d(TAG, "x = " + event.getX() + " ; y = " + event.getY());
            if (event.getX() < 384) {
                gameThread.manageEvents(AndroidCar.MOVE_CAR_LEFT);
            } else {
                gameThread.manageEvents(AndroidCar.MOVE_CAR_RIGHT);
            }
        }
        return super.onTouchEvent(event);
    }
}
