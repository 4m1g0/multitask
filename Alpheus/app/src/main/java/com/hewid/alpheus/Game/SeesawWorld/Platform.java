package com.hewid.alpheus.Game.SeesawWorld;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.hewid.alpheus.GameEngine.AssetHandler;
import com.hewid.alpheus.GameEngine.GameObject;
import com.hewid.alpheus.GameEngine.InteractionEvent;

public class Platform extends GameObject {
    private int platformHeight, platformWidth;
    private int platformStartPoint;
    private Bitmap platformBitmap;

    @Override
    public void start(int width, int height) {
        super.start(width, height);

        platformBitmap = AssetHandler.getBitmap("platform.png");
        platformHeight = platformBitmap.getHeight();
        platformWidth = platformBitmap.getWidth();
        platformStartPoint = (width - platformWidth) / 2;
    }

    public int getHeight() {
        return platformHeight - 10;
    }

    public int getWidth() {
        return platformWidth;
    }

    @Override
    public boolean handleInteractionEvent(InteractionEvent event) {
        return true;
    }

    @Override
    public boolean update(long time) {
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(platformBitmap, platformStartPoint, height - platformHeight, null);
    }
}
