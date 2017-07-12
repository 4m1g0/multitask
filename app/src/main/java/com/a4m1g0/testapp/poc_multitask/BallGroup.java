package com.a4m1g0.testapp.poc_multitask;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 4m1g0 on 9/07/17.
 */

public class BallGroup implements GameObject {
    private static final String TAG = "BallGroup";
    List<Ball> balls;
    private long lastTime;
    private int frames;
    private int FRAME_WINDOW = 30;
    private int height, width;

    public BallGroup(int number, int width, int height) {
        this.height = height;
        this.width = width;
        balls = new ArrayList<Ball>(number);
        populateBalls(number, width, height);
    }

    void populateBalls(int number, int width, int height){
        this.height = height;
        this.width = width;
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        double hspace = (double)width / (double)number;
        double vspace = (double)height / (double)number;

        for (int i = 0; i < number; i++) {
            balls.add(new Ball((int)(hspace*(double)i),(int)(vspace*(double)i),50,paint));
        }
    }

    @Override
    public void draw(Canvas canvas) {
        frames++;
        if (frames >= FRAME_WINDOW){
            frames = 0;
            printFPS();
        }

        Paint p = new Paint();
        p.setColor(Color.BLUE);
        canvas.drawCircle(0,0, 50, p);

        Paint p2 = new Paint();
        p2.setColor(Color.MAGENTA);
        canvas.drawCircle(width, height, 50, p2);

        for (Ball ball : balls) {
            //int randx = (int)(Math.random() * 2);
            //int randx = (int)(Math.random() * 2);


            int y = ball.getY() + 1;
            y %= height;

            ball.setY(y);
            ball.draw(canvas);
        }
    }

    private void printFPS(){
        long execTime = System.nanoTime();
        long elapsed = execTime - lastTime;
        lastTime = execTime;

        double fps = (double)1/(((double)elapsed/(double)FRAME_WINDOW)/(double)1000000000);

        Log.d(TAG, String.valueOf(fps));
    }
}
