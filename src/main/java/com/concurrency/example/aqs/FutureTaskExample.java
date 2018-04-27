package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Created by wanganyu on 2018/04/26.
 */
@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask=new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("do something in callable");
                Thread.sleep(5000);
                return "Done";
            }
        });
        new Thread(futureTask).start();
        System.out.println("do something in main");
        Thread.sleep(1000);
        String result=futureTask.get();
        System.out.println("result{}"+result);
    }
}
