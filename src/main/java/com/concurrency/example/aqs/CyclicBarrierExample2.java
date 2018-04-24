package com.concurrency.example.aqs;

import java.util.concurrent.*;

/**
 * Created by wanganyu on 2018/04/25.
 */
public class CyclicBarrierExample2 {
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
        executor.shutdown();
    }
    private static void race(int threadNum) throws Exception{
       Thread.sleep(1000);
        System.out.println("{} is ready"+threadNum);
        try {
            barrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("{} is continue"+threadNum);
    }
}
