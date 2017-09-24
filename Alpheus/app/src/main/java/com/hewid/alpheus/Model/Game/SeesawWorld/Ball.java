package com.hewid.alpheus.Model.Game.SeesawWorld;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.SensorEvent;

import com.hewid.alpheus.Model.Game.GameEventHandler;
import com.hewid.alpheus.Model.Game.GameObject;
import com.hewid.alpheus.Model.Game.InteractionEvent;

public class Ball extends GameObject{
    private Bitmap catchedBitmap;
    private Platform platform;
    private int height;
    private final int ballDiameter = 100;
    private int position;
    private float acceleration;
    private float speed;

    public Ball(GameEventHandler gameEventHandler, Platform platform, int height, int width) {
        super(gameEventHandler);

        catchedBitmap = Bitmap.createBitmap(100,100,Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(catchedBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.rgb(200,10,10));
        c.drawCircle(50,50,50,paint);
        this.platform = platform;
        this.height = height;
        this.position = width/2-ballDiameter/2;
    }

    @Override
    public boolean handleInteractionEvent(InteractionEvent event) {
        if (event.getAction() == InteractionEvent.ACCELEROMETER) {
            SensorEvent acc = (SensorEvent) event.getPayload();

            acceleration = acc.values[1];
        }
        return false;
    }

    @Override
    public void update(long time) {
        speed += (acceleration/100) * ((double)time / 10000);
        position += speed * ((double)time /10000);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(catchedBitmap, position, height-platform.getHeight()-ballDiameter, null);
    }
}
