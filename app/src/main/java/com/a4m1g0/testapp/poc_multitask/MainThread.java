package com.a4m1g0.testapp.poc_multitask;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by 4m1g0 on 8/07/17.
 */

class MainThread extends Thread {
    public static final int MAX_FPS = 30;
    private static final String TAG = "MainThread";
    private final SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean isRunning;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public void setNotRunning() {
        isRunning = false;
        interrupt();
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        isRunning = true;

        while (isRunning) {
            startTime = System.nanoTime();
            Canvas canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();

                if (canvas != null)
                {
                    synchronized(surfaceHolder) {
                        gamePanel.update();
                        gamePanel.draw(canvas);
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null)
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                if (waitTime > 0)
                    sleep(waitTime);
            } catch (Exception e) {
                if (e instanceof InterruptedException && !isRunning)
                    break;
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if (frameCount == MAX_FPS) {
                double averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                Log.d(TAG, "Average FPS: " + averageFPS);
            }
        }

    }
}
