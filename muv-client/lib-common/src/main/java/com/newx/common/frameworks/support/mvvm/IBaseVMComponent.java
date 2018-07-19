package com.newx.common.frameworks.support.mvvm;

/**
 * Created by xuzhijian on 2018/4/17 0017.
 */

public interface IBaseVMComponent {

    /**
     * 初始化界面传递参数
     */
    void initParam();
    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化界面观察者的监听
     */
    void initViewObservable();
}
