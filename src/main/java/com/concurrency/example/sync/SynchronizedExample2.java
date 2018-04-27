package com.concurrency.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wanganyu on 2018/04/26.
 */
public class SynchronizedExample2 {
    public static void test1(int j){
        //同步语句块 作用范围
        synchronized (SynchronizedExample2.class){
            for(int i=0;i<10;i++){
                System.out.println("test1  "+j+"-{}"+i);
            }
        }
    }
    //修饰一个方法
    public static synchronized  void test2(int j){
        for(int i=0;i<10;i++){
            System.out.println("test2-"+j+"  "+i);
        }
    }
    public static void main(String[] args){
       SynchronizedExample2 example1=new SynchronizedExample2();
        SynchronizedExample2 example2=new SynchronizedExample2();
        ExecutorService executorService= Executors.newCachedThreadPool();
        executorService.execute(()->{
             example1.test1(1);
        });
        executorService.execute(()->{
            example2.test1(2);
        });
    }
}
