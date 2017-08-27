package com.hewid.alpheus.Model.Game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

public class WorldManager extends World {
    private List<World> subworlds;
    private int n = 1;

    public WorldManager(int height, int width) {
        super(height, width);

        subworlds = new ArrayList<>();
        subworlds.add(new SubworldTest(height, width));
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
}
