package com.hewid.alpheus.Game;

import android.graphics.Canvas;

import com.hewid.alpheus.GameEngine.GameEvent;
import com.hewid.alpheus.GameEngine.InteractionEvent;
import com.hewid.alpheus.GameEngine.World;
import com.hewid.alpheus.Game.SeesawWorld.SeesawWorld;

import java.util.ArrayList;
import java.util.List;

public class WorldManager extends World {
    private List<World> subworlds;
    private int n = 2;
    private HardwareManager hardwareManager;
    private boolean isGameOver;

    public WorldManager(HardwareManager hardwareManager) {
        this.hardwareManager = hardwareManager;
        subworlds = new ArrayList<>();

        subworlds.add(new SubworldTest(this));
        subworlds.add(new SeesawWorld(this));
    }

    @Override
    public void start(int width, int height) {
        super.start(width, height);

        for (int i = 0; i < n; i++) {
            subworlds.get(i).start(width, height);
        }
    }

    @Override
    public void update(long time) {
        if (isGameOver)
            return;

        //if (time %12) n++

        for (int i = 0; i < n; i++) {
            subworlds.get(i).update(time);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (!isStarted())
            return;

        //if (time %12) n++

        for (int i = 0; i < n; i++) {
            subworlds.get(i).draw(canvas);
        }
    }

    @Override
    public void handleGameEvent(GameEvent event) {
        switch(event.getAction()){
            case GameEvent.POSITIVE_REINFORCEMENT:
                hardwareManager.vibrate(200);
                break;
            case GameEvent.GAME_OVER:
                // This 'isGameOver' should not be here, It should exist
                // another mechanism to do this...
                this.isGameOver = true;
                hardwareManager.vibrate(400);
                break;
        }
    }

    @Override
    public boolean handleInteractionEvent(InteractionEvent event) {
        for (int i = 0; i < n; i++) {
            if (subworlds.get(i).handleInteractionEvent(event)) {
                return true;
            }
        }

        return false;
    }
}
