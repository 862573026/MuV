package com.newx.camera.util;

import android.content.Context;
import android.provider.MediaStore;

import java.io.FileNotFoundException;

/**
 * Created by xuzhijian on 2018/7/11 0011.
 */

public class MediaUtil {

    public static void insertImage(Context context, String path, String name, String description) {
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), path, name, description);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
