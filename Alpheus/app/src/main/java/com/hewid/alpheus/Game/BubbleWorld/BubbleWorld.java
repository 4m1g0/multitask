package com.hewid.alpheus.Game.BubbleWorld;

import android.graphics.Canvas;

import com.hewid.alpheus.GameEngine.GameEvent;
import com.hewid.alpheus.GameEngine.GameEventHandler;
import com.hewid.alpheus.GameEngine.InteractionEvent;
import com.hewid.alpheus.GameEngine.World;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class BubbleWorld extends World {

    private List<Bubble> bubbleList = new LinkedList<>();

    public BubbleWorld(GameEventHandler gameEventHandler) {
        super(gameEventHandler);

        for (int i = 0; i < 3; i++)
            bubbleList.add(new Bubble(this));
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
        gameEventHandler.handleGameEvent(event);
    }

    @Override
    public boolean handleInteractionEvent(InteractionEvent event) {
        if (event.getAction() == InteractionEvent.TOUCH){
            // This list has to be iterated in reverse order to interact first with the bubbles drawn last
            for (int i = bubbleList.size() - 1; i >= 0; i--) {
                Bubble bubble = bubbleList.get(i);
                if (bubble.handleInteractionEvent(event))
                    return true;
            }
        }

        return false;
    }

    @Override
    public boolean update(long time) {
        ListIterator<Bubble> i = bubbleList.listIterator();

        while (i.hasNext()) {
            Bubble bubble = i.next();
            if (!bubble.update(time)) {
                Bubble newBubble = new Bubble(this);
                newBubble.start(width, height);
                synchronized (this) {
                    i.set(newBubble);
                }
            }
        }

        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        synchronized (this) {
            for (Bubble bubble : bubbleList) {
                bubble.draw(canvas);
            }
        }
    }
}
