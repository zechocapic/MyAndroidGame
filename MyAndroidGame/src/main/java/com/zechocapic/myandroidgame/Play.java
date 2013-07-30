package com.zechocapic.myandroidgame;

import android.os.Bundle;
import android.app.Activity;

public class Play extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameSurfaceView(this));
    }

}
