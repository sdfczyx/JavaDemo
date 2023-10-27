package com.zyx.thread;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

    private static final Lock lock = new ReentrantLock();
    
    public static void main(String[] args) {
        
        lock.lock();//加锁
        try {
            for (char c : "abc".toCharArray()) {
                System.out.print(c);
            }
            System.out.println();
        } finally {
            lock.unlock();//释放锁
        }
    }
}