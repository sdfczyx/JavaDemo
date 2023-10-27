package com.zyx.io;

import java.nio.ByteBuffer;

public class TestBuffer {

    public static void test1() {
        String str = "abcde";

        //分配一个指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println("---------allocate-----------");
        System.out.println(byteBuffer.capacity());   //1024
        System.out.println(byteBuffer.limit());      //1024
        System.out.println(byteBuffer.position());   //0

        //利用 put() 存入数据到缓冲区中
        byteBuffer.put(str.getBytes());
        System.out.println("---------put-----------");
        System.out.println(byteBuffer.capacity());   //1024
        System.out.println(byteBuffer.limit());      //1024
        System.out.println(byteBuffer.position());   //5

        //切换到读数据模式
        byteBuffer.flip();
        System.out.println("---------flip-----------");
        System.out.println(byteBuffer.capacity());   //1024
        System.out.println(byteBuffer.limit());      //5,limit 表示可以操作数据的大小,只有 5 个字节的数据给你读,所以可操作数据大小是 5
        System.out.println(byteBuffer.position());   //0,读数据要从第 0 个位置开始读

        //利用 get() 读取缓冲区中的数据
        byte[] dst = new byte[byteBuffer.limit()];
        byteBuffer.get(dst);
        System.out.println(new String(dst,0,dst.length));
        System.out.println("---------get-----------");
        System.out.println(byteBuffer.capacity());   //1024
        System.out.println(byteBuffer.limit());      //5,可以读取数据的大小依然是 5 个
        System.out.println(byteBuffer.position());   //5,读完之后位置变到了第 5 个

        //rewind() 可重复读
        byteBuffer.rewind();         //这个方法调用完后,又变成了读模式
        System.out.println("---------rewind-----------");
        System.out.println(byteBuffer.capacity());   //1024
        System.out.println(byteBuffer.limit());      //5
        System.out.println(byteBuffer.position());  //0

        //clear() 清空缓冲区,虽然缓冲区被清空了，但是缓冲区中的数据依然存在，只是出于"被遗忘"状态。意思其实是，缓冲区中的界限、位置等信息都被置为最初的状态了，所以你无法再根据这些信息找到原来的数据了，原来数据就出于"被遗忘"状态
        byteBuffer.clear();
        System.out.println("---------clear-----------");
        System.out.println(byteBuffer.capacity());   //1024
        System.out.println(byteBuffer.limit());      //1024
        System.out.println(byteBuffer.position());  //0
    }
  
   public static void test2() {
        String str = "abcde";
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());
        byteBuffer.flip();
        byte[] bytearray = new byte[byteBuffer.limit()];
        byteBuffer.get(bytearray,0,2);
        System.out.println(new String(bytearray,0,2));  //结果是 ab
        System.out.println(byteBuffer.position());   //结果是 2
        //标记一下当前 position 的位置
        byteBuffer.mark();
        byteBuffer.get(bytearray,2,2);
        System.out.println(new String(bytearray,2,2));
        System.out.println(byteBuffer.position());   //结果是 4
        //reset() 恢复到 mark 的位置
        byteBuffer.reset();
        System.out.println(byteBuffer.position());   //结果是 2

        //判断缓冲区中是否还有剩余数据
        if (byteBuffer.hasRemaining()) {
            //获取缓冲区中可以操作的数量
            System.out.println(byteBuffer.remaining());  //结果是 3,上面 position 是从 2 开始的
        }
    }

    public static void main(String[] args) {
        test1();
//        test2();

    }
}