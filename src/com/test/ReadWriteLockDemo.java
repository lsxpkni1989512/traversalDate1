package com.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    public void put(String key,Object o){
        rwLock.writeLock().lock();
        try {

            System.out.println(Thread.currentThread().getName()+"\t 正在写入："+key);
            map.put(key,o);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key){
        rwLock.readLock().lock();
        try {

            System.out.println(Thread.currentThread().getName()+"\t 正在读取");
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成"+o);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }
    }


}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        new Thread(()->{
            myCache.put("1","o1");
            myCache.put("2","o2");
            myCache.get("1");
            myCache.get("2");
        },"AA").start();
        new Thread(()->{
            myCache.put("3","o3");
            myCache.put("4","o4");
            myCache.get("3");
            myCache.get("4");
        },"BB").start();
    }
}
