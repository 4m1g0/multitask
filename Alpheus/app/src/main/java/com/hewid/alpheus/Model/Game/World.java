package com.hewid.alpheus.Model.Game;

public abstract class World extends GameObject implements GameEventHandler {
    private boolean started = false;

    public World() {
    }

    protected boolean isStarted(){
        return started;
    }

    @Override
    public void start(int width, int height) {
        super.start(width, height);
        started = true;
    }

    public World(GameEventHandler gameEventHandler) {
        super(gameEventHandler);
    }
}
