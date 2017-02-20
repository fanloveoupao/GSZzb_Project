package com.example.utils;

/**
 * Created by fan-gk on 2017/2/20.
 */

public class HtmlTextUtils {
    public static final String COLOR_ORANGE = "#ff5500";
    public static final String COLOR_YELOW = "#FF8800";

    public static String color(String colorCode, String str) {
        return "<font color=\"" + colorCode + "\">" + str + "</font>";
    }

    public static String font(String str) {
        return "<font>" + str + "</font>";
    }

    public static String diyTagToFont(String text, String colorString, String tag) {
        String text1 = text.replaceAll("<" + tag + ">", "<font color=\"" + colorString + "\">");
        String text2 = text1.replaceAll("</" + tag + ">", "</font>");
        return text2;
    }

    public static String diyTagToFont(String text, String colorString, String... tag) {

        String text1 = text.replaceAll("<" + tag + ">", "<font color=\"" + colorString + "\">");
        String text2 = text1.replaceAll("</" + tag + ">", "</font>");
        return text2;
    }
}

