package com.concurrency.example.commonUnsafe;

import com.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by wanganyu on 2018/05/06.
 */
@Slf4j
@NotThreadSafe
public class DateFormatExample3 {

    //请求总数
    public static  int clientTotal=5000;
    //同时并发执行的线程数
    public static int threadTotal=200;
    public static void main(String[] args) throws Exception{
        ExecutorService executorService= Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(threadTotal);
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update();
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

    }
    private static void update(){
        try {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.parse("20180506");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
