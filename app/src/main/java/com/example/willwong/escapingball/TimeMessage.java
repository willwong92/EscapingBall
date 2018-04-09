package com.example.willwong.escapingball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by willwong on 11/27/2016.
 */

public class TimeMessage {

    private PointF location;

    private int time;

    public void drawMe(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(40);
        paint.setColor(Color.BLACK);

        canvas.drawText("Time : " + time, location.x, location.y, paint);
    }

    public PointF getLocation() {return location;}

    public void setLocation(PointF location) {this.location = location;}

    public int getTime() {return time;}

    public void setTime(int time) {this.time = time;}
}
