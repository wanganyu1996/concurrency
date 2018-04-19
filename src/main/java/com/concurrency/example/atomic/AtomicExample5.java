package com.concurrency.example.atomic;

import com.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;


/**
 * Created by wanganyu on 2018/04/17.
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater=AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");
    public volatile int count=100;

    public int getCount() {
        return count;
    }

    public static void main(String[] args){
        AtomicExample5 example5=new AtomicExample5();
      if(updater.compareAndSet(example5,100,120)){
            System.out.println("update success: "+example5.getCount());
        }
        if(updater.compareAndSet(example5,100,120)){
            System.out.println("update success: "+example5.getCount());
        }else{
          System.out.println("update success: "+example5.getCount());
      }
    }
}
