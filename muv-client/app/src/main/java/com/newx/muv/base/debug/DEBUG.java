package com.newx.muv.base.debug;

/**
 * Created by xuzhijian on 2018/4/10 0010.
 */

public class DEBUG {

    public static class TAG {
        public static final String SYSTEM_LOG = "SYSTEM_LOG";
    }


    public static class MESSAGE {
        public static final String MESSAGE_LAYOUTID_IS_INVALID_ERROR = "请在 %1$s 的 initContentView 方法设置LayoutId";

        public static final String MESSAGE_LAYOUT_NOT_SUPPORT_DATABINDING = "%1$s 的 initContentView 方法里设置的界面不支持DataBinding，考虑加入<layout>，或者自己设置contentView";


        public static final String MESSAGE_BIND_OBSERVABLE_IS_NULL = "如需使用联动变化控件，请设置TAG且不能为空";



    }
}
