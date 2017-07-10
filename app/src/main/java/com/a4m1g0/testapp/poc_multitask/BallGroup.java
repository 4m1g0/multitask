package com.a4m1g0.testapp.poc_multitask;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 4m1g0 on 9/07/17.
 */

public class BallGroup implements GameObject {
    List<Ball> balls;
    public BallGroup(int number, int width, int height) {
        balls = new ArrayList<Ball>(number);
        populateBalls(number, width, height);
    }

    void populateBalls(int number, int width, int height){
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
        for (Ball ball : balls) {
            //int randx = (int)(Math.random() * 2);
            //int randx = (int)(Math.random() * 2);


            int x = ball.getY() + 1;

            x %= canvas.getHeight();


            ball.setY(x);

            ball.draw(canvas);
        }
    }
}
