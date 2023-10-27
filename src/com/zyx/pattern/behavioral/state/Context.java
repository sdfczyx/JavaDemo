package com.zyx.pattern.behavioral.state;

public class Context {
   private State state;

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void start() {
        if (getState() instanceof StopState) {
            getState().start(this);
            setState(new StartState());
        }else {
            System.out.println("already start");
        }
    }

    public void close() {
        if (getState() instanceof StartState) {
            getState().close(this);
            setState(new StopState());
        }else {
            System.out.println("already stop");
        }
    }

}
