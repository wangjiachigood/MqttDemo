package com.pb.joindata.mqttdemo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 版权:版权所有 © 2015   上海嘉银数据技术有限公司
 * <p/>
 * 描述:时间转换类
 * <p/>
 * Created by chenlong1 on 2016/6/30.
 * <p/>
 * email: panic4java@gmail.com
 */
public class TimeUtil {

    public static final String weekStr[] = {"7", "1", "2", "3", "4", "5", "6"};

    public static final String DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String changeTime(long l) {
        long currentTime = new Date().getTime();
        long sysTime = (currentTime - l) / 1000;
        if (sysTime / 60 / 60 / 24 > 0) {
            return sysTime / 60 / 60 / 24 + "天前";
        } else if (sysTime / 60 / 60 > 0) {
            return sysTime / 60 / 60 + "小时前";
        } else if (sysTime / 60 <= 0) {
            return "刚刚";
        } else {
            return sysTime / 60 + "分钟前";
        }
    }

    /**
     * 毫秒转年月日周时分秒的集合 年4位，周1位，其他2位数字
     *
     * @param l
     * @return
     */
    public static List<String> millisecondToYearMonDayWeekHourMinSec(long l) {
        List<String> list = new ArrayList<String>();
        Calendar cTime = Calendar.getInstance();
        cTime.setTime(new Date(l));
        // 年
        list.add(String.format("%04d", cTime.get(Calendar.YEAR)));
        // 月
        list.add(String.format("%02d", cTime.get(Calendar.MONTH) + 1));
        // 日
        list.add(String.format("%02d", cTime.get(Calendar.DATE)));
        // 周
        list.add(weekStr[cTime.get(Calendar.DAY_OF_WEEK) - 1]);
        // 时
        list.add(String.format("%02d", cTime.get(Calendar.HOUR_OF_DAY)));
        // 分
        list.add(String.format("%02d", cTime.get(Calendar.MINUTE)));
        // 秒
        list.add(String.format("%02d", cTime.get(Calendar.SECOND)));
        return list;
    }

    /**
     * 得到: pattern格式化的时间
     *
     * @param pattern
     * @param timeInMillis
     * @return 年-月-日 小时:分钟
     */
    public static String formatDate(String pattern, long timeInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String sDateTime = sdf.format(timeInMillis);
        return sDateTime;
    }

    public static String formatDate(String pattern, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String sDateTime = sdf.format(date);
        return sDateTime;
    }

    public static String formatDateWithLastDay(String pattern, long timeInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String sDateTime = sdf.format(timeInMillis);
        int type = isYesterday(timeInMillis);
        if (type == 0) {
            sDateTime = "昨天" + sDateTime;
        } else if (type == 1) {
            SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd HH:mm:ss");
            sDateTime = sdf2.format(timeInMillis);
        }
        return sDateTime;
    }

    public static int isYesterday(long timeInMillis) {
        try {
            Date newTime = new Date();
            //将下面的 理解成  yyyy-MM-dd 00：00：00 更好理解点
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String todayStr = format.format(newTime);
            Date today = format.parse(todayStr);
            //昨天 86400000=24*60*60*1000 一天
            if ((today.getTime() - timeInMillis) > 0 && (today.getTime() - timeInMillis) <= 86400000) {
                return 0;
            } else if ((today.getTime() - timeInMillis) <= 0) { //至少是今天
                return -1;
            } else { //至少是前天
                return 1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;

    }

    /**
     * 返回当前日期的格式化时间
     *
     * @return
     */
    public static String getFormattedTimeNow(String pattern) {
        return formatDate(pattern, new Date());
    }

}
