package com.newx.ui.adapter.provider;

import android.content.Context;

import com.newx.ui.adapter.BaseViewHolder;

import java.util.List;

/**
 * https://github.com/chaychan
 * @author ChayChan
 * @description: The base class of ItemProvider
 * @date 2018/3/21  10:41
 */

public abstract class BaseItemProvider<T,V extends BaseViewHolder> {

    public Context mContext;
    public List<T> mData;

    public abstract void convert(V helper, T data, int position);

    public abstract void onClick(V helper, T data, int position);

    public abstract boolean onLongClick(V helper, T data, int position);
}
