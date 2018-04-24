package com.concurrency.example.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by wanganyu on 2018/04/25.
 */
public class SemaphoreExample1 {
    private  final  static int threadCount=20;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec= Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(3);
        for(int i=0;i<threadCount;i++){
            final int threadNum=i;
            exec.execute(()->{
                try {
                    if(semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)){//尝试获取一个许可
                        test(threadNum);
                        semaphore.release(3);//释放许可
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }finally {
                }
            });
        }
      //  System.out.println("finish");
        exec.shutdown();
    }
    public static void test(int threadNum) throws InterruptedException {
        System.out.println("{}"+System.currentTimeMillis()+" "+threadNum);
        Thread.sleep(1000);

    }
}
