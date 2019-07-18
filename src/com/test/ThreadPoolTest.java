package com.test;

import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) {
//        ThreadPoolExecutor
        Executors.newScheduledThreadPool(3);//比如线程池设置时间参数  -Schedule调度（带时间）
        Executors.newWorkStealingPool();//java8新增，使用目前机器上可用的处理器作为它的并行级别


        //重点
//        ExecutorService executorService = Executors.newFixedThreadPool(5);//一池固定数线程
//        ExecutorService executorService1 = Executors.newSingleThreadExecutor();//一池一线程
//        ExecutorService executorService2 = Executors.newCachedThreadPool();
        //一池多线程

        //手写线程池
        ExecutorService executorService= new ThreadPoolExecutor(
                3,
                5,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        try {
            for(int i = 1;i<=12;i++){

                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 处理事务");
                });
            }
            try{ TimeUnit.SECONDS.sleep(2);}catch(Exception e){e.printStackTrace();}
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
//            executorService1.shutdown();
//            executorService2.shutdown();

        }

//        try {
//            System.out.println(Thread.currentThread().getName());
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//
//        }
//        try{ TimeUnit.SECONDS.sleep(5);}catch(Exception e){e.printStackTrace();}

    }
}
