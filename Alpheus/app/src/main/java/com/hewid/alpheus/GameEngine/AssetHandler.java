package com.hewid.alpheus.GameEngine;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

class AssetHandler {
    private static AssetManager assetManager;

    static void setAssetManager(AssetManager assetManager) {
        AssetHandler.assetManager = assetManager;
    }

    public static Bitmap getBitmap(String assetName) {
        try {
            InputStream bmpStream = assetManager.open(assetName, AssetManager.ACCESS_BUFFER);
            return BitmapFactory.decodeStream(bmpStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
