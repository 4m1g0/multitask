package com.hewid.alpheus.Controller;

import android.view.MotionEvent;
import android.view.View;

import com.hewid.alpheus.Model.Game.WorldManager;

public class TouchListener implements View.OnTouchListener {
    WorldManager worldManager;

    public TouchListener(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        worldManager.touch(event);
        throw new UnsupportedOperationException();
    }
}
