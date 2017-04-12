package com.example.utils;

import com.example.lang.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by fan-gk on 2017/3/1.
 */


public class DateUtil {

    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DAY_FORMAT = "yyyy-MM-dd";
    private static SimpleDateFormat allFormat = null;

    private static Calendar getCalendar() {
        return Calendar.getInstance();
    }


    public static Date now() {
        return getCalendar().getTime();
    }

    /**
     * 获取输入参数的年月日返回
     *
     * @param date
     * @return 年月日
     */
    public static Date getDate(Date date) {
        Calendar calendar = getCalendar();
        calendar.setTime(date == null ? now() : date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date addSeconds(Date date, int seconds) {
        return add(date, Calendar.SECOND, seconds);
    }

    public static Date addMilliseconds(Date date, int milliseconds) {
        return add(date, Calendar.MILLISECOND, milliseconds);
    }

    public static Date addDays(Date date, int days) {
        return add(date, Calendar.DATE, days);
    }

    public static boolean isSameYear(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;
        Calendar calendar = getCalendar();
        calendar.setTime(d1);
        int y1 = calendar.get(Calendar.YEAR);
        calendar.setTime(d2);
        return y1 == calendar.get(Calendar.YEAR);
    }

    private static Date add(Date date, int field, int value) {
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        calendar.add(field, value);
        return calendar.getTime();
    }

    public static String getDate() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = sDateFormat.format(now());
        return date;
    }

    /**
     * 获取现在时间的字符串
     */
    public static String getCurrentTimeStr() {
        if (allFormat == null) {
            allFormat = new SimpleDateFormat(TIME_FORMAT);
        }
        String serverTime = allFormat.format(now()).replace(" ", "T");
        return serverTime;
    }

    /**
     * 获取当天
     */
    public static String getCurrentDay() {
        if (allFormat == null) {
            allFormat = new SimpleDateFormat(DAY_FORMAT);
        }
        String serverTime = allFormat.format(now()).replace(" ", "T");
        return serverTime;
    }

    /**
     * 将一个时区的时间戳转换为Utc时间戳
     *
     * @param time
     * @param zone
     * @return
     */
    public static long toUtc(long time, TimeZone zone) {
        time = time - TimeZone.getDefault().getRawOffset();
        return time;
    }

    /**
     * 将默认时区的时间戳转换为Utc时间戳
     *
     * @param time
     * @return
     */
    public static long toUtc(long time) {
        return toUtc(time, TimeZone.getDefault());
    }

    /**
     * 将Utc时间戳转换为一个时区的时间戳
     *
     * @param time
     * @param zone
     * @return
     */
    public static long fromUtc(long time, TimeZone zone) {
        return time + zone.getRawOffset();
    }

    /**
     * 将Utc时间戳转换为默认时区的时间戳
     *
     * @param time
     * @return
     */
    public static long fromUtc(long time) {
        return fromUtc(time, TimeZone.getDefault());
    }

    /**
     * 把带有时间字符串的T字符替换
     *
     * @param time
     * @return
     */
    public static String timeFormat(String time) {
        if (time.contains("T"))
            return time.replace("T", " ");
        else return time;
    }

    /**
     * 把String日期转成制定格式
     */
    public static String getDateFormat(String strDate, String format) {
        if (strDate != null && strDate.length() > 0) {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Date date = null;
            try {
                if (strDate.contains("T")) {
                    if (strDate.contains(".")) {
                        date = getFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(strDate);
                    } else {
                        date = getFormat("yyyy-MM-dd'T'HH:mm:ss").parse(strDate);
                    }

                }
            } catch (ParseException e) {
                e.printStackTrace();
                return strDate;
            }

            return simpleDateFormat.format(date);
        }
        return strDate;
    }

    public static SimpleDateFormat getFormat(String partten) {
        return new SimpleDateFormat(partten);
    }


    public static String dateToString(Date date, String strFormat) {
        String time;
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        time = format.format(date);
        return time;
    }


    public static String parseTime(String serverTime) {
        try {
            String date = timeFormat(serverTime);
            SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
            Date d = sdf.parse(date);
            return getInterval(d);
        } catch (Exception e) {
            e.printStackTrace();
            return "--";
        }
    }

    public static String parseTime(String serverTime, String format) {
        try {
            String date = timeFormat(serverTime);
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date d = sdf.parse(date);
            return getInterval(d);
        } catch (Exception e) {
            e.printStackTrace();
            return "--";
        }
    }


    public static Date stringToDate(String time, String format) throws ParseException {
        String formatTime = timeFormat(time);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = simpleDateFormat.parse(formatTime);
        return date;

    }

    //获取处理后的时间
    public static String getInterval(Date date) {
        String interval = "";
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        long millisecond = Calendar.getInstance().getTimeInMillis()
                - date.getTime();
        long second = millisecond / 1000;
        if (second <= 0) {
            second = 0;
        }
        if (second < 60) {
            interval = "刚刚";
        } else if (second < 60 * 60) {
            interval = (int) (second / 60) + "分钟前";
        } else {
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);
            String AP = "";
            if (hour <= 12) {
                AP = "上午 ";
            } else {
                AP = "下午";
            }
            if (Calendar.getInstance().get(Calendar.YEAR) == year) {// 同年
                if (Calendar.getInstance().get(Calendar.MONTH) == month) {// 同月
                    switch (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                            - day) {
                        case 0:// 当天
                            interval += AP;
                            interval += dateToString(date, " HH:mm");
                            break;
                        case 1:// 昨天
                            interval += "昨天 ";
                            interval += AP;

                            break;
                        case 2:// 前天
                            interval += "前天 ";
                            interval += AP;
                            break;
                        default:
                            interval = dateToString(date, "M-dd HH:mm");
                            break;
                    }
                } else {
                    interval = dateToString(date, "M-dd HH:mm");
                }
            } else {
                interval = dateToString(date, "yyyy-M-dd");
            }
        }
        return interval;
    }

    /**
     * 获取与当前时间差
     *
     * @return
     */
    public static long getNowTimeDisatnce(String time) {
        if (StringUtil.isNullOrEmpty(time))
            return -1;
        long second = 0;
        try {
            SimpleDateFormat df = new SimpleDateFormat(TIME_FORMAT);
            Date now = now();
            Date next = df.parse(time);
            second = (next.getTime() - now.getTime()) / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return second;
    }

    /**
     * 判断当前时间是否超过指定时间n天
     *
     * @return
     */
    public static boolean hasMissDays(String time, int day) {
        if (time == null)
            return false;
        try {
            int missDay = 0;
            SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
            Date now = now();
            Date t = dateFormat.parse(time);
            missDay = (int) ((now.getTime() - t.getTime()) / (1000 * 60 * 60 * 24));
            if (missDay < day)
                return false;
            else
                return true;
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return false;

    }


}
