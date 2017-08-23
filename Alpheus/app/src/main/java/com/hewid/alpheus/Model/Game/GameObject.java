package com.hewid.alpheus.Model.Game;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface GameObject {
    void update(long time);
    void draw(Canvas canvas);

    void touch(MotionEvent event);
}
