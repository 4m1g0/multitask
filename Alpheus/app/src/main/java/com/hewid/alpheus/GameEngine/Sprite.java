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
    private int width, height;
    private double mspf;
    private int sizeOfSprite;
    Canvas canvas;
    private int lastFrame = -1;
    private Paint p;
    private int sum = 0;

    public Sprite(String filename, int width, int height, int sizeofSprite, int fps) {
        this.mspf = (double) 1000 / fps;
        this.sizeOfSprite = sizeofSprite;
        rawBitmap = AssetHandler.getBitmap(filename);
        this.width = width;
        this.height = height;
        catchedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(catchedBitmap);
        p = new Paint();
        p.setAlpha(255);
    }

    public synchronized void update(long time) {
        int frame = (int)((time / mspf) % sizeOfSprite);

        if (frame == lastFrame)
            return;

        lastFrame = frame;

        Matrix matrix = new Matrix();
        matrix.postTranslate(-frame * width, 0);

        canvas.setBitmap(catchedBitmap);
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(rawBitmap, matrix, p);
    }

    public synchronized Bitmap getBitmap() {
        return catchedBitmap;
    }
}
