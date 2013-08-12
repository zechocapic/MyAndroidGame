package com.zechocapic.myandroidgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by zechocapic on 11/08/13.
 */
public class GameEngine {
    private final static int GAME_OK = 1;
    private final static int GAME_OVER = -1;

    public float screenWidth;
    public float screenHeight;

    private Context context;
    private Resources resources;

    private int gameState = GAME_OK;
    private int carMovement = 0;

    private GameCar gameCar;
    private GameObstacle[] gameObstacles;
    private GameScenery gameScenery;
    private GameOver gameOver;
    private GameScore gameScore;

    public void Init(Context context) {
        this.context = context;
        this.resources = context.getResources();

        this.gameCar = new GameCar(resources, 5);
        this.gameObstacles = new GameObstacle[5];
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            gameObstacles[i] = new GameObstacle(resources, 184 + ( i * 100), 1 + random.nextInt(10));
        }
        this.gameScenery = new GameScenery(resources, 20);
        this.gameOver = new GameOver();
        this.gameScore = new GameScore();
    }

    public void Update() {
        if (gameState == GAME_OK) {
            gameCar.move(carMovement);
            gameScenery.move();
            for (int i = 0; i < 5; i++) {
                gameObstacles[i].move();
                if (detectCollision()) {
                    gameState = GAME_OVER;
                }
            }
        }

    }

    public void Draw(Canvas canvas) {
        if (gameState == GAME_OK) {
            gameScenery.draw(canvas);
            gameCar.draw(canvas);
            for (int i = 0; i < 5; i++) {
                gameObstacles[i].draw(canvas);
            }
            gameScore.draw(canvas,GameObstacle.getNbObstaclesAvoided());
        } else {
            gameOver.draw(canvas);
        }

    }

    public boolean detectCollision() {
        for (int i = 0; i < 5; i++) {
            if (Math.abs(gameObstacles[i].getxPos() - gameCar.getxPos()) < 64 && Math.abs(gameCar.getyPos() - gameObstacles[i].getyPos()) < 96) {
                return true;
            }
        }
        return false;
    }

    public void manageEvents(int event) {
        this.carMovement = event;
    }

}
