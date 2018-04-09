package com.example.willwong.escapingball;

import android.graphics.Canvas;
import android.graphics.PointF;

/**
 * Created by willwong on 11/27/2016.
 */

public abstract class GameObjs {

    protected PointF location;

    protected float radius = GameValues.RADIUS;

    public abstract  void onDraw(Canvas canvas);




    public abstract boolean collide(GameObjs objs);

    public float getRadius() {return radius;}

    public void setRadius (float radius) {this.radius = radius;}

    public PointF getLocation() {return  location;}

    public void setLocation(PointF location) {this.location = location;}


}
