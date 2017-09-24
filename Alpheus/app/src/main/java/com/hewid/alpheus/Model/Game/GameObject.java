package com.hewid.alpheus.Model.Game;

import android.graphics.Canvas;
import android.view.MotionEvent;

public abstract class GameObject implements InteractionEventHandler{

    protected GameEventHandler gameEventHandler;

    public GameObject() {
    }

    public GameObject(GameEventHandler gameEventHandler) {
        this.gameEventHandler = gameEventHandler;
    }

    public abstract void update(long time);
    public abstract void draw(Canvas canvas);
}
