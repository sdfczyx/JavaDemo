package com.zyx.pattern.behavioral.state;

public class StartState implements State {

    public void start(Context context) {
        // do nothing
        System.out.println("already StartState");
    }

    public void close(Context context) {
        context.setState(new StopState());//注意状态的切换

        System.out.println("close State");
    }
}
