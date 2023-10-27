package com.zyx.pattern.behavioral.state;

public class StopState implements State {
 
   public void start(Context context) {
        context.setState(new StartState());//注意状态的切换
        System.out.println("start State");
    }

    public void close(Context context) {
        // do nothing
        System.out.println("already StopState");
    }

}
