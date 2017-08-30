package com.hewid.alpheus.Model.Game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

public class WorldManager extends World {
    private List<World> subworlds;
    private int n = 1;

    public WorldManager() {
        subworlds = new ArrayList<>();
        subworlds.add(new SubworldTest());
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
    public boolean touch(MotionEvent event) {
        //if (time %12) n++

        for (int i = 0; i < n; i++) {
            if (subworlds.get(i).touch(event)) {
                Log.d("TEST","***RETURNED TRUE***");
                return true;
            }
        }

        Log.d("TEST","---RETURN FALSE---");
        return false;
    }
}
