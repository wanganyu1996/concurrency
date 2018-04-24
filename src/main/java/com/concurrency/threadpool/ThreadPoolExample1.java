package com.concurrency.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wanganyu on 2018/04/24.
 */
@Slf4j
public class ThreadPoolExample1 {
    public static void main(String[] args){
        ExecutorService executorService= Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
               final int index=i;
               executorService.execute(new Runnable() {
                   @Override
                   public void run() {
                       System.out.println("tast:{}"+index);
                   }
               });
        }
        executorService.shutdown();
    }
}
