package com.concurrency.example.singleton;

import com.concurrency.annoations.ThreadSafe;

/**
 * Created by wanganyu on 2018/04/30.
 */
@ThreadSafe
public class SingletonExample3 {
    private SingletonExample3(){

    }
    //单例对象
    private static SingletonExample3 instance=new SingletonExample3();
   //静态的工厂方法
    public static  synchronized SingletonExample3 getInstance(){
        return instance;
    }
}
