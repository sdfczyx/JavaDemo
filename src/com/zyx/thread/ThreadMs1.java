package com.zyx.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 面试题, 两个线程交替打印ab, 共打印100次
 */
public class ThreadMs1 {
    public static ReentrantLock lock = new ReentrantLock();
    
    public static void main(String[] args) throws InterruptedException {
        test4();
    }
    
    public static void test1() {
        Thread[] threads = new Thread[2];
        threads[0] = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                System.out.print("a");
                LockSupport.unpark(threads[1]);
                LockSupport.park();
            }
        });
        
        threads[1] = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                LockSupport.park();
                System.out.print("b");
                LockSupport.unpark(threads[0]);
            }
        });
        threads[0].start();
        threads[1].start();
        
        
    }
    
    public static void test2() {
        PrintObject p = new PrintObject(1, 50);
        new Thread(() ->{
            p.print("A", 1, 2);
        }).start();
        new Thread(() ->{
            p.print("B", 2, 1);
        }).start();
        
    }
    
    public static void test3() {
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        
        Thread a = new Thread(() ->{
            lock.lock();
            for (int i = 0; i < 50; i++) {
                System.out.print("a");
                c1.signal();
                try {
                    c2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();
        });
        Thread b = new Thread(() ->{
            lock.lock();
            a.start();
            for (int i = 0; i < 50; i++) {
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("b");
                c2.signal();
            }
            lock.unlock();
        });
        b.start();
    }
    
    public static void test4() {
        CyclicBarrier barrier = new CyclicBarrier(2);
        AtomicInteger counter = new AtomicInteger();
        CompletableFuture.runAsync(() ->{
            try {
                while(counter.incrementAndGet()<=100){
                    System.out.print("a");
                    barrier.await();
                    barrier.await();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        CompletableFuture.runAsync(() ->{
            try {
                while(counter.incrementAndGet()<=100){
                    barrier.await();
                    System.out.print("b");
                    barrier.await();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        
        try {
            Thread.currentThread().sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
}

class PrintObject{
    private int flag;
    private final int loopnum;
    
    public PrintObject(int flag, int loopnum) {
        this.loopnum = loopnum;
        this.flag = flag;
    }
    public void print(String str, int currFlag, int nextFlag) {
        for (int i = 0; i < loopnum; i++) {
            synchronized (this) {
                while(flag != currFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(str);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }
    
}