package cn.education.web.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class DateUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    public static final int DEFAULT = 0;
    public static final int YM = 1;
    public static final int YMR_SLASH = 11;
    public static final int NO_SLASH = 2;
    public static final int YM_NO_SLASH = 3;
    public static final int DATE_TIME = 4;
    public static final int DATE_TIME_NO_SLASH = 5;
    public static final int DATE_HM = 6;
    public static final int TIME = 7;
    public static final int HM = 8;
    public static final int LONG_TIME = 9;
    public static final int SHORT_TIME = 10;
    public static final int DATE_TIME_LINE = 12;
    public static final int DATE_YEAR_TWO = 13;
    public static final int DATE_DAY = 14;
    public static final int DATE_MONTH = 15;
    public static final int DATE_MIN = 16;

    /**
     * 默认时间格式
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    public static final String DEFAULT_DATE_SEC = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认格式化类
     */
    public static final SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);

    public DateUtil() {
    }

    public static String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return sdf.format(date);
    }

    public static Date parse(String date,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (null == date) {
            return null;
        }
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parse(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        if (null == date) {
            return null;
        }
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static long formatDay(Long day){
        if(null == day){
            return 0;
        }
        Date date = new Date(day.longValue());
        date = parse(format(date));
        if(null == date){
            return 0;
        }else {
            return date.getTime();
        }
    }

    public static Date parseByPattern(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        if (null == date) {
            return null;
        }
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parseDate(Date now) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        String date = sdf.format(now);
        if (null == date) {
            return null;
        }
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parseDate(Date now,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String date = sdf.format(now);
        if (null == date) {
            return null;
        }
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String dateToStr(Date date, String pattern) {
        if (date != null && !date.equals("")) {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            return formatter.format(date);
        } else {
            return null;
        }
    }

    public static String dateToStr(Date date) {
        return dateToStr(date, "yyyy/MM/dd");
    }

    /**
     * 获取timeAdd天后的日期
     *
     * @param date
     * @param timeAdd
     * @return
     */
    public static Date getDayAddDate(Date date, int timeAdd) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, timeAdd);
        return cal.getTime();
    }

    /**
     * 获取timeAdd天后的日期
     *
     * @param now
     * @param timeAdd
     * @return
     */
    public static Date getDayAddSecond(Date now, int timeAdd) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.SECOND, timeAdd);
        return cal.getTime();
    }

    public static String addMonth(String date, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = sdf.parse(date);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);

            rightNow.add(Calendar.MONTH, n);
            Date dt1 = rightNow.getTime();
            String reStr = sdf.format(dt1);

            return reStr;
        } catch (ParseException e) {
            return null;
        }


    }

    public static String dateToStr(Date date, int type) {
        switch (type) {
            case 0:
                return dateToStr(date);
            case 1:
                return dateToStr(date, "yyyy/MM");
            case 2:
                return dateToStr(date, "yyyyMMdd");
            case 3:
                return dateToStr(date, "yyyyMM");
            case 4:
                return dateToStr(date, "yyyy/MM/dd HH:mm:ss");
            case 5:
                return dateToStr(date, "yyyyMMddHHmmss");
            case 6:
                return dateToStr(date, "yyyy/MM/dd HH:mm");
            case 7:
                return dateToStr(date, "HH:mm:ss");
            case 8:
                return dateToStr(date, "HH:mm");
            case 9:
                return dateToStr(date, "HHmmss");
            case 10:
                return dateToStr(date, "HHmm");
            case 11:
                return dateToStr(date, "yyyy-MM-dd");
            case 12:
                return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
            case 13:
                return dateToStr(date, "yyMMdd");
            case 14:
                return dateToStr(date, "dd");
            case 15:
                return dateToStr(date, "yyyy-MM");
            case 16:
                return dateToStr(date, "yyyy-MM-dd HH:mm");
            default:
                throw new IllegalArgumentException("Type undefined : " + type);
        }
    }

    public static int calcTwoDateIntervalDays(Date firstDay, Date secondDay) {
        String firstDayStr = DateUtil.dateToStr(firstDay, 11);
        firstDay = parse(firstDayStr);
        String secondDayStr = DateUtil.dateToStr(secondDay, 11);
        secondDay = parse(secondDayStr);
        int interval = (int) ((firstDay.getTime() - secondDay.getTime()) / (1000 * 3600 * 24));
        return interval;
    }

}
