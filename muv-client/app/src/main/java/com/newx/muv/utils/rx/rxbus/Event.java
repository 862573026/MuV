package com.newx.muv.utils.rx.rxbus;

/**
 * Created by xuzhijian on 2018/4/4 0004.
 * 事件
 */

public class Event<T> {

    private String mEventName;
    private T mContent;

    public Event(String eventName) {
        mEventName = eventName;
    }

    public Event(String eventName, T content) {
        mEventName = eventName;
        mContent = content;
    }

    public String getEventName() {
        return mEventName;
    }

    public void setEventName(String eventName) {
        mEventName = eventName;
    }

    public T getContent() {
        return mContent;
    }

    public void setContent(T content) {
        mContent = content;
    }
}
