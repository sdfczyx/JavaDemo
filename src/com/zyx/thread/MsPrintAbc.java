package com.zyx.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Consumer;

public class MsPrintAbc {
    
    public static void main(String[] agrs) throws InterruptedException {

        MsPrintAbc lockSupportMain = new MsPrintAbc();

        //定义线程t1、t2、t3执行的函数方法
        Consumer<String> consumer = str -> {
            while (true) {
                //线程消费许可证，并传入blocker，方便后续排查问题
                LockSupport.park(lockSupportMain);
                //防止线程是因中断操作唤醒
                if (Thread.currentThread().isInterrupted()){
                    throw new RuntimeException("线程被中断，异常结束");
                }
                System.out.println(Thread.currentThread().getName() + ":" + str);
            }
        };

        /**
         * 定义分别输出A、B、C的线程
         */
        Thread t1 = new Thread(() -> {
            consumer.accept("A");
        },"T1");
        Thread t2 = new Thread(() -> {
            consumer.accept("B");
        },"T2");
        Thread t3 = new Thread(() -> {
            consumer.accept("C");
        },"T3");


        /**
         * 定义调度线程
         */
        Thread dispatch = new Thread(() -> {
            int i=0;
            try {
                while (true) {
                    if((i%3)==0) {
                        //线程t1设置许可证，并唤醒线程t1
                        LockSupport.unpark(t1);
                    }else if((i%3)==1) {
                        //线程t2设置许可证，并唤醒线程t2
                        LockSupport.unpark(t2);
                    }else {
                        //线程t3设置许可证，并唤醒线程t3
                        LockSupport.unpark(t3);
                    }
                    i++;
                    TimeUnit.MILLISECONDS.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //启动相关线程
        t1.start();
        t2.start();
        t3.start();
        dispatch.start();

    }

    
}
