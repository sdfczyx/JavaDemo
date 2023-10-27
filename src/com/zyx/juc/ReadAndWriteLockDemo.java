package com.zyx.juc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.sleep;

/*
 * @author wengyz
 * @version ReadAndWriteLockDemo.java, v 0.1 2020-05-05 15:15
 */
public class ReadAndWriteLockDemo {
    public static void main(String[] args) {
        // 读写锁
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        // 读锁
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        // 写锁
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        /**
         * 写锁一
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                writeLock.lock();
                System.out.println("写锁一加锁成功");
                System.out.println("---写锁一释放成功");
                writeLock.unlock();
            }
        }).start();

        /**
         * 读锁一
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                readLock.lock();
                System.out.println("读锁一加锁成功");
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("---读锁一释放成功");
                readLock.unlock();
            }
        }).start();

        /**
         * 读锁二
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                readLock.lock();
                System.out.println("读锁二加锁成功");
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("---读锁二释放成功");
                readLock.unlock();
            }
        }).start();

        /**
         * 写锁二
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                writeLock.lock();
                System.out.println("写锁二加锁成功");
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("---写锁二释放成功");
                writeLock.unlock();
            }
        }).start();
    }
}