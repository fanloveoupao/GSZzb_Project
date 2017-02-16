package com.example.lang;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fan-gk on 2016/9/9.
 */

public class StringUtil {
    public static final String EMPTY = "";

    public static boolean isNullOrEmpty(String value) {
        return value == null || EMPTY.equals(value);
    }

    public static boolean isNullOrWhiteSpace(String value) {
        return value == null || EMPTY.equals(value.trim());
    }

    public static String capitalize(String value) {
        if (value == null)
            return value;
        if (value.length() == 1)
            return value.toUpperCase();
        return value.substring(0, 1).toUpperCase()
                + value.substring(1);
    }

    public static String padLeft(String text, int totalWidth, char paddingChar) {
        if (text == null)
            text = EMPTY;

        if (text.length() >= totalWidth)
            return text;

        int paddingLength = totalWidth - text.length();
        char[] paddingChars = new char[paddingLength];
        for (int i = 0; i < paddingChars.length; i++) {
            paddingChars[i] = paddingChar;
        }
        return new String(paddingChars) + text;
    }

    public static String hidden(String text, int start) {
        return hidden(text, start, 0);
    }

    public static String hidden(String text, int start, int limit) {
        return hidden(text, start, limit, '*');
    }

    public static boolean isEqualLength(String text, int length) {
        if (text != null)
            return text.length() == length ? true : false;
        else
            return false;
    }

    public static boolean isReachLength(String text, int length) {
        if (text != null)
            return text.length() >= length ? true : false;
        else
            return false;
    }


    public static String hidden(String text, int start, int limit, char paddingChar) {
        if (text == null)
            return text;

        int len = text.length();
        if (start > len)
            start = 0;

        limit = Math.max(0, limit);
        if (limit == 0)
            limit = len;

        if (start + limit > len)
            limit = len - start;

        String ht = padLeft(StringUtil.EMPTY, limit, paddingChar);

        String result = EMPTY;
        if (start > 0)
            result += text.substring(0, start);
        result += ht;
        if (start + limit < len)
            result += text.substring(start + limit);

        return result;
    }

    public static boolean verifyMobile(String mobile) {
        if (mobile == null)
            return false;
        mobile = mobile.trim();
        if (mobile.length() != 11 || !mobile.startsWith("1"))
            return false;
        else
            return true;
    }

    public static String firstOrDefaultNotNullOrWhiteSpace(String... values) {
        if (values == null || values.length == 0) return null;
        for (String value : values) {
            if (!isNullOrWhiteSpace(value))
                return value;
        }
        return null;
    }


    public static String getPart(String string, int start, int end) {
        if (string.length() >= end) {
            String str = string.substring(start, end);
            return str;
        } else {
            return null;
        }
    }

    public static String ensureNotNull(String str) {
        return isNullOrEmpty(str) ? StringUtil.EMPTY : str;
    }

    public static String join(CharSequence delimiter, Iterable<?> tokens) {
        StringBuilder sb = new StringBuilder();
        Iterator<?> it = tokens.iterator();
        if (it.hasNext()) {
            sb.append(it.next());
            while (it.hasNext()) {
                sb.append(delimiter);
                sb.append(it.next());
            }
        }
        return sb.toString();
    }

    public static boolean noCharactor(String str){
        if (isNullOrWhiteSpace(str)) return false;
        Pattern pattern = Pattern.compile("^([\u4E00-\u9FA5]|\\w)+$");
        Matcher m = pattern.matcher(str);
        return m.matches();
    }

    public static boolean isAllHanZhi(String str) {
        if (isNullOrWhiteSpace(str)) return false;
        Pattern pattern = Pattern.compile("^[\u4E00-\u9FA5]+$");
        Matcher m = pattern.matcher(str);
        return m.matches();
    }

    public static boolean isTrueName(String name) {
        return name.length() >= 2 && name.length() <= 8 && isAllHanZhi(name);
    }

    public static boolean isTrueCompanyName(String company) {
        return !isNullOrWhiteSpace(company) && company.length() >= 3 && Pattern.compile("^[\u4E00-\u9FA5|0-9a-zA-Z]+$").matcher(company).matches();
    }

    /**
     * 比较两个字符串是否相等，任意一个为null都返回false
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2){
        return str1 != null && str2 != null && str1.equals(str2);
    }
}
