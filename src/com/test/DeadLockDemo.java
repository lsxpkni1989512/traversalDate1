package com.test;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{


    private String lockA;
    private String lockB;

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 持有锁"+lockA+" 尝试获取锁"+lockB);
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 持有锁"+lockB+" 尝试获取锁"+lockA);

            }
        }
    }
    public HoldLockThread(String lockA,String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {

//        String lockA = "lockA";
//        String lockB = "lockB";
//        new Thread(new HoldLockThread(lockA,lockB),"A").start();
//        new Thread(new HoldLockThread(lockB,lockA),"B").start();
        System.out.println("********");
        try{ TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);}catch(Exception e){e.printStackTrace();}


    }

}
