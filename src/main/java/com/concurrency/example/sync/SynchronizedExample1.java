package com.concurrency.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wanganyu on 2018/04/26.
 */
public class SynchronizedExample1 {
    public void test1(int j){
        //同步语句块 作用范围
        synchronized (this){
            for(int i=0;i<10;i++){
                System.out.println("test  "+j+"-{}"+i);
            }
        }
    }
    //修饰一个方法
    public synchronized  void test2(int j){
        for(int i=0;i<10;i++){
            System.out.println("test-"+j+"  "+i);
        }
    }
    public static void main(String[] args){
       SynchronizedExample1 example1=new SynchronizedExample1();
        SynchronizedExample1 example2=new SynchronizedExample1();
        ExecutorService executorService= Executors.newCachedThreadPool();
        executorService.execute(()->{
             example1.test2(1);
        });
        executorService.execute(()->{
            example2.test2(2);
        });
    }
}
