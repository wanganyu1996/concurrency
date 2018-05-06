package com.concurrency.example.singleton;

import com.concurrency.annoations.ThreadSafe;

/**
 * Created by wanganyu on 2018/04/30.
 */
@ThreadSafe
public class SingletonExample5 {
    private SingletonExample5(){

    }
    //单例对象
    private static SingletonExample5 instance=null;
   //静态的工厂方法
    public static SingletonExample5 getInstance(){
        if(instance==null){
            synchronized (SingletonExample5.class){
                if(instance==null){
                    instance=new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
