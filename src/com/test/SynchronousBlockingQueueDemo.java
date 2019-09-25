package com.test;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousBlockingQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        try {
            new Thread(()->{

                try {
                    System.out.println(Thread.currentThread().getName()+"\t 放1");
                    synchronousQueue.put(1);

                    System.out.println(Thread.currentThread().getName()+"\t  放2");
                    synchronousQueue.put(2);

                    System.out.println(Thread.currentThread().getName()+"\t  放3");
                    synchronousQueue.put(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"A").start();

            new Thread(()->{
                try {
                    try{ TimeUnit.SECONDS.sleep(5);}catch(Exception e){e.printStackTrace();}
                    System.out.println(Thread.currentThread().getName()+"\t 取1");
                    synchronousQueue.take();

                    try{ TimeUnit.SECONDS.sleep(5);}catch(Exception e){e.printStackTrace();}
                    System.out.println(Thread.currentThread().getName()+"\t 取2");
                    synchronousQueue.take();

                    try{ TimeUnit.SECONDS.sleep(5);}catch(Exception e){e.printStackTrace();}
                    System.out.println(Thread.currentThread().getName()+"\t 取3");
                    synchronousQueue.take();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {

                }

            },"B").start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
