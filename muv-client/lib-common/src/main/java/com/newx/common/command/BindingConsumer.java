package com.newx.common.command;

/**
 * Created by xuzhijian on 2018/4/17 0017.
 */

public interface BindingConsumer<T> {
    void call(T t);
}

