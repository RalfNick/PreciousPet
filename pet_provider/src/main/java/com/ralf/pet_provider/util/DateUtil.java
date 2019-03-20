package com.ralf.pet_provider.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


/**
 * @author lixiyuan
 */
public class DateUtil {

    public final static long TIME_MINUTE = 60;
    public final static long TIME_HOUR = TIME_MINUTE * 60;
    public final static long TIME_DAY = TIME_HOUR * 24;
    public final static long TIME_MONTH = TIME_DAY * 30;
    public final static long TIME_YEAR = TIME_DAY * 365;

    public static String DATE_PATTERN_YYYYMMDD = "yyyy-MM-dd";

    public static String DATE_PATTERN_YYYYMMDD_HHMM = "yyyy-MM-dd HH:mm";

    public static String DATE_PATTERN_HHMM = "HH:mm";
    public static String DATE_PATTERN_HHMMSS = "HH:mm:ss";
    public static String DATE_PATTERN_MMSS = "mm:ss";

    public static String DATE_PATTERN_MMDD_HHMM = "MM/dd HH:mm";

    public static String DATE_PATTERN_TASK_MMDD_HHMM = "MM月dd日 HH:mm 到期";

    public static String DATE_PATTERN_LAW_SCHOOL = "MM月dd日";

    public static String DATE_PATTERN_DETAIL_DATE = "yyyy年MM月dd日";

