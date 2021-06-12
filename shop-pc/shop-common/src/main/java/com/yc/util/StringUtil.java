package com.yc.util;

/**
 * @author hp
 */
public class StringUtil {

    /**
     * 字符串分割
     */
    public static String[] splitString(String str, String regex) {
        if (null == str || "".equals(str)) {
            return null;
        }
        return str.split(regex);
    }
}
