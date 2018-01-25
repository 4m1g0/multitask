package com.hewid.alpheus.Game.SeesawWorld;

import android.graphics.Canvas;

import com.hewid.alpheus.GameEngine.GameEvent;
import com.hewid.alpheus.GameEngine.GameEventHandler;
import com.hewid.alpheus.GameEngine.InteractionEvent;
import com.hewid.alpheus.GameEngine.World;

public class SeesawWorld extends World {
    private Ball ball;
    private Platform platform;

    public SeesawWorld(GameEventHandler gameEventHandler) {
        super(gameEventHandler);

        platform = new Platform();
        ball = new Ball(this, platform);
    }

    @Override
    public void start(int width, int height) {
        super.start(width, height);

        platform.start(width, height);
        ball.start(width, height);
    }

    @Override
    public void handleGameEvent(GameEvent event) {
        gameEventHandler.handleGameEvent(event);
    }

    @Override
    public boolean handleInteractionEvent(InteractionEvent event) {
        if (event.getAction() == InteractionEvent.ACCELEROMETER){
            return ball.handleInteractionEvent(event);
        }

        return false;
    }

    @Override
    public boolean update(long time) {
        platform.update(time);
        ball.update(time);
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        platform.draw(canvas);
        ball.draw(canvas);
    }
}
