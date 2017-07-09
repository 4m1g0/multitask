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
    public BallGroup(int number) {
        balls = new ArrayList<Ball>(number);
        populateBalls(number);
    }

    void populateBalls(int number){
        Paint paint = new Paint();
        paint.setColor(Color.DKGRAY);

        for (int i = 0; i < number; i++) {
            balls.add(new Ball(0,0,50,paint));
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for (Ball ball : balls) {
            int randx = (int)(Math.random() * 10 - 5);
            int randy = (int)(Math.random() * 10 - 5);

            int x = ball.getX() + randx;
            if (x > canvas.getWidth() || x < 0)
                x = -x;

            int y = ball.getY() + randy;
            if (y > canvas.getHeight() || y < 0)
                y = -y;

            ball.setX(x);
            ball.setY(y);

            ball.draw(canvas);
        }
    }
}
