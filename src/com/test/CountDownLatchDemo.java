package com.test;

import java.util.concurrent.CountDownLatch;

//倒计时器
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i =1;i<=6;i++){
            new Thread(()->{

                System.out.println(Thread.currentThread().getName()+"\t 加班结束 离开办公室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 最后的人锁门 离开办公室");
    }
}
