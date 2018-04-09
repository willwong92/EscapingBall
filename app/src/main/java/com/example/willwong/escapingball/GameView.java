package com.example.willwong.escapingball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

/**
 * Created by willwong on 11/27/2016.
 */

public class GameView extends View {

    private Controller controller;

    private Runnable updateThread;

    private Handler handler;


    public GameView(Context context) {
        super(context);

        handler = new Handler();
        updateThread = new Runnable() {

            public void run() {
                postInvalidate();
                handler.removeCallbacks(updateThread);
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
/*
        Paint mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        Canvas mCanvas = new Canvas();
        mCanvas.drawText("Time : " + controller.getGameTime(), 100, 200, mPaint);
*/
        controller.initDraw(canvas);
        super.onDraw(canvas);
    }

    public Controller getController() {return controller;}

    public void setController(Controller controller) {this.controller = controller;}

    public void reDraw() {handler.post(updateThread);}
}
