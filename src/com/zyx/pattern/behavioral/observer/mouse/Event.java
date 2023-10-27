package com.zyx.pattern.behavioral.observer.mouse;

import java.lang.reflect.Method;

public class Event {
    // 事件源
    private Object source;
    // 观察者
    private EventListener target;
    // 回调函数
    private Method callback;
    // 事件名称
    private String eventName;
    // 触发事件
    private long time;
    //
    private String trigger;
    
    public Event(EventListener target, Method callback) {
        this.target = target;
        this.callback = callback;
    }
    public Object getSource() {
        return source;
    }
    public Event setSource(Object source) {
        this.source = source;
        return this;
    }
    public EventListener getTarget() {
        return target;
    }
    public Event setTarget(EventListener target) {
        this.target = target;
        return this;
    }
    public Method getCallback() {
        return callback;
    }
    public Event setCallback(Method callback) {
        this.callback = callback;
        return this;
    }
    public String getEventName() {
        return eventName;
    }
    public Event setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }
    public long getTime() {
        return time;
    }
    public Event setTime(long time) {
        this.time = time;
        return this;
    }
    
    public String getTrigger() {
        return trigger;
    }
    public Event setTrigger(String trigger) {
        this.trigger = trigger;
        return this;
    }
    @Override
    public String toString() {
        return "Event [source=" + source + ", target=" + target + ", callback=" + callback + ", eventName=" + eventName
                + ", time=" + time + ", trigger=" + trigger + "]";
    }
    
}