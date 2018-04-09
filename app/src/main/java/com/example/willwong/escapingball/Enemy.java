package com.example.willwong.escapingball;

import android.graphics.PointF;

/**
 * Created by willwong on 11/27/2016.
 */

public abstract class Enemy extends GameObjs {

    protected PointF direction;

    public void chasingBall(GameObjs objs) {
        if (this.direction == null) {
            this.direction = new PointF();
        }
        float distX = objs.location.x - this.location.x;
        float distY = objs.location.y - this.location.y;

        this.direction.x =
                (float) (distX / Math.sqrt(distX*distX+distY*distY)*GameValues.ENEMY_SPEED);
        this.direction.y =
                (float) (distY / Math.sqrt(distX*distX+distY*distY)*GameValues.ENEMY_SPEED);
    }


    public void move() throws MoveException {

        float x = location.x + direction.x;
        float y = location.y + direction.y;
        if ((x < 0 || x > GameValues.SCREEN_WIDTH) || (y < 0 || y > GameValues.SCREEN_HEIGHT)) {
            throw new MoveException();
        }
        location.x = x;
        location.y = y;
    }

    @Override
    public boolean collide(GameObjs objs) {
        float x = objs.location.x - this.location.x;
        float y = objs.location.y - this.location.y;
        float dist = (float) Math.sqrt(x * x + y * y);
        return  dist < objs.radius + this.radius;
    }
}
