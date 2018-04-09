package com.example.willwong.escapingball;

import android.graphics.PointF;

/**
 * Created by willwong on 11/28/2016.
 */

public class EnemyFactory {

    public static Enemy getEnemy() {

        Enemy enemy = new RealEnemy();

        float pos1 = (float) Math.random();
        float pos2 = (float) Math.random();
        float pos3 = (float) Math.random();
        PointF point = new PointF();
        if (pos1 > 0.5) {
            point.x = GameValues.SCREEN_WIDTH * pos3;
            point.y = (pos2 > 0.5)? 0 : GameValues.SCREEN_HEIGHT;
        }
        else {
            point.y = GameValues.SCREEN_HEIGHT * pos3;
            point.x = (pos2 > 0.5) ? 0 : GameValues.SCREEN_WIDTH;
        }
        enemy.setLocation(point);
        return enemy;
    }
}
