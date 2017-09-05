package com.hewid.alpheus.Model.Game;

/**
 * Created by 4m1g0 on 5/09/17.
 */

public class HewidGameEvent extends GameEvent {

    public HewidGameEvent(int action) {
        super(action);
    }

    public static final int GAME_OVER = 0;
    public static final int POSITIVE_REINFORCEMENT = 1;
    public static final int NEGATIVE_REINFORCEMENT = 2;
}
