package com.hewid.alpheus.Model.Game;

public abstract class Event  {
    private int action;
    private Object payload;

    public Event(int action) {
        this.action = action;
    }

    public Event(int action, Object payload) {
        this.payload = payload;
        this.action = action;
    }

    public int getAction() {
        return action;
    }

    public Object getPayload() {
        return payload;
    }
}
