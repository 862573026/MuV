package com.newx.utils.text;

/**
 * Created by xuzhijian on 2018/4/10 0010.
 * 封装 StringBuilder 和 StringBuffer
 */

public class DynamicStr {

    public static StringBuilder mBuilder;

    public static StringBuffer mBuffer;


    /**
     * 创建
     *
     * @param strs
     * @return
     */
    public static String create(Object... strs) {
        StringBuilder builder = new StringBuilder();
        for (Object str : strs) {
            builder.append(str);
        }
        return builder.toString();
    }

    public static String createBuffer(Object... strs) {
        StringBuffer buffer = new StringBuffer();
        for (Object str : strs) {
            buffer.append(str);
        }
        return buffer.toString();
    }

    public static StringBuilder create(){
        mBuilder = new StringBuilder();
        return mBuilder;
    }

    public static StringBuilder append(String str) {
        if (mBuilder == null) {
            mBuilder = new StringBuilder();
        }
        mBuilder.append(str);
        return mBuilder;
    }

    public static StringBuffer appendBuffer(String str){
        mBuffer.append(str);
        return mBuffer;
    }

}
