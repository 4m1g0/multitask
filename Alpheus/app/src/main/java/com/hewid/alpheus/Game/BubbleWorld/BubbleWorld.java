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
        boolean handled = false;

        if (event.getAction() == InteractionEvent.TOUCH){
            for (Bubble bubble : bubbleList) {
                handled = bubble.handleInteractionEvent(event);
            }
        }

        return handled;
    }

    @Override
    public boolean update(long time) {
        ListIterator<Bubble> i = bubbleList.listIterator();
        while (i.hasNext()) {
            Bubble bubble = i.next();
            if (!bubble.update(time)) {
                i.remove();
                Bubble newBubble = new Bubble(this);
                newBubble.start(width, height);
                i.add(newBubble);
            }
        }

        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        for (Bubble bubble : bubbleList) {
            bubble.draw(canvas);
        }
    }
}
