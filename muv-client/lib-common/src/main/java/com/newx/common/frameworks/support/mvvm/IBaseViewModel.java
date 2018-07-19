package com.newx.common.frameworks.support.mvvm;

/**
 * Created by xuzhijian on 2018/4/17 0017.
 */

public interface IBaseViewModel {

    /**
     * View的界面创建时回调
     */
    void onCreate();

    /**
     * View的界面销毁时回调
     */
    void onDestroy();

    /**
     * Fragment懒加载
     */
    void lazyLoad();
}
