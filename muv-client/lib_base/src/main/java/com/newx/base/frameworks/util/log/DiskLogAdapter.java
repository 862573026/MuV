package com.newx.base.frameworks.util.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.newx.base.frameworks.util.log.Utils.checkNotNull;


/**
 * Created by newx on 18-4-1.
 * This is used to saves log messages to the disk.
 * By default it uses {@link CsvFormatStrategy} to translates text message into CSV format.
 */
public class DiskLogAdapter implements LogAdapter {

    @NonNull
    private final FormatStrategy formatStrategy;

    public DiskLogAdapter() {
        formatStrategy = CsvFormatStrategy.newBuilder().build();
    }

    public DiskLogAdapter(@NonNull FormatStrategy formatStrategy) {
        this.formatStrategy = checkNotNull(formatStrategy);
    }

    @Override public boolean isLoggable(int priority, @Nullable String tag) {
        return true;
    }

    @Override
    public void log(int priority, @Nullable String tag, @NonNull String message, @Nullable boolean needPrintStack) {
        formatStrategy.log(priority, tag, message,needPrintStack);
    }

    @Override
    public boolean needPrintStack() {
        return false;
    }

}