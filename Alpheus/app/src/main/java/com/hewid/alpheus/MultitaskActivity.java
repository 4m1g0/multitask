package com.hewid.alpheus;

import android.os.Vibrator;

import com.hewid.alpheus.GameEngine.GameActivity;
import com.hewid.alpheus.Game.HardwareManager;
import com.hewid.alpheus.Game.WorldManager;

public class MultitaskActivity extends GameActivity{
    @Override
    protected void onWorldCreate() {
        HardwareManager hardwareManager = new HardwareManager((Vibrator) getSystemService(VIBRATOR_SERVICE));
        setWorld(new WorldManager(hardwareManager));
    }
}
