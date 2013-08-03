package com.zechocapic.myandroidgame;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainGame extends Activity {
    private static final String TAG = MainGame.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Maingame activity onCreate");

        setContentView(R.layout.activity_main_game);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Maingame activity onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Maingame activity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Maingame activity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Maingame activity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Maingame activity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Maingame activity onDestroy");
    }

    public void onClickPlay(View v) {
        Toast.makeText(this, "You clicked on Play !", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }

    public void onClickOptions(View v) {
        Toast.makeText(this, "You clicked on Options !", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Options.class);
        startActivity(intent);
    }

    public void onClickRules(View v) {
        Toast.makeText(this, "You clicked on Rules !", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Rules.class);
        startActivity(intent);
    }

    public void onClickCredits(View v) {
        Toast.makeText(this, "You clicked on Credits !", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Credits.class);
        startActivity(intent);
    }
}
