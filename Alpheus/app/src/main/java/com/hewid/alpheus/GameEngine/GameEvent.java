package com.hewid.alpheus.GameEngine;

import com.hewid.alpheus.GameEngine.Event;

public class GameEvent extends Event {

    public GameEvent(int action) {
        super(action);
    }

    public static final int GAME_OVER = 0;
    public static final int POSITIVE_REINFORCEMENT = 1;
    public static final int NEGATIVE_REINFORCEMENT = 2;
}
