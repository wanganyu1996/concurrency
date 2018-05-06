package com.concurrency.example.singleton;

/**
 * Created by wanganyu on 2018/04/30.
 */
public class SingletonExample1 {
    private SingletonExample1(){

    }
    //单例对象
    private static SingletonExample1 instance=null;
   //静态的工厂方法
    public static SingletonExample1 getInstance(){
        if(instance==null){
            instance=new SingletonExample1();
        }
        return instance;
    }
}
