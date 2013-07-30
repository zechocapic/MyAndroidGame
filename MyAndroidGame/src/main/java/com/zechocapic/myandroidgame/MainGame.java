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
