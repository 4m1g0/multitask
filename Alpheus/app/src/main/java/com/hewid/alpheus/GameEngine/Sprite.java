package com.hewid.alpheus.GameEngine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class Sprite {
    private Bitmap rawBitmap;
    private Bitmap catchedBitmap;
    private int width, height;
    Canvas canvas;

    public Sprite(String filename, int width, int height, int sizeofSprite){
        rawBitmap = AssetHandler.getBitmap(filename);
        this.width = width;
        this.height = height;
        Matrix matrix = new Matrix();
        catchedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(catchedBitmap);
        canvas.drawBitmap(rawBitmap, matrix, null);
    }

    public void update(long time) {
        Matrix matrix = new Matrix();
        matrix.postTranslate(width, 0);
        // TODO:
        //use sizeOfSprite to cycle on end
        canvas.drawBitmap(rawBitmap, matrix, null);
    }

    public Bitmap getBitmap() {
        return catchedBitmap;
    }
}
