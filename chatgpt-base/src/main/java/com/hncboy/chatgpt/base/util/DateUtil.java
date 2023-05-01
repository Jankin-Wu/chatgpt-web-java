package com.hncboy.chatgpt.base.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Jankin Wu
 * @description 日期工具类
 * @date 2023/5/1 18:04
 */
public class DateUtil {

    /**
     * LocalDateTime 转 Date
     *
     * @param localDateTime /
     * @return /
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }
}
