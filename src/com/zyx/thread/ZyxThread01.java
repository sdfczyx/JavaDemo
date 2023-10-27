package com.zyx.thread;

public class ZyxThread01 extends Thread{
    
    public static void main(String[] args){

        // 主线程
        for(int i=0; i<100; i++){
            new ZyxThread01().start();
        }
        System.out.println("主线程：" + Thread.currentThread().getName());
        
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running");
    }
    
}
