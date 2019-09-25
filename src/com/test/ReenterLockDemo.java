package com.test;

import java.util.concurrent.locks.ReentrantLock;

class Phone {
    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"\t sendEmail()");
        phone();
    }
    public synchronized void phone(){
        System.out.println(Thread.currentThread().getName()+"\t phone()");
    }
}

class Car{
    ReentrantLock lock = new ReentrantLock();
    public void start(){

        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"\t start()");
            end();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public void end(){

        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"\t end()");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
public class ReenterLockDemo {
    public static void main(String[] args) {
        Car car = new Car();
        new Thread(()->{
            car.start();
        },"t1").start();
        new Thread(()->{
            car.start();
        },"t2").start();
    }

}
