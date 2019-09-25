package com.test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CallableDemo {
    public static void main(String[] args) throws Exception{
//        Callable
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask).start(); //FutureTask适配了Runnable 和 Callable两种接口
        int i = 100000;
        int j = futureTask.get();
        System.out.println(Thread.currentThread().getName()+"\t 结果："+(i+j));
    }
}
class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() +" come in Callable");
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
        }
        return 1024;
    }
}
