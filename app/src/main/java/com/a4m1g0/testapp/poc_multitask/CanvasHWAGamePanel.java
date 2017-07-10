package com.a4m1g0.testapp.poc_multitask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by cristian on 10/07/17.
 */

public class CanvasHWAGamePanel extends View {
    private Paint paint;
    private BallGroup ballGroup;

    public CanvasHWAGamePanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addTreeObserver();
    }

    public CanvasHWAGamePanel(Context context) {
        super(context);
        addTreeObserver();
    }

    private void addTreeObserver() {
        final ViewTreeObserver observer = this.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                initialize();
            }
        });
    }

    private void initialize() {
        paint = new Paint();
        paint.setColor(Color.RED);
        //setLayerType(View.LAYER_TYPE_HARDWARE, null);
        ballGroup = new BallGroup(2000, getWidth(), getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ballGroup.draw(canvas);
        invalidate();
    }


}


