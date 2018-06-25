package com.newx.camera.focus.util;


import com.newx.camera.focus.Constant;

/**
 * Created by newx on 18-6-20.
 */

public class CameraUtil {

    public static String getCameraImgPath() {
        return Constant.PHOTO_DIR + "/" + System.currentTimeMillis() + ".jpg";
    }

}
