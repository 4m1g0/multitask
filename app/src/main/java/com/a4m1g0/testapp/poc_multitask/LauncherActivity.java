package com.a4m1g0.testapp.poc_multitask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    }

    public void HWACanvasClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction(MainActivity.HWA_CANVAS);
        startActivity(intent);
    }

    public void SurfaceViewCanvasClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction(MainActivity.SURFACE_VIEW_CANVAS);
        startActivity(intent);
    }
}
