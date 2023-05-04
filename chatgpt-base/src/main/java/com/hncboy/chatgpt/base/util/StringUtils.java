package com.hncboy.chatgpt.base.util;

/**
 * @author Jankin Wu
 * @description 字符串工具类
 * @date 2023/5/2 20:20
 */
public class StringUtils {

    /**
     * 去除字符串中的空格和空行
     *
     * @param str 输入的字符串
     * @return 输出的字符串
     */
    public static String removeSpacesAndBlankLines(String str) {
        return str.replaceAll("\\s+", "").replaceAll("(?m)^\\s*$|" + System.lineSeparator() + "|\r\n", "");
    }
}
