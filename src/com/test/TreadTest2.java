package com.test;

import com.test.Beans.Resource;

public class TreadTest2 {
    /**
     *
     * 题目：
     * 多线程按A>B>C顺序执行：
     * A线程打印5行,B线程打印10行,C线程打印15行
     * 紧接着
     * A线程打印5行,B线程打印10行,C线程打印15行
     * ...
     * 来10轮
     */

    /**
     * 思路：用ReentrantLock的精确唤醒线程功能
     */




    public static void main(String[] args) {

        Resource resource = new Resource();
            //写法1：
//        for (int i = 1;i<=2;i++){
//            new Thread(()->{
//                resource.print5();
//            },"A").start();
//
//            new Thread(()->{
//                resource.print10();
//            },"B").start();
//
//            new Thread(()->{
//                resource.print15();
//            },"C").start();
//        }
        //写法2：

            new Thread(()->{
                for (int i = 1;i<=2;i++){
                    resource.print5();
                }
            },"A").start();

            new Thread(()->{
                for (int i = 1;i<=2;i++){

                    resource.print10();
                }
            },"B").start();

            new Thread(()->{
                for (int i = 1;i<=2;i++){

                    resource.print15();
                }
            },"C").start();

    }




}
