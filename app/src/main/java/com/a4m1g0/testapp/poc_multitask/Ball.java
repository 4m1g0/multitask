package com.a4m1g0.testapp.poc_multitask;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


public class Ball implements GameObject {

    private int x, y;
    private int radius;

    public Ball(int x, int y, int radius, Paint paint) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.paint = paint;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getY() {
        return y;
    }

    private Paint paint;

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }
}
