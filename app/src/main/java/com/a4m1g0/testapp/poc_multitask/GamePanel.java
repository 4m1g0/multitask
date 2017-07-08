package com.a4m1g0.testapp.poc_multitask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class GamePanel extends View {
    private static final int RADIUS = 50;
    private int deltaX = 0;
    Paint paint;
    private FailListener listener;

    public GamePanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public GamePanel(Context context) {
        super(context);
        initialize();
    }

    private void initialize() {
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int originX = canvas.getWidth()/2;
        int originY = canvas.getHeight()-RADIUS;

        canvas.drawCircle(originX + deltaX, originY, RADIUS, paint);
    }

    // TODO: El parametro delta de esta función define la velocidad, al sumarlo iterativamente
    // a deltaX obtenemos la posición, el problema es que delta deberia ser la aceleración por que
    // viene del acelerómetro, por lo tanto tendría que asignar una variable aceleración que en cada
    // tick incrementase una variable velocidad, que en cada tick incrementase una variable posicion

    public void setDeltaX(int delta) {
        deltaX += delta;
    }

    public void bind(FailListener listener) {
        this.listener = listener;
    }

    public interface FailListener {
        void fail();
    }
}
