package com.zyx.pattern.behavioral.observer.mouse;

public class Mouse extends EventContext{
    public void click(){
        System.out.println("鼠标单击事件");
        this.trigger(MouseEventType.ON_CLICK);
    }
    
}
