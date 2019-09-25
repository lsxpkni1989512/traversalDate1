package com.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTests {

    public static void main(String[] args) {

        Tickets tickets = new Tickets();

        new Thread(tickets,"1号窗口").start();
        new Thread(tickets,"2号窗口").start();
        new Thread(tickets,"3号窗口").start();
    }

    static class Tickets implements Runnable{

        private Lock lock = new ReentrantLock(true);
        private int tick = 100;
        @Override
        public void run() {
            //case1:
//            while (tick > 0){
//                System.out.println(Thread.currentThread().getName() + " 完成售票，余票为："+ --tick);
//            }

            //case2:
//            while (true){
//                if(tick > 0){
//                    try {
//                        Thread.sleep(200);
//                    }catch (Exception e){
//
//                    }
//                    System.out.println(Thread.currentThread().getName() + " 完成售票，余票为："+ --tick);
//                }
//            }
            /**
             * case2 出现问题
             * 1号窗口 完成售票，余票为：90
             * 2号窗口 完成售票，余票为：90
             * 3号窗口 完成售票，余票为：90
             */

            /**
             * 解决case2问题：加锁
             * 3号窗口 完成售票，余票为：2
             * 3号窗口 完成售票，余票为：1
             * 3号窗口 完成售票，余票为：0
             */
            while (true){
                lock.lock();//上锁
                try {
                    if(tick > 0){
                        try {
                            Thread.sleep(200);
                        }catch (Exception e){

                        }
                        System.out.println(Thread.currentThread().getName() + " 完成售票，余票为："+ --tick);
                    }else{
                        break;
                    }

                }finally {
                    lock.unlock();//解锁
                }

            }
        }
    }
}
