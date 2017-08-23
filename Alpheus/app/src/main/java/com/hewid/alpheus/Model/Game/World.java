package com.hewid.alpheus.Model.Game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

public class World implements GameObject {
    List<SubWorld> subworlds;
    int n = 1;

    public World() {
        subworlds = new ArrayList<>();
        subworlds.add(new SubworldTest());
    }

    @Override
    public void update(long time) {
        //if (time %12) n++

        for (int i = 0; i < n; i++) {
            subworlds.get(i).update(time);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        //if (time %12) n++

        for (int i = 0; i < n; i++) {
            subworlds.get(i).draw(canvas);
        }
    }

    @Override
    public void touch(MotionEvent event) {

    }
}
