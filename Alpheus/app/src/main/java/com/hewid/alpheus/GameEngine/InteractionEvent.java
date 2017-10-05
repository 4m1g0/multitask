package com.hewid.alpheus.GameEngine;

import com.hewid.alpheus.GameEngine.Event;

public class InteractionEvent extends Event {

    public InteractionEvent(int action) {
        super(action);
    }

    public InteractionEvent(int action, Object payload) {
        super(action, payload);
    }

    public static final int TOUCH = 0;
    public static final int ACCELEROMETER = 1;
}
