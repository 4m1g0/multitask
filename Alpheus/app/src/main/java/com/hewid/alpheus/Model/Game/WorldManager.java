package com.hewid.alpheus.Model.Game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

public class WorldManager extends World {
    private List<World> subworlds;
    private int n = 1;
    private HardwareManager hardwareManager;

    public WorldManager(HardwareManager hardwareManager) {
        this.hardwareManager = hardwareManager;
        subworlds = new ArrayList<>();
        subworlds.add(new SubworldTest(this));
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);

        for (int i = 0; i < n; i++) {
            subworlds.get(i).setSize(width, height);
        }
    }

    @Override
    public void update(long time) {
        //if (time %12) n++

        for (int i = 0; i < n; i++) {
            subworlds.get(i).update(time);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        //if (time %12) n++

        for (int i = 0; i < n; i++) {
            subworlds.get(i).draw(canvas);
        }
    }

    @Override
    public void touch(MotionEvent event) {

    }

    @Override
    public void handleEvent(GameEvent event) {
        if (event.getAction() == HewidGameEvent.POSITIVE_REINFORCEMENT) {
            hardwareManager.vibrate(10);
        }
    }
}
