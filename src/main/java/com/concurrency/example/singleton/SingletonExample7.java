package com.concurrency.example.singleton;

import com.concurrency.annoations.Recommend;
import com.concurrency.annoations.ThreadSafe;

/**
 * Created by wanganyu on 2018/04/30.
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {
    private SingletonExample7(){

    }
    //单例对象
    private static SingletonExample7 instance=null;
    static {
        instance=new SingletonExample7();
    }

   //静态的工厂方法
    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }
   private enum Singleton{
        INSTANCE;
        private SingletonExample7 singleton;
        //JVM保证只被调用一次
        Singleton(){
            singleton=new SingletonExample7();
        }
        public SingletonExample7 getInstance(){
            return singleton;
        }
   }
}
