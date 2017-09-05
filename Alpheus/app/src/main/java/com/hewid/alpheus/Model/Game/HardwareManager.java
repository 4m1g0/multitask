package com.hewid.alpheus.Model.Game;

import android.os.Vibrator;

public class HardwareManager {
    private Vibrator vibrator;

    public HardwareManager(Vibrator vibrator) {
        this.vibrator = vibrator;
    }

    public void vibrate(int time) {
        vibrator.vibrate(time);
    }
}
