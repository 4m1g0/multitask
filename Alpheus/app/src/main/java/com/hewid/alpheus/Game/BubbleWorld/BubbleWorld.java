package com.hewid.alpheus.Game.BubbleWorld;

import android.graphics.Canvas;

import com.hewid.alpheus.GameEngine.GameEvent;
import com.hewid.alpheus.GameEngine.GameEventHandler;
import com.hewid.alpheus.GameEngine.InteractionEvent;
import com.hewid.alpheus.GameEngine.World;

import java.util.LinkedList;
import java.util.List;

public class BubbleWorld extends World {

    List<Bubble> bubbleList = new LinkedList<>();

    public BubbleWorld(GameEventHandler gameEventHandler) {
        super(gameEventHandler);

        bubbleList.add(new Bubble());
    }

    @Override
    public void start(int width, int height) {
        super.start(width, height);

        for (Bubble bubble : bubbleList) {
            bubble.start(width, height);
        }
    }

    @Override
    public void handleGameEvent(GameEvent event) {

    }

    @Override
    public boolean handleInteractionEvent(InteractionEvent event) {
        return false;
    }

    @Override
    public void update(long time) {
        for (Bubble bubble : bubbleList) {
            bubble.update(time);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for (Bubble bubble : bubbleList) {
            bubble.draw(canvas);
        }
    }
}
