package com.test;

import com.test.utils.TimeUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TraversalDateTest {
    //遍历任意月份的日期
    public static void main(String[] args) {
        System.out.println("开始");
        String month = "2019-02";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        try {
            Date parse = dateFormat.parse(month);
            TimeUtils.dayReport(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("结束");


    }
}
