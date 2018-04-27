package com.mmall.threadpool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wanganyu on 2018/04/26.
 */
public class ThreadExcutor {
    private volatile boolean RUNNING=true;
    //阻塞队列
    private static BlockingQueue<Runnable> queue=null;
    private final HashSet<Worker> workers=new HashSet<Worker>();
    private final ArrayList<Thread> threadList=new ArrayList<Thread>();

    int poolSize=0;
    int coreSize=0;
    boolean shutdown=false;
    public ThreadExcutor(int poolSize){
        this.poolSize=poolSize;
        queue=new LinkedBlockingQueue<Runnable>(poolSize);
    }
    public void exec(Runnable runnable){
        if(runnable==null) throw new NullPointerException();
        if(coreSize<poolSize){
           addThread(runnable);
        }else{
                try {
                    queue.put(runnable);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }

    }
    public void addThread(Runnable runnable){
        coreSize++;
        Worker worker=new Worker(runnable);
        workers.add(worker);
        Thread t=new Thread(worker);
        threadList.add(t);
        t.start();
    }
    public void shutdown(){
        RUNNING=false;
        if(!workers.isEmpty()){
            for(Worker worker:workers){
                worker.interruptIfIdle();
            }
        }
        shutdown=true;
        Thread.currentThread().interrupt();
    }
    class Worker implements Runnable{

        public Worker(Runnable runnable){
            queue.offer(runnable);
        }
        @Override
        public void run() {
          while(true&&RUNNING){
              if(shutdown==true){
                  Thread.interrupted();
              }
              Runnable task=null;
              try {
                  task=getTask();
                  task.run();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }

          }
        }
        public Runnable getTask() throws InterruptedException {
            return queue.take();
        }
        public void interruptIfIdle(){
            for(Thread thread:threadList){
                System.out.println(thread.getName()+" interrupt");
                thread.interrupt();
            }
        }
    }
}
