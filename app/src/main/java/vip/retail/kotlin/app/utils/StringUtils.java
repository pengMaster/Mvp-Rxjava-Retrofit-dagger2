package vip.retail.kotlin.app.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

import java.text.DecimalFormat;
import java.util.UUID;

public class StringUtils {
//    public final static String UTF_8 = "utf-8";

    /**
     * 返回一个高亮spannable
     *
     * @param content 文本内容
     * @param color   高亮颜色
     * @param start   起始位置
     * @param end     结束位置
     * @return 高亮spannable
     */
    public static CharSequence getHighLightText(String content, int color, int start, int end) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        start = start >= 0 ? start : 0;
        end = end <= content.length() ? end : content.length();
        SpannableString spannable = new SpannableString(content);
        CharacterStyle span = new ForegroundColorSpan(color);
        spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }
    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    public static boolean isEmpty(String value) {
        if (value != null && !"".equalsIgnoreCase(value.trim()) && !"null".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * 格式化文件大小，不保留末尾的0
     */
    public static String formatFileSize(long len) {
        return formatFileSize(len, false);
    }

    /**
     * 格式化文件大小，保留末尾的0，达到长度一致
     */
    public static String formatFileSize(long len, boolean keepZero) {
        String size;
        DecimalFormat formatKeepTwoZero = new DecimalFormat("#.00");
        DecimalFormat formatKeepOneZero = new DecimalFormat("#.0");
        if (len < 1024) {
            size = String.valueOf(len + "B");
        } else if (len < 10 * 1024) {
            // [0, 10KB)，保留两位小数
            size = String.valueOf(len * 100 / 1024 / (float) 100) + "KB";
        } else if (len < 100 * 1024) {
            // [10KB, 100KB)，保留一位小数
            size = String.valueOf(len * 10 / 1024 / (float) 10) + "KB";
        } else if (len < 1024 * 1024) {
            // [100KB, 1MB)，个位四舍五入
            size = String.valueOf(len / 1024) + "KB";
        } else if (len < 10 * 1024 * 1024) {
            // [1MB, 10MB)，保留两位小数
            if (keepZero) {
                size = String.valueOf(formatKeepTwoZero.format(len * 100 / 1024 / 1024 / (float) 100)) + "MB";
            } else {
                size = String.valueOf(len * 100 / 1024 / 1024 / (float) 100) + "MB";
            }
        } else if (len < 100 * 1024 * 1024) {
            // [10MB, 100MB)，保留一位小数
            if (keepZero) {
                size = String.valueOf(formatKeepOneZero.format(len * 10 / 1024 / 1024 / (float) 10)) + "MB";
            } else {
                size = String.valueOf(len * 10 / 1024 / 1024 / (float) 10) + "MB";
            }
        } else if (len < 1024 * 1024 * 1024) {
            // [100MB, 1GB)，个位四舍五入
            size = String.valueOf(len / 1024 / 1024) + "MB";
        } else {
            // [1GB, ...)，保留两位小数
            size = String.valueOf(len * 100 / 1024 / 1024 / 1024 / (float) 100) + "GB";
        }
        return size;
    }

    /**
     * 获取当前时间: 年 月 日
     */
    public static String getDate4YMD() {
        return String.format("%tF", System.currentTimeMillis());
    }

    /**
     * 获取当前时间: 时 分 秒
     */
    public static String getDate4HMM() {
        return String.format("%tT", System.currentTimeMillis());
    }

    /**
     * 单位信息 set 数据 处理
     * 1.去空 去null
     * 2.添加2个空格
     */
    public static String getDisStr(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.trim().replace("null", "");
        } else {
            return "-";
        }
    }

    /**
     * 数据库可以识别的不带 '-' 的uuid字符串
     *
     * @return String
     */
    public static String getUUIDString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 单位信息 set 数据 处理
     * 1.去空 去null
     * 2.添加2个空格
     */
    public static String isNoBlank(String str) {
        if (!TextUtils.isEmpty(str)) {
            return "" + str.trim().replace("null", "");
        } else {
            return str;
        }
    }
    /**
     * 获取专业类别
     */
    public  static String getSpeCode(String type) {
        type = getDisStr(type).trim();
        switch (type) {
            case "01":
                type = "公共场所";
                break;
            case "02":
                type = "生活饮用水";
                break;
            case "03":
                type = "职业卫生";
                break;
            case "04":
                type = "放射卫生";
                break;
            case "05":
                type = "学校卫生";
                break;
            case "06":
                type = "医疗卫生";
                break;
            case "0701":
                type = "消毒产品单位";
                break;
            case "0703":
                type = "传染病防治";
                break;
            case "0704":
                type = "餐饮具集中消毒单位";
                break;
            case "08":
                type = "血液安全";
                break;
            case "09":
                type = "计划生育";
                break;
            default:
                type = "-";
        }
        return type;
    }

}