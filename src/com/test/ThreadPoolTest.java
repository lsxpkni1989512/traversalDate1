package com.test;

import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) {
        int i1 = Runtime.getRuntime().availableProcessors();
        System.out.println("本机线程数："+i1);
//        ThreadPoolExecutor
//        Executors.newScheduledThreadPool(3);//比如线程池设置时间参数  -Schedule调度（带时间）
//        Executors.newWorkStealingPool();//java8新增，使用目前机器上可用的处理器作为它的并行级别


        //重点
//        ExecutorService executorService = Executors.newFixedThreadPool(5);//一池固定数线程
//        ExecutorService executorService1 = Executors.newSingleThreadExecutor();//一池一线程
//        ExecutorService executorService2 = Executors.newCachedThreadPool();
        //一池多线程

        //手写线程池
        ExecutorService executorService= new ThreadPoolExecutor(
                1,
                80,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        try {
            for(int i = 1;i<=80;i++){

                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 处理事务");
                });
            }
//            try{ TimeUnit.SECONDS.sleep(2);}catch(Exception e){e.printStackTrace();}
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
