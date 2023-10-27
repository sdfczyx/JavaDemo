package com.zyx.thread;

import java.util.concurrent.locks.LockSupport;

import org.junit.Test;

/*
 在没有LockSupport之前，线程的挂起和唤醒咱们都是通过Object的wait和notify/notifyAll方法实现。
 但是在有LockSupport之后，线程的挂起和唤醒都是通过synchronized方法实现。    
 
 */
public class TestLockSupport {
    @Test
    // 之前的方式
    public void test1() throws Exception {
        final Object obj = new Object();
        Thread A = new Thread(() -> {
            int sum = 0;
            for (int i = 0; i <= 10; i++) {
                sum += i;
            }
            try {
                synchronized (obj) {
                    obj.wait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(sum);
        });
        A.start();
        //睡眠一秒钟，保证线程A已经计算完成，阻塞在wait方法
        Thread.sleep(1000);
        synchronized (obj) {
            obj.notify();
        }
    }
    
    @Test
    // 使用了 LockSupport
    public void test2() throws Exception {
        Thread A = new Thread(() -> {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += i;
            }
            LockSupport.park();
            System.out.println(sum);
        });
        A.start();
        //睡眠一秒钟，保证线程A已经计算完成，阻塞在wait方法
        Thread.sleep(1000);
        LockSupport.unpark(A);
    }
    /* 总结
     * 如果去掉Thread.sleep()调用
     * 老的方式可能会 主线程调用完notify后，线程A才进入wait方法，导致线程A一直阻塞住
     * LockSupport就支持主线程先调用unpark后，线程A再调用park而不被阻塞
     * 
     * LockSupport不需要在同步代码块里 。所以线程间也不需要维护一个共享的同步对象了，实现了线程间的解耦。
     * unpark函数可以先于park调用，所以不需要担心线程间的执行的先后顺序
     * 多次调用unpark方法和调用一次unpark方法效果一样，因为都是直接将_counter赋值为1，而不是加1。
     * 简单说就是：线程A连续调用两次LockSupport.unpark(B)方法唤醒线程B，然后线程B调用两次LockSupport.park()方法， 
     * 线程B依旧会被阻塞。因为两次unpark调用效果跟一次调用一样，只能让线程B的第一次调用park方法不被阻塞，第二次调用依旧会阻塞。
    */
        

}
