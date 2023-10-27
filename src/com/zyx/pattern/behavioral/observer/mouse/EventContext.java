package com.zyx.pattern.behavioral.observer.mouse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class EventContext {
    protected Map<String, Event> events = new HashMap<>();
    
    public void addListener(String eventType, EventListener target, Method callback) {
        // 添加监听器
        events.put(eventType, new Event(target, callback));
    }
    public void addListener(String eventType, EventListener target) {
        // 添加监听器
        try {
            this.addListener(eventType, target, target.getClass().getMethod("on" + capitalize(eventType), Event.class));
        } catch (NoSuchMethodException | SecurityException e) {
            return;
        }
        
    }
    
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        
        char[] cs=str.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);

    }
    
    public void trigger(Event event){
        event.setSource(this).setTime(System.currentTimeMillis());
        if(event.getCallback()!=null){
            try {
                event.getCallback().invoke(event.getTarget(), event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
            
    }
    
    public void trigger(String eventType){
        if (!this.events.containsKey(eventType)) { return; }
        trigger(this.events.get(eventType).setTrigger(eventType));
    }

}
