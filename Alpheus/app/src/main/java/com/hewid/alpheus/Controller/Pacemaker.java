package com.hewid.alpheus.Controller;
import com.hewid.alpheus.Model.Game.World;

public class Pacemaker extends Thread implements FrameManager{
    private FrameManager view;
    private World world;
    private Boolean isRunning;


    public Pacemaker(World world) {
        this.world = world;
    }

    public Pacemaker(World world, FrameManager view) {
        view.register(this);
        this.view = view;
        this.world = world;
    }

    public void kill(){
        this.isRunning = false;
        this.interrupt();
    }

    /**
     * Generates a frame and notify the view to invalidate previous frame and redraw,
     * after that, this thread begins to {@code wait} and the view is responsible to call
     * {@code notify} to inform this thread it has finished drawing and the next frame can start
     * being generated.
     */
    @Override
    public void run() {
        long initialTime = System.currentTimeMillis();
        while (isRunning && !isInterrupted()) {

            // Call world to update the game state
            world.update(System.currentTimeMillis()-initialTime);

            synchronized (this) {
                // Notify the view that a new frame is available
                view.notifyFrame();
                try {
                    // Wait until the view calls notify once the frame is drawn
                    this.wait();
                } catch (InterruptedException e) {
                    isRunning = false;
                }
            }
        }
    }

    /**
     * Registers the object this thread has to notify when a new frame is generated
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
        this.notify();
    }
}
