package com.a4m1g0.testapp.poc_multitask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    Paint paint;
    BallGroup ballGroup;

    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        initialize();
    }

    public GamePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void initialize() {
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        ballGroup = new BallGroup(4000, getWidth(), getHeight());
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

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        ballGroup.draw(canvas);
        invalidate();
    }
}
