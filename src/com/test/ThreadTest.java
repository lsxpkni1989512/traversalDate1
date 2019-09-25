package com.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {
    private volatile static int count = 0;

    public static Lock lock = new ReentrantLock();//lock 显示锁
    public static void t1(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //每个线程让count自增100次
                        for (int j = 0; j < 100; j++) {
                            add();
                        }
                    }finally {

                    }

                }
            }).start();
        }

        try{
            Thread.sleep(6000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"  最终count："+count);
    }
    public static synchronized void add(){ //synchronized 隐式锁  同步方法
//        lock.lock();
        try{
            count++;
            System.out.println(Thread.currentThread().getName()+"  count:"+count);
            Thread.sleep(10);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            lock.unlock();
        }
//        System.out.println(Thread.currentThread().getName()+"  count:"+count);
    }

    /**
     * case1:add()不加syncronized
     * 出现问题：
     * Thread-0  count:170
     * Thread-1  count:170
     * Thread-0  count:171
     * Thread-1  count:172
     * Thread-0  count:173
     * Thread-1  count:174
     * main  最终count：174
     */

    /**
     * case2:add()加sync
     * Thread-1  count:194
     * Thread-1  count:195
     * Thread-1  count:196
     * Thread-1  count:197
     * Thread-1  count:198
     * Thread-0  count:199
     * Thread-0  count:200
     * main  最终count：200
     */

    /**
     * case3:
     */



}
