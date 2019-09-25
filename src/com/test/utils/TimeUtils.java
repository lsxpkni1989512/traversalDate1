package com.test.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
    //获取指定月份的天数
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);

        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        System.out.println(maxDate);
        return maxDate;
    }

    public static void dayReport(Date month) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(month);//month 为指定月份任意日期A
        int year = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
        int mm = cal.get(Calendar.DAY_OF_MONTH);
        System.out.println(mm);//当前月的第几天
        int dayNumOfMonth = TimeUtils.getDaysByYearMonth(year, m+1);//当钱前月一共有多少天
//        System.out.println(year +","+(m+1)+","+dayNumOfMonth);
        cal.set(Calendar.DAY_OF_MONTH, 1);// 从一号开始

        for (int i = 0; i < dayNumOfMonth; i++, cal.add(Calendar.DATE, 1)) {
            Date d = cal.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String df = simpleDateFormat.format(d);
            System.out.println(df);
        }
    }
}
