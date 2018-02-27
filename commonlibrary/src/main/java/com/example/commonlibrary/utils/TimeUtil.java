package com.example.commonlibrary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class TimeUtil {
    public static final String DATE_YMD_DEFAULT = "yyyy-MM-dd";
    public static final String DATE_YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_YMD = "yyyy/MM/dd";
    public static final String DATE_YMD_CN = "yyyy年MM月dd日";
    public static final String DATE_YMD_NOR = "yyyy-MM-dd";

    /**
     * 睦邻发帖时间统一为超过24小时则显示日期
     *
     * @param strData
     * @return
     */
    public static String formatDataForDisplay(String strData) {
        Date date = new Date();
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat(DATE_YMD_HMS);
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_YMD_NOR);
        Date issueDate = null;
        if (strData == null || strData.length() == 0) {
            return "";
        } else if (strData.contains("-")) {
            try {
                issueDate = myFormatter.parse(strData);
            } catch (ParseException e) {
                e.printStackTrace();
                return "";
            }
        } else {
            long l = Long.valueOf(strData);
            issueDate = new Date(l);
        }

        long currTime = date.getTime();
        long issueTime = issueDate.getTime();
        long diff = currTime - issueTime;
        diff = diff / 1000;//秒
        if (diff / 60 < 1) {
            return "刚刚";
        }
        if (diff / 60 >= 1 && diff / 60 <= 60) {
            return diff / 60 + "分钟前";
        }
        if (diff / 3600 > 0 && diff / 3600 <= 24) {
            return diff / 3600 + "小时前";
        }
        if (diff / (3600 * 24) > 0) {
            return formatter.format(issueDate);
        }
        return "";
    }


    public static String transfrom(String strData, String frompattern, String toPattern) {
        SimpleDateFormat from = new SimpleDateFormat(frompattern);
        SimpleDateFormat to = new SimpleDateFormat(toPattern);
        Date date = null;
        try {
            date = from.parse(strData);
            return to.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getDay(String strData) {
        return transfrom(strData, DATE_YMD_HMS, "dd");
    }

    public static String getMonth(String strData) {
        return transfrom(strData, DATE_YMD_HMS, "MM");
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(long s, String format) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = new Date(s);
        res = simpleDateFormat.format(date);
        return res;
    }


//    public static long dateToLong(String date) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            Date time = simpleDateFormat.parse(date);
//            return time.getTime();
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }

    /***
     * 日期转为时间戳
     * @param date
     * @return
     */
    public static long dateToLong(String date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            Date time = simpleDateFormat.parse(date);
            return time.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取上个月月份
     *
     * @return
     */
    public static int getLastMonth() {
        Calendar aCalendar = Calendar.getInstance();
        int month = aCalendar.get(Calendar.MONTH);
        if (month == 0) {
            return 12;
        }
        return month;
    }

    /**
     * 转化为年月日中文格式
     *
     * @param date
     * @return
     */
    public static String changeDateFormat(String date) {
        if (StringUtil.isEmpty(date))
            return "";
        long time = dateToLong(date, DATE_YMD_NOR);
        return stampToDate(time, DATE_YMD_CN);
    }


    /**
     * 获取日期间隔
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int getDaySpace(long beginDate, long endDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(new Date(beginDate));
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(new Date(endDate));
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        return day2 - day1;
    }

    /**
     * 获取年，月，日
     *
     * @param time
     * @return
     */
    public static int[] longToYMD(long time) {
        Calendar mycalendar = Calendar.getInstance();
        mycalendar.setTimeInMillis(time);
        return new int[]{mycalendar.get(Calendar.YEAR), mycalendar.get(Calendar.MONTH), mycalendar.get(Calendar.DAY_OF_MONTH)};
    }

    /**
     * 获取count天前的年月日
     *
     * @param count
     * @return
     */
    public static int[] getBeforeDate(int count) {
        Date today = new Date();
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(today);
        theCa.add(theCa.DATE, -count);//最后一个数字30可改，30天的意思

        return new int[]{theCa.get(Calendar.YEAR), theCa.get(Calendar.MONTH), theCa.get(Calendar.DAY_OF_MONTH)};

    }


}
