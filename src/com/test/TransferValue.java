package com.test;

public class TransferValue {
    public void changeValue(String str){
        str = "xxx";
    }
    public static void main(String[] args) {
        String str = "aaa";
        TransferValue transferValue = new TransferValue();
        transferValue.changeValue(str);
        System.out.println(str);
    }
}
