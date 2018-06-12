package com.newx.muv.base.thread;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xuzhijian on 2018/5/28 0028.
 * 线程封装
 */

public class ThreadUtil {

    public static void doInMainThread(Consumer task) {
        Flowable.just(task)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> task.accept(result));
    }

    public static void doInIOThread(Consumer task) {
        Flowable.just(task)
                .observeOn(Schedulers.io())
                .subscribe(result -> task.accept(result));
    }
}
