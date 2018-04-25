package com.concurrency.example.aqs;

import java.util.concurrent.*;

/**
 * Created by wanganyu on 2018/04/25.
 */
public class CyclicBarrierExample3 {
    private static CyclicBarrier barrier=new CyclicBarrier(5,()->{
        System.out.println("callback is running");
    });
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
        executor.shutdown();
    }
    private static void race(int threadNum) throws Exception{
       Thread.sleep(1000);
        System.out.println("{} is ready"+threadNum);
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("{} is continue"+threadNum);
    }
}
