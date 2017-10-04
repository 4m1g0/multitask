package com.hewid.alpheus.Controller;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;

import com.hewid.alpheus.Model.Game.HardwareManager;
import com.hewid.alpheus.Model.Game.InteractionEvent;
import com.hewid.alpheus.Model.Game.WorldManager;
import com.hewid.alpheus.R;
import com.hewid.alpheus.View.GameView;

public class GameActivity extends AppCompatActivity implements SensorEventListener {
    private Pacemaker pacemaker;
    private WorldManager worldManager;
    private GameView view;

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        view = (GameView) findViewById(R.id.game_view);


        HardwareManager hardwareManager = new HardwareManager((Vibrator) getSystemService(VIBRATOR_SERVICE));
        worldManager = new WorldManager(hardwareManager);
        pacemaker = new Pacemaker(worldManager);
        view.attachWorld(worldManager);
        view.register(pacemaker);

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                worldManager.start(view.getWidth(), view.getHeight());
                pacemaker.start();
            }
        });

        senSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        pacemaker.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        pacemaker.resume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        pacemaker.pause();
        senSensorManager.unregisterListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        pacemaker.kill();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            worldManager.handleInteractionEvent(new InteractionEvent(InteractionEvent.ACCELEROMETER, event));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
