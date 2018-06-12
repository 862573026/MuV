package com.newx.base.frameworks.util.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by newx on 18-4-1.
 * Used to determine how messages should be printed or saved.
 */
public interface FormatStrategy {

    void log(int priority, @Nullable String tag, @NonNull String message, @NonNull boolean needPrintStack);
}
