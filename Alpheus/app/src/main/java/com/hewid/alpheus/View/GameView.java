package com.hewid.alpheus.View;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hewid.alpheus.Controller.FrameManager;
import com.hewid.alpheus.Model.Game.InteractionEvent;
import com.hewid.alpheus.Model.Game.WorldManager;

public class GameView extends View implements FrameManager {
    FrameManager paceMaker;
    private WorldManager worldManager;

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void attachWorld(final WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    @Override
    public void register(FrameManager frameManager) {
        frameManager.register(this);
        this.paceMaker = frameManager;
    }

    @Override
    public void notifyFrame() {
        this.postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        worldManager.draw(canvas);
        paceMaker.notifyFrame();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (worldManager != null) {

            worldManager.handleInteractionEvent(new InteractionEvent(InteractionEvent.TOUCH, event));
        }

        return true;

    }
}
