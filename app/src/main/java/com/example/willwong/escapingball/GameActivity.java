package com.example.willwong.escapingball;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newGame:

                controller.newGame();
                break;
            case R.id.startOrPause:
                if (controller.isAlive()){
                    controller.pauseGame();
                } else {
                    controller.startGame();
                }
            default:
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }



    private Controller controller;
    private GameView gameView;
    private SensorManager sensorManager = null;
    private Sensor sensor = null;
    private PowerManager.WakeLock wakeLock;
    private WindowManager windowManager;
    private Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
        controller = new Controller();
        gameView = new GameView(this);
        controller.setGameView(gameView);
        gameView.setController(controller);
        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(controller, sensor, SensorManager.SENSOR_DELAY_GAME);
        controller.newGame();
        setContentView(gameView);
        //setContentView(R.layout.activity_main);
    }

    private void initActivity() {
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();

        // Get an instance of the PowerManager
        PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);


    }

    @Override
    protected  void onPause() {
        super.onPause();
        sensorManager.unregisterListener(controller);


    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(controller, sensor, SensorManager.SENSOR_DELAY_GAME);


    }

}






































