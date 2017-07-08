package com.a4m1g0.testapp.poc_multitask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        while (true) {
            try {
                thread.setNotRunning();
                thread.join();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(23,23,100, paint);
    }
}
