package com.hewid.alpheus.Game.BubbleWorld;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.hewid.alpheus.GameEngine.GameEvent;
import com.hewid.alpheus.GameEngine.GameEventHandler;
import com.hewid.alpheus.GameEngine.GameObject;
import com.hewid.alpheus.GameEngine.InteractionEvent;
import com.hewid.alpheus.GameEngine.Sprite;


public class Bubble extends GameObject {

    private final int BUBBLE_HEIGHT = 150;
    private final int BUBBLE_WIDTH = 150;
    private Sprite sprite;
    private int posX, posY;

    public Bubble(GameEventHandler gameEventHandler) {
        super(gameEventHandler);
    }

    @Override
    public void start(int width, int height) {
        super.start(width, height);

        sprite = new Sprite("bubble.png", BUBBLE_WIDTH, BUBBLE_HEIGHT, 2, 2, -1);

        posX = (int) (Math.random() * (width - BUBBLE_WIDTH));
        posY = (int) (Math.random() * (height - BUBBLE_HEIGHT));
    }

    @Override
    public boolean handleInteractionEvent(InteractionEvent event) {
        if (event.getAction() == InteractionEvent.TOUCH) {
            MotionEvent motionEvent = (MotionEvent) event.getPayload();

            int[] bubbleCenter = {posX + BUBBLE_WIDTH/2, posY + BUBBLE_HEIGHT/2};
            double distance = Math.sqrt(Math.pow(bubbleCenter[0] - motionEvent.getX(), 2) + Math.pow(bubbleCenter[1] - motionEvent.getY(), 2));

            if (distance <= BUBBLE_HEIGHT/2){
                gameEventHandler.handleGameEvent(new GameEvent(GameEvent.POSITIVE_REINFORCEMENT));
                sprite = new Sprite("bubble.png", BUBBLE_WIDTH, BUBBLE_HEIGHT, 2, 2, 1);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean update(long time) {
        return sprite.update(time);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(sprite.getBitmap(), posX, posY, null);
    }
}
