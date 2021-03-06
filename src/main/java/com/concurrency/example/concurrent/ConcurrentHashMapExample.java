package com.concurrency.example.concurrent;

import com.concurrency.annoations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by wanganyu on 2018/05/07.
 */
@ThreadSafe
public class ConcurrentHashMapExample {
    //请求总数
    public static  int clientTotal=5000;
    //同时并发执行的线程数
    public static int threadTotal=200;
    private static Map<Integer,Integer> map=new ConcurrentHashMap<>();
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
