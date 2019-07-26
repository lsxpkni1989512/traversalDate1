package com.test;

import java.util.concurrent.CountDownLatch;

enum CountryEnum{
    ONE(1,"齐"),
    TWO(2,"楚"),
    THREE(3,"燕"),
    FOUR(4,"韩"),
    FIVE(5,"赵"),
    SIX(6,"魏");

    private Integer retCode;
    private String retMessage;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }
    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] myArray = CountryEnum.values();
        for (CountryEnum countryEnum:myArray){

            if(index == countryEnum.getRetCode()){
                return countryEnum;
            }
        }
        return null;
    }
}
//倒计时器
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
//        for (int i =1;i<=6;i++){
//            new Thread(()->{
//
//                System.out.println(Thread.currentThread().getName()+"\t 加班结束 离开办公室");
//                countDownLatch.countDown();
//            },String.valueOf(i)).start();
//        }
//
//        countDownLatch.await();
//        System.out.println(Thread.currentThread().getName()+"\t 最后的人锁门 离开办公室");

        //秦灭6国，一统天下
        for (int i =1;i<=6;i++){
            new Thread(()->{

                System.out.println(Thread.currentThread().getName()+"\t 国灭");
                countDownLatch.countDown();
            },CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 秦国统一");
    }
}
