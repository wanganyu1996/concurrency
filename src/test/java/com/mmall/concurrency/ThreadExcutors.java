package com.mmall.concurrency;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadExcutors {
   private volatile boolean RUNNING=true;  
   //阻塞队列  
   private static BlockingQueue<Runnable> queue=null;
   //对于一个final变量，如果是基本数据类型的变量，则其数值一旦在初始化之后便不能更改；如果是引用类型的变量，则在对其初始化之后便不能再让其指向另一个对象。  
   private final HashSet<Worker> workers = new HashSet<Worker>();
     
   private final ArrayList<Thread> threadList = new ArrayList<Thread>();
     
     
   int poolSize = 0;  
     
   int coreSize = 0;  
  
   boolean shutdown = false;  
     
   //构造函数,  
   public ThreadExcutors(int poolSize){
       this.poolSize = poolSize;  
       queue = new LinkedBlockingQueue<Runnable>(poolSize);
   }  
     
   public void exec(Runnable runnable) {  
       if (runnable == null) throw new NullPointerException();  
       if(coreSize < poolSize){           
           addThread(runnable);                  
       }else{  
           //System.out.println("offer" +  runnable.toString() + "   " + queue.size());  
           try {  
               queue.put(runnable);                //coreSize>poolSzie 加入阻塞队列中去  
           } catch (InterruptedException e) {  
               e.printStackTrace();  
           }  
       }  
   }  
     
   public void addThread(Runnable runnable){  
       coreSize ++;                                 //正在工作的线程+1  
       Worker worker = new Worker(runnable);        //  
       workers.add(worker);                           
       Thread t = new Thread(worker);                
       threadList.add(t);                            
       try {  
           t.start();  
       }catch (Exception e){  
           e.printStackTrace();  
       }  
  
   }  
  
   public void shutdown() {  
       RUNNING = false;  
       if(!workers.isEmpty()){  
           for (Worker worker : workers){  
               worker.interruptIfIdle();  
           }  
       }  
       shutdown = true;  
       Thread.currentThread().interrupt();  
   }  
      
   class  Worker implements Runnable{  
  
       public Worker(Runnable runnable){  
           queue.offer(runnable);                  //将这个runable将入到队列中去  
       }  
  
       @Override  
       public void run() {  
           while (true && RUNNING){  
               if(shutdown == true){  
                   Thread.interrupted();  
               }  
               Runnable task = null;  
               try {  
                   task = getTask();  
                   task.run();  
               } catch (InterruptedException e) {  
                   e.printStackTrace();  
               }  
           }  
       }  
  
       public Runnable getTask() throws InterruptedException {  
           return queue.take();                     
       }  
  
       public void interruptIfIdle() {  
           for (Thread thread :threadList) {  
               System.out.println(thread.getName() + " interrupt");  
               thread.interrupt();  
           }  
       }  
      
}}  