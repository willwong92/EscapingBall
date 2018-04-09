package com.example.willwong.escapingball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by willwong on 11/28/2016.
 */

public class RealEnemy extends Enemy {
    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);

        canvas.drawCircle(location.x, location.y, radius, paint);
    }
}
