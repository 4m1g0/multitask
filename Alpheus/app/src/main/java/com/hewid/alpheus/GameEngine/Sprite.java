package com.hewid.alpheus.GameEngine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.Log;

import java.sql.Time;

public class Sprite {
    private Bitmap rawBitmap;
    private Bitmap catchedBitmap;
    private int width;
    private int height;
    private int repetitions;
    private double mspf;
    private int sizeOfSprite;
    private Canvas canvas;
    private int lastFrame = -1;
    private Paint p;
    private int sum = 0;
    private Matrix matrix = new Matrix();
    private int repetitionCount = 0;
    private long initTime = 0;

    public Sprite(String filename, int width, int height, int sizeofSprite, int fps, int repetitions) {
        this.mspf = (double) 1000 / fps; // milliseconds per frame
        this.sizeOfSprite = sizeofSprite;
        rawBitmap = AssetHandler.getBitmap(filename);
        this.width = width;
        this.height = height;
        this.repetitions = repetitions;
        catchedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(catchedBitmap);
        p = new Paint();
        p.setAlpha(255);
    }

    public synchronized boolean update(long time) {
        if (initTime == 0) initTime = time;

        int frame = (int)((time - initTime)/ mspf);

        if (frame == lastFrame)
            return true;

        frame %= sizeOfSprite;

        if (frame == 0)
            repetitionCount++;

        if (repetitions > 0 && repetitionCount > repetitions)
            return false;

        lastFrame = frame;
        
        matrix.setTranslate(-frame * width, 0);
        return true;
    }

    public synchronized Bitmap getBitmap() {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(rawBitmap, matrix, p);
        
        return catchedBitmap;
    }
}
