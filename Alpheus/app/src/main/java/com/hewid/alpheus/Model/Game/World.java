package com.hewid.alpheus.Model.Game;

public abstract class World extends GameObject implements GameEventHandler {
    protected int height;
    protected int width;
    protected boolean started = false;

    public World() {
    }

    public World(GameEventHandler gameEventHandler) {
        super(gameEventHandler);
    }

    public void start(int width, int height) {
        this.height = height;
        this.width = width;
    }
}
