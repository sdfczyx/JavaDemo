package com.zyx.pattern.structural.proxydy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 定义一个处理器
 */
public class MyInvocationHandler implements InvocationHandler {
    /**
     * 因为需要处理真实角色，所以要把真实角色传进来
     */
    Subject realSubject ;

    public MyInvocationHandler(Subject realSubject) {
        this.realSubject = realSubject;
    }

    /**
     *
     * @param proxy    代理类
     * @param method    正在调用的方法
     * @param args      方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用代理类");
        if(method.getName().equals("sellBooks")){
            int returnVal = (int)method.invoke(realSubject, args);
            System.out.println("调用的是卖书的方法");
            return returnVal ;
        }else {
            String returnVal = (String) method.invoke(realSubject,args) ;
            System.out.println("调用的是说话的方法");
            return  returnVal ;
        }
    }
}
