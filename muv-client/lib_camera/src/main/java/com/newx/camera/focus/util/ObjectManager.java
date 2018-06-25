package com.newx.camera.focus.util;

import android.graphics.Bitmap;

/**
 * Created by newx on 18-6-20.
 */

public class ObjectManager {

    private Bitmap mCameraBitmap;

    private static ObjectManager obj;

    public static ObjectManager instance() {
        if (obj == null) {
            synchronized (ObjectManager.class) {
                if (obj == null)
                    obj = new ObjectManager();//这里
            }
        }
        return obj;
    }

    public Bitmap getCameraBitmap() {
        return mCameraBitmap;
    }

    public void setCameraBitmap(Bitmap mCameraBitmap) {
        if (mCameraBitmap != null) {
            recycleCameraBitmap();
        }
        this.mCameraBitmap = mCameraBitmap;
    }

    public void recycleCameraBitmap() {
        if (mCameraBitmap != null) {
            if (!mCameraBitmap.isRecycled()) {
                mCameraBitmap.recycle();
            }
            mCameraBitmap = null;
        }
    }
}
