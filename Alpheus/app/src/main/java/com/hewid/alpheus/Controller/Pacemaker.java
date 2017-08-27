package com.hewid.alpheus.Controller;

import com.hewid.alpheus.Model.Game.WorldManager;

public class Pacemaker implements FrameManager {
    private FrameManager view;
    private WorldManager worldManager;
    private Boolean isRunning = false;
    UpdateThread updateThread;
    DrawThread drawThread;


    public Pacemaker(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    public Pacemaker(WorldManager worldManager, FrameManager view) {
        view.register(this);
        this.view = view;
        this.worldManager = worldManager;
    }

    public void start() {
        drawThread = new DrawThread();
        updateThread = new UpdateThread();
        drawThread.start();
        updateThread.start();
        isRunning = true;
    }

    public void kill() {
        if (!isRunning)
            return;

        this.isRunning = false;
        updateThread.interrupt();
        drawThread.interrupt();
    }

    private class UpdateThread extends Thread {

        /**
         * Generates a frame, after that, begins to {@code wait} and the view is responsible to call
         * {@code notify} to inform this thread it has finished drawing and the next frame can start
         * being generated.
         */
        @Override
        public void run() {
            long initialTime = System.currentTimeMillis();
            while (isRunning && !isInterrupted()) {

                // Call worldManager to update the game state
                worldManager.update(System.currentTimeMillis() - initialTime);

                synchronized (this) {
                    try {
                        // Wait until the view calls notify once the frame is drawn
                        this.wait();
                    } catch (InterruptedException e) {
                        isRunning = false;
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
                    isRunning = false;
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
        synchronized (updateThread) {
            updateThread.notify();
        }
    }
}
