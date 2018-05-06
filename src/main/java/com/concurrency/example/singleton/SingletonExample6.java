package com.concurrency.example.singleton;

import com.concurrency.annoations.ThreadSafe;

/**
 * Created by wanganyu on 2018/04/30.
 */
@ThreadSafe
public class SingletonExample6 {
    private SingletonExample6(){

    }
    //单例对象
    private static SingletonExample6 instance=null;
    static {
        instance=new SingletonExample6();
    }

   //静态的工厂方法
    public static SingletonExample6 getInstance(){
        return instance;
    }
    public static void main(String[] args){
        System.out.println(getInstance());
        System.out.println(getInstance());
    }
}
