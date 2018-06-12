package com.newx.base.frameworks.util.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.newx.base.frameworks.util.log.Utils.checkNotNull;


/**
 * Created by newx on 18-4-1.
 * LogCat implementation for {@link LogStrategy}
 *
 * This simply prints out all logs to Logcat by using standard {@link Log} class.
 */
public class LogcatLogStrategy implements LogStrategy {

    static final String DEFAULT_TAG = "NO_TAG";

    @Override public void log(int priority, @Nullable String tag, @NonNull String message) {
        checkNotNull(message);

        if (tag == null) {
            tag = DEFAULT_TAG;
        }

        Log.println(priority, tag, message);
    }
}
