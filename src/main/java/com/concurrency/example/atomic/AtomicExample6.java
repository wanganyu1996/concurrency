package com.concurrency.example.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by wanganyu on 2018/04/20.
 */
public class AtomicExample6 {
    private static AtomicBoolean isHappened=new AtomicBoolean(false);
    //请求总数
    public static  int clientTotal=5000;
    //同时并发执行的线程数
    public static int threadTotal=200;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(threadTotal);
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    System.out.println("exception"+e.getMessage());
                    //log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("isHappened: "+isHappened.get());
    }
    private static void test(){
    if(isHappened.compareAndSet(false,true)){
        System.out.println("execute");
    }
    }
}