    public static String format(Date date, String pattern) {
        if (date == null || TextUtils.isEmpty(pattern)) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.US);
        return simpleDateFormat.format(date);
    }

    /**
     * 格式化时间格式，判断是否今天
     *
     * @param date    date
     * @param pattern pattern
     * @return string
     */
    public static String formatIsToday(Date date, String pattern) {
        if (isToday(date)) {
            return "今天";
        }
        return format(date, pattern);
    }

    /**
     * 获取计时时长 00:11
     *
     * @param milliseconds milliseconds
     * @return string
     */
    public static String getHHmm(long milliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN_HHMM);
        return formatter.format(milliseconds);
    }

    public static String getDurationOfMMss(long duration) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN_MMSS);
        return formatter.format(duration);
    }

    public static String getMMMdd(long milliseconds) {
        String formatStr = null;
        if (isThisYear(milliseconds)) {
            formatStr = "MM月dd日";
        } else {
            formatStr = "yyyy年MM月dd日";
        }
        if (!TextUtils.isEmpty(formatStr)) {
            SimpleDateFormat formatter = new SimpleDateFormat(formatStr, Locale.CHINA);
            try {
                return formatter.format(milliseconds);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return String.valueOf(milliseconds);
    }

    /**
     * 判断是否是今年
     *
     * @param millis
     * @return
     */
    public static boolean isThisYear(long millis) {
        Calendar otherYear = Calendar.getInstance();
        otherYear.setTimeInMillis(millis);
        Calendar thisYear = Calendar.getInstance();
        thisYear.setTimeInMillis(System.currentTimeMillis());

        return otherYear.get(Calendar.YEAR) == thisYear.get(Calendar.YEAR);
    }

    /**
     * 根据日期获得星期
     *
     * @param millis
     * @return
     */
    public static String getWeekOfDateFromZ(long millis) {
        String[] weekDaysName = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    /**
     * 格式化时间格式，判断是否今天
     *
     * @param date
     * @return
     */
    public static String formateDayStr(Date date) {
        String dateStr = format(date, DATE_PATTERN_YYYYMMDD_HHMM);

        if (!TextUtils.isEmpty(dateStr)) {
            if (isToday(date)) {
                dateStr = String.format("今天 %s", dateStr.split(" ")[1]);
            }
        }
        return dateStr;
    }

    /**
     * 判断日期是否今天
     *
     * @param date date
     * @return boolean
     */
    public static boolean isToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());

        Calendar now = Calendar.getInstance();

        return calendar.get(Calendar.YEAR) == now.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 将字符串转化为时间戳
     *
     * @param format format
     * @param time   time
     * @return time
     */
    public static long getStringToDate(String format, String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 获取当天时间字符串，格式yyyy-MM-dd
     *
     * @return
     */
    public static String getTodayStr() {
        return getTodayStr(DATE_PATTERN_YYYYMMDD);
    }

    /**
     * 根据指定格式获取当天时间字符串
     *
     * @param pattern
     * @return
     */
    public static String getTodayStr(String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

    public static final String YMD = "yyyyMMdd";
    public static final String YMD_YEAR = "yyyy";
    public static final String YMD_BREAK = "yyyy-MM-dd";
    public static final String YMDHMS = "yyyyMMddHHmmss";
    public static final String YMDHMS_BREAK = "yyyy-MM-dd HH:mm:ss";
    public static final String YMDHMS_BREAK_HALF = "yyyy-MM-dd HH:mm";

    /**
     * 计算时间差
     */
    public static final int CAL_MINUTES = 1000 * 60;
    public static final int CAL_HOURS = 1000 * 60 * 60;
    public static final int CAL_DAYS = 1000 * 60 * 60 * 24;

    /**
     * 获取当前时间格式化后的值
     *
     * @param pattern
     * @return
     */
    public static String getNowDateText(String pattern) {
        SimpleDateFormat sdf = getSimpleDateFormat(pattern);
        return sdf.format(new Date());
    }

    /**
     * 获取日期格式化后的值
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getDateText(Date date, String pattern) {
        SimpleDateFormat sdf = getSimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 字符串时间转换成Date格式
     *
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date getDate(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = getSimpleDateFormat(pattern);
        return sdf.parse(date);
    }

    private static SimpleDateFormat getSimpleDateFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    /**
     * 获取时间戳
     *
     * @param date
     * @return
     */
    public static Long getTime(Date date) {
        return date.getTime();
    }

    /**
     * 计算时间差
     *
     * @param startDate
     * @param endDate
     * @param calType   计算类型,按分钟、小时、天数计算
     * @return
     */
    public static int calDiffs(Date startDate, Date endDate, int calType) {
        Long start = getTime(startDate);
        Long end = getTime(endDate);
        int diff = (int) ((end - start) / calType);
        return diff;
    }

    /**
     * 计算时间差值以某种约定形式显示
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String timeDiffText(Date startDate, Date endDate) {
        int calDiffs = calDiffs(startDate, endDate, CAL_MINUTES);
        if (calDiffs == 0) {
            return "刚刚";
        }
        if (calDiffs < 60) {
            return calDiffs + "分钟前";
        }
        calDiffs = calDiffs(startDate, endDate, CAL_HOURS);
        if (calDiffs < 24) {
            return calDiffs + "小时前";
        }
        if (calDiffs < 48) {
            return "1天前";
        }

        if (calDiffs < 72) {
            return "2天前";
        }

        if (calDiffs < 96) {
            return "3天前";
        }

        if (calDiffs < 120) {
            return "4天前";
        }
        if (calDiffs < 144) {
            return "5天前";
        }
        return getDateText(startDate, YMD_BREAK);
    }

    /***
     * 格式化时间戳
     * @param timeString
     * @return
     */
    public static String timeAutoFormat(@NonNull String timeString) {
        Long currentTime = System.currentTimeMillis() / 1000;
        Long paramTime = Long.valueOf(timeString);
        long intervalTime = currentTime - paramTime;
        if (intervalTime > TIME_YEAR) {
            return String.valueOf(intervalTime / TIME_YEAR) + "年前";
        } else if (intervalTime > TIME_MONTH) {
            return String.valueOf(intervalTime / TIME_MONTH) + "月前";
        } else if (intervalTime > TIME_DAY) {
            return String.valueOf(intervalTime / TIME_DAY) + "天前";
        } else if (intervalTime < TIME_MINUTE) {
            return "刚刚";
        } else if (intervalTime > TIME_HOUR) {
            return String.valueOf(intervalTime / TIME_HOUR) + "小时前";
        } else {
            return String.valueOf(intervalTime / TIME_MINUTE) + "分钟前";
        }

    }

    /**
     * 显示某种约定后的时间值,类似微信朋友圈发布说说显示的时间那种
     *
     * @param date
     * @return
     */
    public static String showTimeText(Date date) {
        return timeDiffText(date, new Date());
    }


    /**
     * 获取任务列表中任务到期时间
     *
     * @param date
     * @return
     */
    public static String showTaskEndTime(Date date) {
        return getDateText(date, DATE_PATTERN_MMDD_HHMM);
    }

    /**
     * 获取任务详情中任务到期时间
     *
     * @param date
     * @return
     */
    public static String showTaskDetailEndTime(Date date) {
        return getDateText(date, DATE_PATTERN_TASK_MMDD_HHMM);
    }

    /**
     * 返回课程预计播出时间
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public static String showCourseExpectTime(Date startDate, Date endDate) {
        String sDate = getDateText(startDate, DATE_PATTERN_HHMM);
        String eDate = getDateText(endDate, DATE_PATTERN_HHMM);
        return sDate + " - " + eDate;
    }

    /**
     * 获取某天某时的时间戳
     *
     * @param today    某天
     * @param needHour 某时
     * @return
     */
    public static long getTimeStampOfDay(long today, int needHour) {
//        String currentTimeZone = getCurrentTimeZone();
        TimeZone curTimeZone = TimeZone.getTimeZone("UTC+0");
        Calendar calendar = Calendar.getInstance(curTimeZone);
        calendar.setTimeInMillis(today);
        calendar.set(Calendar.HOUR_OF_DAY, needHour);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static String getCurrentTimeZone() {
        TimeZone tz = TimeZone.getDefault();
        String strTz = tz.getDisplayName(false, TimeZone.SHORT);
        return strTz;
    }

    /**
     * 显示当前的日期及星期
     *
     * @return
     */
    public static String getCurrentDateAndweek() {
        String currentDate = DateUtil.format(new Date(), DateUtil.DATE_PATTERN_LAW_SCHOOL);

        return currentDate + " " + getCurrentWeekDay();

    }

    public static String getCurrentWeekDay() {
        String week = "";
        Calendar c1 = Calendar.getInstance();
        int day = c1.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case 1:
                week = "星期日";
                break;
            case 2:
                week = "星期一";
                break;
            case 3:
                week = "星期二";
                break;
            case 4:
                week = "星期三";
                break;
            case 5:
                week = "星期四";
                break;
            case 6:
                week = "星期五";
                break;
            case 7:
                week = "星期六";
                break;
            default:
                break;
        }
        return week;
    }
}
