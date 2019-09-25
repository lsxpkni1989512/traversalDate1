package com.test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lsx on 2019/2/19.
 */
class MyData {
//        int  num = 0;//没有加volatile修饰的，没有可见性
    volatile int num = 0;//加volatile修饰的，具有可见性

    AtomicInteger atomicInteger = new AtomicInteger();//保证原子性

    public  void addTo60() {
        num = 60;
    }
    public synchronized int getNum(){
        return this.num;
    }

    public void addPlusPlus() {
        num++;
    }

    public void addPlusPlus_atomic() {
        atomicInteger.getAndIncrement();
    }
}

public class VolatileTest {
    public static void main1(String[] args) {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
//                    myData.addPlusPlus_atomic();
                }
            }, String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t" + myData.atomicInteger.get());
//        System.out.println(Thread.currentThread().getName() + "\t" + myData.num);
    }

    //可见性
    public static void main2(String[] args) {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t addTo60 task invoked......");

        }, "A").start();

        while (myData.getNum() == 0) {

        }
        System.out.println(Thread.currentThread().getName() + "\t task over...");
    }

    public static void main(String[] args) {
        List<String> list = null;
        for (String s:list){
            System.out.println(s.toString());
        }
    }
}
         