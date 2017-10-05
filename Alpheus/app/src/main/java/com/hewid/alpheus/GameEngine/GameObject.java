package com.hewid.alpheus.GameEngine;

import android.graphics.Canvas;

public abstract class GameObject implements InteractionEventHandler {
    protected GameEventHandler gameEventHandler;
    protected int height;
    protected int width;

    public GameObject() {
    }

    public GameObject(GameEventHandler gameEventHandler) {
        this.gameEventHandler = gameEventHandler;
    }

    public void start(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public abstract void update(long time);
    public abstract void draw(Canvas canvas);
}
