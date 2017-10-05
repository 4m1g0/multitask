package com.hewid.alpheus.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.hewid.alpheus.GameEngine.GameEvent;
import com.hewid.alpheus.GameEngine.GameEventHandler;
import com.hewid.alpheus.GameEngine.InteractionEvent;
import com.hewid.alpheus.GameEngine.World;

class SubworldTest extends World {
    private Bitmap catchedBitmap;
    private int[] position = new int[2];
    private float[] speed = new float[]{1f, 1f};

    public SubworldTest(GameEventHandler gameEventHandler) {
        super(gameEventHandler);
        catchedBitmap = Bitmap.createBitmap(100,100,Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(catchedBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.rgb(12,200,170));
        c.drawCircle(50,50,50,paint);
    }

    @Override
    public void update(long time) {
        position[0] = (int)((speed[0] * time) % (width - 100));
        position[1] = (int)((speed[1] * time) % (height - 100));
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(catchedBitmap, position[0], position[1], null);
    }

    @Override
    public void handleGameEvent(GameEvent event) {
        gameEventHandler.handleGameEvent(event);
    }

    @Override
    public boolean handleInteractionEvent(InteractionEvent event) {
        if (event.getAction() == InteractionEvent.TOUCH) {
            MotionEvent motionEvent = (MotionEvent) event.getPayload();
            int[] ballCenter = {position[0] + 50, position[1] + 50};
            double distance = Math.sqrt(Math.pow(ballCenter[0] - motionEvent.getX(), 2) + Math.pow(ballCenter[1] - motionEvent.getY(), 2));

            if (distance <= 50){
                gameEventHandler.handleGameEvent(new GameEvent(GameEvent.POSITIVE_REINFORCEMENT));
                return true;
            }
        }
        return false;
    }
}
