package com.zyx.pattern.behavioral.state;

public interface State {

    public void start(Context context);
    public void close(Context context);
}
