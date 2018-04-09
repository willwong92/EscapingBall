package com.example.willwong.escapingball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by willwong on 11/27/2016.
 */

public class Ball extends GameObjs {

    public void move (PointF direction) {
        float x = location.x + direction.x;
        float y = location.y + direction.y;
        if (x > 0 && x < GameValues.SCREEN_WIDTH) {
            location.x = x;
        }
        if (y > 0 && y < GameValues.SCREEN_HEIGHT) {
            location.y = y;
        }
    }

    @Override
    public boolean collide(GameObjs objs) {
        return false;
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

        canvas.drawCircle(location.x, location.y, radius, paint);
    }
}
