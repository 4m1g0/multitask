package com.hewid.alpheus.GameEngine;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View implements FrameManager {
    FrameManager paceMaker;
    private World world;

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void attachWorld(final World world) {
        this.world = world;
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
        world.draw(canvas);
        paceMaker.notifyFrame();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (world != null) {
            world.handleInteractionEvent(new InteractionEvent(InteractionEvent.TOUCH, event));
        }

        return true;

    }
}
