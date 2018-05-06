package com.concurrency.example.singleton;

import com.concurrency.annoations.ThreadSafe;

/**
 * Created by wanganyu on 2018/04/30.
 */
@ThreadSafe
public class SingletonExample4 {
    private SingletonExample4(){

    }
    //单例对象
    private volatile static SingletonExample4 instance=null;
   //静态的工厂方法
    public static   SingletonExample4 getInstance(){
        if(instance==null){
            synchronized (SingletonExample4.class){
                if(instance==null){
                    instance=new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
