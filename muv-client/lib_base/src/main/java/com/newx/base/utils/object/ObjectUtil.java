package com.newx.base.utils.object;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by newx on 18-4-12.
 */

public class ObjectUtil {

    @NonNull
    public static <T> T checkNotNull(@Nullable final T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }
}
