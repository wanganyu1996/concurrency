package com.mmall.threadpool;

import com.mmall.concurrency.ThreadExcutors;

/**
 * Created by wanganyu on 2018/04/26.
 */
public class ThreadBlocked {
    public static void main(String[] args){
       ThreadExcutors excutor=new ThreadExcutors(3);
       for(int i=0;i<10;i++){
           excutor.exec(new Runnable() {
               @Override
               public void run() {
                   System.out.println("线程 "+Thread.currentThread().getName()+" 在帮我干活");
               }
           });
       }
        excutor.shutdown();
    }
}
