package com.concurrency.example.commonUnsafe;

import com.concurrency.annoations.ThreadSafe;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by wanganyu on 2018/05/07.
 */
@ThreadSafe
public class HashTableExample {
    //请求总数
    public static  int clientTotal=5000;
    //同时并发执行的线程数
    public static int threadTotal=200;
    private static Hashtable<Integer,Integer> map=new Hashtable<>();
    public static void main(String[] args) throws Exception{
        ExecutorService executorService= Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(threadTotal);
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            final int count=i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update(count);
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
        System.out.println("size:"+map.size());

    }
    private static void update(int i){
        map.put(i,i);
    }
}
