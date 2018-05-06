package com.concurrency.example.singleton;

import com.concurrency.annoations.ThreadSafe;

/**
 * Created by wanganyu on 2018/04/30.
 */
@ThreadSafe
public class SingletonExample2 {
    private SingletonExample2(){

    }
    //单例对象
    private static SingletonExample2 instance=new SingletonExample2();
   //静态的工厂方法
    public static SingletonExample2 getInstance(){
        return instance;
    }
}
