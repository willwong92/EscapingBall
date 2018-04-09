package com.example.willwong.escapingball;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by willwong on 11/27/2016.
 */

public class Controller implements Runnable, SensorEventListener {

    private Ball ball;

    private List<Enemy> enemyList;

    private GameView gameView;

    private Handler handler;

    private TimeMessage timeMessage;

    public Controller() {handler = new Handler();}



    private float gameTime = 0;

    private boolean alive = false;


    public void initDraw(Canvas canvas) {
        ball.onDraw(canvas);

        for (Enemy enemy : enemyList) {
            enemy.onDraw(canvas);
        }

        timeMessage.drawMe(canvas);
    }

    public Ball getBall() {return ball;}
    public void setBall(Ball ball) {this.ball = ball;}
    public List<Enemy> getEnemyList() {return enemyList;}
    public void setEnemyList(List<Enemy> enemyList) {this.enemyList = enemyList;}
    public GameView getGameView() {return gameView;}
    public void setGameView(GameView gameView) {this.gameView = gameView;}
    public float getGameTime() {return gameTime ;}




    public void run() {
        handler.postDelayed(this, GameValues.DELAY_TIME);

        gameTime += GameValues.DELAY_TIME / 1000.0;
        timeMessage.setTime((int) gameTime);

        List<Enemy> removedEnemy = null;

        for (Enemy enemy : enemyList) {
            try {
                enemy.move();
            } catch (MoveException e) {
                if (removedEnemy == null) {
                    removedEnemy = new ArrayList<Enemy>();
                }
                removedEnemy.add(enemy);
            }
        }

        if (removedEnemy != null) {
            enemyList.removeAll(removedEnemy);
        }

        if (enemyList.size() < GameValues.GAME_LEVEL[((int) (gameTime / 10))
                % GameValues.GAME_LEVEL.length]) {
            int tmpCount = GameValues.GAME_LEVEL[((int) (gameTime / 10))
                    % GameValues.GAME_LEVEL.length] - enemyList.size();

            for (int i = 0; i < tmpCount; i++) {
                Enemy enemy = EnemyFactory.getEnemy();
                enemy.chasingBall(ball);
                enemyList.add(enemy);
            }
        }

        gameView.reDraw();

        checkCollide();

    }

    private void checkCollide() {
        for (Enemy enemy : enemyList) {
            if (enemy.collide(ball)) {
                if (enemy instanceof RealEnemy) {
                    pauseGame();
                }
            }
        }
    }

    public void newGame() {
        ball = new Ball();
        ball.setLocation(new PointF(120, 150));


        enemyList = new ArrayList<Enemy>();
        Enemy enemy = EnemyFactory.getEnemy();
        enemy.chasingBall(ball);
        enemyList.add(enemy);

        gameTime = 0;

        timeMessage = new TimeMessage();
        timeMessage.setLocation(new PointF(10, 40));
        timeMessage.setTime((int) gameTime);

        startGame();
    }

    public void startGame() {
        handler.removeCallbacks(this);
        handler.post(this);
        alive = true;
    }

    public void pauseGame() {
        handler.removeCallbacks(this);
        alive = false;
    }

    public void onAccuracyChanged(Sensor arg0, int arg1) {}

    public void onSensorChanged(SensorEvent arg0) {
        if (alive) {
            float x = -arg0.values[0];
            float y = arg0.values[1];
            float z = arg0.values[2];



            if (z < -8) {
                pauseGame();
            }

            PointF direction = new PointF(x, y);
            ball.move(direction);
        }
    }

    public boolean isAlive() {return alive;}

}


































