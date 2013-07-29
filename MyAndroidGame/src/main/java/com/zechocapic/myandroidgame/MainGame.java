package com.zechocapic.myandroidgame;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainGame extends Activity {
    private static final String TAG = MainGame.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Maingame activity onCreate");

        setContentView(new AndroidGameSurfaceView(this));
        Log.d(TAG, "SurfaceView inserted");
    }

}
