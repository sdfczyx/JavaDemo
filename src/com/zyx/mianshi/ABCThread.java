package com.zyx.mianshi;

class Opt {
    //下一个执行的线程编号
    private Integer nextIndex;

    public Integer getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(Integer nextIndex) {
        this.nextIndex = nextIndex;
    }
}


public class ABCThread extends Thread {
    String [] ABC = new String[]{"A","B","C"};
    //当前线程编号
    private Integer index;
    //线程间通信的对象
    private Opt opt;

    //统计当前线程打印次数
    int num = 0;

    public ABCThread(Integer index,Opt opt) {
        this.index = index;
        this.opt = opt;
    }

    @Override
    public void run() {
        while (true) {
            //锁通信的对象
            synchronized (opt) {
                //循环执行 判断是否是当前线程执行 （当先线程编号和opt记录的线程编号一致则是要执行的线程）
                //需要循环操作 A ->B和C
                while (opt.getNextIndex() != index) {
                    try {
                        //不是当前线程执行，继续等待
                        opt.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.print(ABC[index] +" ");
                //统计次数+1
                num++;

                //设置下一个执行的线程编号 A：（0 -1） ->B(1 ->2) ->C(2-0)
                opt.setNextIndex((index+1)%3);

                //通知其他的两个线程
                opt.notifyAll();

                //当前执行到达10次，执行结束
                if (num > 9) break;
            }
        }
    }


 public static void main(String[] args) {
        Opt opt = new Opt();
        //设置第一个执行的线程编号
        opt.setNextIndex(0);
        new ABCThread(0,opt).start();//A线程
        new ABCThread(1,opt).start();//B线程
        new ABCThread(2,opt).start();//C线程
    }

}
