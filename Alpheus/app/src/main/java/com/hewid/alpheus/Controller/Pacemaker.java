package com.hewid.alpheus.Controller;

import com.hewid.alpheus.Model.Game.WorldManager;

public class Pacemaker implements FrameManager {
    private FrameManager view;
    private WorldManager worldManager;
    private Boolean isRunning = false;
    private UpdateThread updateThread;
    private DrawThread drawThread;
    private long initialTime;
    private long pauseTime;
    private boolean isPaused;
    private final Object pauseMutex = new Object();
    private Object frameMutex = new Object();


    public Pacemaker(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    public Pacemaker(WorldManager worldManager, FrameManager view) {
        view.register(this);
        this.view = view;
        this.worldManager = worldManager;
    }

    public void start() {
        updateThread = new UpdateThread();
        drawThread = new DrawThread();
        isRunning = true;
        drawThread.start();
        updateThread.start();
    }

    public void kill() {
        if (!isRunning)
            return;

        this.isRunning = false;
        updateThread.interrupt();
        drawThread.interrupt();
    }

    public void resume() {
        initialTime += System.currentTimeMillis() - pauseTime;
        synchronized (pauseMutex) {
            isPaused = false;
            pauseMutex.notifyAll();
        }
    }

    public void pause() {
        synchronized (pauseMutex){
            isPaused = true;
        }
        pauseTime = System.currentTimeMillis();
    }

    private class UpdateThread extends Thread {

        /**
         * Generates a frame, after that, begins to {@code wait} and the view is responsible to call
         * {@code notify} to inform this thread it has finished drawing and the next frame can start
         * being generated.
         */
        @Override
        public void run() {
            while (isRunning && !isInterrupted()) {
                // Call worldManager to update the game state
                worldManager.update(System.currentTimeMillis() - initialTime);

                synchronized (frameMutex) {
                    try {
                        // Wait until the view calls notify once the frame is drawn
                        frameMutex.wait();
                    } catch (InterruptedException e) {
                        break;
                    }
                }

                synchronized (pauseMutex) {
                    try {
                        if (isPaused){
                            // game is in pause, wait until notify
                            pauseMutex.wait();
                        }
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Each 16ms (60fps) invalidates the view so that it can draw the current state.
     * No matter if the worldManager is being updated at the same time.
     */
    private class DrawThread extends Thread {
        @Override
        public void run() {
            while (isRunning && !isInterrupted()) {
                view.notifyFrame();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    break;
                }

                synchronized (pauseMutex) {
                    try {
                        if (isPaused){
                            // game is in pause, wait until notify
                            pauseMutex.wait();
                        }
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Registers the object this thread has to notify when a new frame is generated
     *
     * @param frameManager The reference of the object to notify (normally the view)
     */
    @Override
    public void register(FrameManager frameManager) {
        this.view = frameManager;
    }

    /**
     * Method called from the other {@code FrameManager} when a new frame is drawn.
     */
    @Override
    public void notifyFrame() {
        synchronized (frameMutex) {
            frameMutex.notify();
        }
    }
}
