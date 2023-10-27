package com.zyx.pattern.behavioral.observer.mouse;

public class MouseEventListener implements EventListener{
    public void onClick(Event event){
        System.out.println("鼠标单击事件:" + event);
    }
        
}
