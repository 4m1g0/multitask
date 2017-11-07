package com.hewid.alpheus.Game.BubbleWorld;

import android.graphics.Canvas;

import com.hewid.alpheus.GameEngine.GameObject;
import com.hewid.alpheus.GameEngine.InteractionEvent;
import com.hewid.alpheus.GameEngine.Sprite;


public class Bubble extends GameObject {
    private Sprite sprite;
    private int posX, posY;

    @Override
    public void start(int width, int height) {
        super.start(width, height);

        sprite = new Sprite("bubble.png", 100, 100, 7);
    }

    @Override
    public boolean handleInteractionEvent(InteractionEvent event) {
        return true;
    }

    @Override
    public void update(long time) {
        sprite.update(time);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(sprite.getBitmap(), posX, posY, null);
    }
}
