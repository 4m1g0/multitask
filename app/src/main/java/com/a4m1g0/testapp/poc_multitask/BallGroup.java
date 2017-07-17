package com.a4m1g0.testapp.poc_multitask;

import android.graphics.Bitmap;
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
    float hspace, vspace;
    int N;
    int initialPositionY = 0;
    Bitmap cachedBitmap;

    public BallGroup(int number, int width, int height) {
        this.height = height;
        this.width = width;
        balls = new ArrayList<Ball>(number);
        populateBalls(number, width, height);

        hspace = (float)((double)width / (double)number);
        vspace = (float)((double)height / (double)number);
        N = number;

        cachedBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(cachedBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.rgb(12,200,170));
        c.drawCircle(50, 50, 50, paint);
    }

    void populateBalls(int number, int width, int height){
        this.height = height;
        this.width = width;
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

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


        initialPositionY = ++initialPositionY % height;
        canvas.translate(0, initialPositionY);
        float currentPositionY = initialPositionY;

        for (int i = 0; i < N; i++) {
            currentPositionY += vspace;
            if (currentPositionY > height) {
                canvas.translate(0, -currentPositionY);
                currentPositionY = 0;
            }
            //Log.d(TAG, "" + canvas.getMatrix().toString());
            canvas.translate(hspace, vspace);
            canvas.drawBitmap(cachedBitmap, 0, 0, null);
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
