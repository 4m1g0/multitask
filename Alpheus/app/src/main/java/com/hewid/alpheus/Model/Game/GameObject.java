package com.hewid.alpheus.Model.Game;

import android.graphics.Canvas;

public interface GameObject {
    void update(long time);
    void draw(Canvas canvas);
}
