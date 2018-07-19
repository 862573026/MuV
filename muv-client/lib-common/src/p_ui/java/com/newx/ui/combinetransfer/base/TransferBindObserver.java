package com.newx.ui.combinetransfer.base;


import com.newx.ui.combinetransfer.observer.BindProperty;

/**
 * Created by xuzhijian on 2018/4/11 0011.
 * TBS -> TransferBindSend
 * TBR -> TransferBindReceive
 * 联动变化 观察者
 */

public interface TransferBindObserver {


    void setEventTag(String tag);

    void setProperty(BindProperty property);
}
