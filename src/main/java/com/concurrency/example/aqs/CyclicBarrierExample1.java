package com.concurrency.example.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wanganyu on 2018/04/25.
 */
public class CyclicBarrierExample1 {
    private static CyclicBarrier barrier=new CyclicBarrier(5);
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor= Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            final int threadNum=i;
            Thread.sleep(1000);
            executor.execute(()->{
                try {
                    race(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }
    }
    private static void race(int threadNum) throws Exception{
       Thread.sleep(1000);
        System.out.println("{} is ready"+threadNum);
        barrier.await();
        System.out.println("{} is continue"+threadNum);
    }
}
