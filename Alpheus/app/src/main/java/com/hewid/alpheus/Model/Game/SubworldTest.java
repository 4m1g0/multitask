package com.hewid.alpheus.Model.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

class SubworldTest extends World {
    private Bitmap catchedBitmap;
    private int[] position = new int[2];
    private float[] speed = new float[]{0.5f, 0.5f};

    public SubworldTest(int height, int width) {
        super(height, width);

        catchedBitmap = Bitmap.createBitmap(100,100,Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(catchedBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.rgb(12,200,170));
        c.drawCircle(50,50,50,paint);
    }

    @Override
    public void update(long time) {
        position[0] = (int)((speed[0] * time) % (width - 100));
        position[1] = (int)((speed[1] * time) % (height - 100));
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(catchedBitmap, position[0], position[1], null);
    }

    @Override
    public void touch(MotionEvent event) {

    }
}
