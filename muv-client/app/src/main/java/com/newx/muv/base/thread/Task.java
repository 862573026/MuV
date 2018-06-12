package com.newx.muv.base.thread;

/**
 * Created by xuzhijian on 2018/5/29 0029.
 */

public interface Task<T> {
    void execute(T t) throws Exception;
}
