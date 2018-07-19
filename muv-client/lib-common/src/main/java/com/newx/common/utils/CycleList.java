package com.newx.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuzhijian on 2018/7/12 0012.
 * 循环列表
 */
public class CycleList<T> {

    private List<T> list;
    private int index; //当前index

    public CycleList() {
        list = new ArrayList<>();
        index = 0;
    }

    public void add(T t) {
        list.add(t);
    }

    public void add(int index, T t) {
        list.add(index, t);
    }

    public void remove(T t) {
        list.remove(t);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public void clear() {
        list.clear();
    }

    public T get(int index) {
        return list.get(index);
    }

    public boolean contains(T t) {
        return list.contains(t);
    }

    public int indexOf(T t) {
        return list.indexOf(t);
    }

    public T next() {
        index = (index + 1) % list.size();
        return list.get(index);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index % list.size();
    }
}
