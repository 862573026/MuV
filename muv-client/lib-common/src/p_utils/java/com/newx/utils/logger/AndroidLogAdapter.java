package com.newx.utils.logger;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


/**
 * Created by newx on 18-4-1.
 */

public class AndroidLogAdapter implements LogAdapter {

    @NonNull
    private final FormatStrategy formatStrategy;

    public AndroidLogAdapter() {
        this.formatStrategy = PrettyFormatStrategy.newBuilder().build();
    }

    public AndroidLogAdapter(@NonNull FormatStrategy formatStrategy) {
        this.formatStrategy = Utils.checkNotNull(formatStrategy);
    }

    @Override
    public boolean isLoggable(int priority, @Nullable String tag) {
        return true;
    }

    @Override
    public void log(int priority, @Nullable String tag, @NonNull String message, @Nullable boolean needPrintStack) {
        formatStrategy.log(priority, tag, message, needPrintStack);
    }

    @Override
    public boolean needPrintStack() {
        return false;
    }
}

