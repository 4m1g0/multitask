package com.hewid.alpheus.Model.Game;

public abstract class SubWorld implements GameObject {


    public class GameObjectException extends Exception {
        public GameObjectException(String message) {
            super(message);
        }
    }
}
