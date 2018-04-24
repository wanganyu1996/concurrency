package com.concurrency.example.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wanganyu on 2018/04/25.
 */
public class CountDownLatchExample1 {
    private  final  static int threadCount=200;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec= Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch=new CountDownLatch(threadCount);
        for(int i=0;i<threadCount;i++){
            final int threadNum=i;
            exec.execute(()->{
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }finally {
                    countDownLatch.countDown();;
                }
            });
        }
        countDownLatch.await();
        System.out.println("finish");
        exec.shutdown();
    }
    public static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        System.out.println("{}"+threadNum);
        Thread.sleep(100);

    }
}
