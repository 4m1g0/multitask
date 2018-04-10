package com.hewid.alpheus.Game.SeesawWorld;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.SensorEvent;

import com.hewid.alpheus.GameEngine.AssetHandler;
import com.hewid.alpheus.GameEngine.GameEvent;
import com.hewid.alpheus.GameEngine.GameEventHandler;
import com.hewid.alpheus.GameEngine.GameObject;
import com.hewid.alpheus.GameEngine.InteractionEvent;

public class Ball extends GameObject {
    private Bitmap catchedBitmap;
    private Platform platform;
    private final int ballDiameter = 100;
    private int position;
    private float acceleration, accelerationAccomulation;
    private float speed;
    private int nValues;
    private final float RANDOMACC = 0.15f;
    private final int RANDOMTIME = 5000;
    private long lastRandomTime;
    private double currentRandAcc;
    private long lastUpdateTime;
    private Bitmap ballBitmap;
    private Matrix ballTransformMatrix = new Matrix();

    public Ball(GameEventHandler gameEventHandler, Platform platform) {
        super(gameEventHandler);
        this.platform = platform;
    }

    @Override
    public void start(int width, int height) {
        super.start(width, height);

        ballBitmap = AssetHandler.getBitmap("ball.png");


//        catchedBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
//        Canvas c = new Canvas(catchedBitmap);
//        Paint paint = new Paint();
//        paint.setColor(Color.rgb(200, 10, 10));
//        paint.setAntiAlias(true);
//        c.drawCircle(50, 50, 50, paint);
        this.position = width / 2 - ballDiameter / 2;
    }

    @Override
    public boolean handleInteractionEvent(InteractionEvent event) {
        if (event.getAction() == InteractionEvent.ACCELEROMETER) {
            SensorEvent acc = (SensorEvent) event.getPayload();

            synchronized (this) {
                accelerationAccomulation += acc.values[1];
                nValues++;
            }
        }
        return false;
    }

    private synchronized void calculateMeanAcceleration() {
        if (nValues == 0) {
            acceleration = accelerationAccomulation;
            return;
        }

        acceleration = accelerationAccomulation / nValues;
        accelerationAccomulation = 0;
        nValues = 0;
    }

    private void randomizeAcceleration(long time) {
        if (time - lastRandomTime > RANDOMTIME) {
            lastRandomTime = time;
            currentRandAcc = Math.random() * RANDOMACC * 2 - RANDOMACC;
        } else {
            acceleration += currentRandAcc;
        }
    }

    @Override
    public boolean update(long time) {
        long elapsedTime = time - lastUpdateTime;
        lastUpdateTime = time;
        calculateMeanAcceleration();
        randomizeAcceleration(time);
        speed += acceleration * ((float) elapsedTime / 20);
        if (Math.abs(speed) < 0.1) {
            if (acceleration > 0)
                speed = 0.2f;
            else
                speed = -0.2f;
        }
        position += speed * ((double) elapsedTime / 20);

        if (isBallOutOfPlatform()) {
            gameEventHandler.handleGameEvent(new GameEvent(GameEvent.GAME_OVER));
        }

        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        ballTransformMatrix.setTranslate(position, height - platform.getHeight() - ballDiameter);
        ballTransformMatrix.postRotate(position % 360, position + ballDiameter / 2,
                height - platform.getHeight() - ballDiameter / 2);
        canvas.drawBitmap(ballBitmap, ballTransformMatrix, null);
    }

    private boolean isBallOutOfPlatform() {
        int centerBallPosition = position + ballDiameter / 2;

        return (centerBallPosition < (this.width - platform.getWidth()) / 2) ||
                (centerBallPosition > platform.getWidth() + (this.width - platform.getWidth()) / 2);
    }
}
