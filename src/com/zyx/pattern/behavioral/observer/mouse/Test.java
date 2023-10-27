package com.zyx.pattern.behavioral.observer.mouse;

public class Test {
    public static void main(String[] args) {
        EventListener listener = new MouseEventListener();
        Mouse m = new Mouse();
        m.addListener(MouseEventType.ON_CLICK,  listener);
        m.addListener(MouseEventType.ON_MOVE,  listener);
        
        m.click();
        
    }
}
