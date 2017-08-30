package com.hewid.alpheus.Model.Game;

import android.util.Log;
import android.view.MotionEvent;

public abstract class World implements GameObject {
    protected int height;
    protected int width;

    public void setSize(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public class GameObjectException extends Exception {
        public GameObjectException(String message) {
            super(message);
        }
    }
}
