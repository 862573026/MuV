package com.newx.muv.base.debug;

import android.support.annotation.Nullable;

import com.newx.base.frameworks.util.log.AndroidLogAdapter;


/**
 * Created by xuzhijian on 2018/4/2 0002.
 */

public class NXLogAdapter extends AndroidLogAdapter {
    /**
     * 不能把Log开关放在文件中，不安全
     */
    public static final boolean      IS_ON                       = false;              // 关闭所有日志输出
    public static final boolean      PRINT_STACK_ON              = false;               //是否显示所有Log堆栈 -> 调试用

    @Override
    public boolean isLoggable(int priority, @Nullable String tag) {
       if (IS_ON){
           return false;
       }
       return true;
    }

    @Override
    public boolean needPrintStack() {
        return PRINT_STACK_ON;
    }
}
