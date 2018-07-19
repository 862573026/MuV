package com.newx.utils.logger;

/**
 * Created by newx on 18-4-1.
 */

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * A proxy interface to enable additional operations.
 * Contains all possible Log message usages.
 */
public interface Printer {

    void addAdapter(@NonNull LogAdapter adapter);

    Printer t(@Nullable String tag);

    /**
     * 可以根据需要，设置个全局打印堆栈的开关
     * @return
     */
    Printer printStack();

    Printer useDefaultTAG();

    void d(@NonNull String message, @Nullable Object... args);

    void d(@Nullable Object object);

    void e(@NonNull String message, @Nullable Object... args);

    void e(@NonNull String message, boolean normal, @Nullable Object... args);

    void e(@Nullable Throwable throwable, @NonNull String message, @Nullable Object... args);

    void w(@NonNull String message, @Nullable Object... args);

    void i(@NonNull String message, @Nullable Object... args);

    void v(@NonNull String message, @Nullable Object... args);

    void wtf(@NonNull String message, @Nullable Object... args);

    /**
     * Formats the given json content and print it
     */
    void json(@Nullable String json);

    /**
     * Formats the given xml content and print it
     */
    void xml(@Nullable String xml);

    /**
     *
     * @param priority
     * @param tag
     * @param message
     * @param throwable
     * @param normal 是否是普通Log
     */
    void log(int priority, @Nullable String tag, @Nullable String message, @Nullable Throwable throwable, @Nullable boolean normal);

    void clearLogAdapters();
}
