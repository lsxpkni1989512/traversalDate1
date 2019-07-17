package com.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    private volatile boolean FLAG = true;//默认开启，进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    BlockingQueue<String> blockingQueue = null;
    public MyResource(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd()throws Exception{
        String data = null;
        boolean returnValue ;
        while(FLAG){
            data = atomicInteger.incrementAndGet()+"";
            returnValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if(returnValue){

                System.out.println(Thread.currentThread().getName()+"\t 插入队列 " +data+"成功");
            }else{

                System.out.println(Thread.currentThread().getName()+"\t 插入队列 " +data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t 大老板叫停了 FLAG =false 生产动作结束");
    }

    public void myConsumer()throws Exception{
        String data = null;
        while (FLAG){
            data = blockingQueue.poll(2,TimeUnit.SECONDS);
            if(null == data || "".equalsIgnoreCase(data)){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过2秒没有取到蛋糕,消费退出  " );
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费蛋糕  " +data+" 成功");
        }
    }

    public void stop(){
        FLAG = false;
        System.out.println(Thread.currentThread().getName()+"\t stop方法 被调用");
    }

}

public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        //生产+消费 5 秒
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(3));
        new Thread(()->{
            try {

                myResource.myProd();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"生产线程").start();

        new Thread(()->{
            try {

                myResource.myConsumer();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"消费线程").start();

        try {

//            TimeUnit.SECONDS.sleep(5);
            Thread.sleep(5000L);
        }catch (Exception e){
            e.printStackTrace();
        }
        myResource.stop();
    }
}
