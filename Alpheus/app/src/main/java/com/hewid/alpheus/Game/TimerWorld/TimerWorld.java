package com.hewid.alpheus.Game.TimerWorld;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.hewid.alpheus.GameEngine.AssetHandler;
import com.hewid.alpheus.GameEngine.GameEvent;
import com.hewid.alpheus.GameEngine.GameEventHandler;
import com.hewid.alpheus.GameEngine.InteractionEvent;
import com.hewid.alpheus.GameEngine.World;

public class TimerWorld extends World{
    private Bitmap timerBackground;
    private int timerWidth;
    private int timerHeight;
    private int timerStartPoint;
    private static final int MARGIN_TOP = 35;

    public TimerWorld(GameEventHandler gameEventHandler) {
        super(gameEventHandler);
    }

    @Override
    public void start(int width, int height) {
        super.start(width, height);

        timerBackground = AssetHandler.getBitmap("timer.png");
        timerHeight = timerBackground.getHeight();
        timerWidth = timerBackground.getWidth();
        timerStartPoint = (width - timerWidth);
    }

    @Override
    public boolean update(long time) {
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(timerBackground, timerStartPoint, MARGIN_TOP, null);
    }

    @Override
    public void handleGameEvent(GameEvent event) {

    }

    @Override
    public boolean handleInteractionEvent(InteractionEvent event) {
        return false;
    }


}
