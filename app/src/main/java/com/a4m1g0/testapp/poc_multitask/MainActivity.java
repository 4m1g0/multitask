package com.a4m1g0.testapp.poc_multitask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String HWA_CANVAS = "com.a4m1g0.testapp.poc_multitask.HWACanvas";
    public static final String SURFACE_VIEW_CANVAS = "com.a4m1g0.testapp.poc_multitask.SurfaceViewCanvas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        switch (intent.getAction()) {
            case HWA_CANVAS:
                setContentView(R.layout.activity_main);
                break;
            case SURFACE_VIEW_CANVAS:
                setContentView(new CustomSurfaceViewGamePanel(this));
                break;
        }
    }
}
