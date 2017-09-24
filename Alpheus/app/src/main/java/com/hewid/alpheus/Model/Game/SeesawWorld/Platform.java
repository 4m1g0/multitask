package com.hewid.alpheus.Model.Game.SeesawWorld;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.hewid.alpheus.Model.Game.GameObject;
import com.hewid.alpheus.Model.Game.InteractionEvent;

public class Platform extends GameObject {
    private int platformHeight, platformWidth;
    private Rect platform;
    private Paint paint;

    public Platform(int width, int height) {
        platformHeight = 30;
        platformWidth = width / 2;
        paint = new Paint();
        paint.setColor(Color.rgb(50,50,50));
        int startPoint = (width - platformWidth)/2;
        platform = new Rect(startPoint, height-platformHeight, startPoint+platformWidth, height);
    }

    public int getHeight() {
        return platformHeight;
    }

    public int getWidth() {
        return platformWidth;
    }

    @Override
    public boolean handleInteractionEvent(InteractionEvent event) {
        return true;
    }

    @Override
    public void update(long time) { }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(platform, paint);
    }
}
