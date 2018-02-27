package com.example.commonlibrary.utils;

import android.support.annotation.Nullable;
import android.text.Html;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * desc:
 * <p>
 * date:2016/12/20 0020
 * time:下午 2:43
 */

public class StringUtil {

    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,1,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }


    public static String notNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }


    public static boolean isErrorMsg(String msg) {
        if (!isEmpty(msg)) {
            msg = msg.toLowerCase();
            if (msg.contains("exception") ||
                    msg.contains("error") ||
                    msg.contains("wrong") ||
                    msg.contains("异常")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 密码合法性检查
     *
     * @param psd
     * @return 0-ok,1-长度为0,2-格式不正确
     */
    public static int checkPsd(String psd) {
        if (isEmpty(psd)) {
            return 1;
        }
        if (psd.length() < 6 || psd.length() > 20) {
            return 1;
        }
        Pattern p = Pattern.compile("[a-zA-Z0-9]{6,20}");
        Matcher m = p.matcher(psd);
        return m.matches() ? 0 : 2;
    }

    public static boolean isCodeNumber(String code) {
        if (isEmpty(code))
            return false;

        if (code.length() == 6) {
            Pattern p = Pattern.compile("[0-9]{6}");
            Matcher m = p.matcher(code);
            return m.matches();
        } else {
            return false;
        }
    }

    /**
     * 判断是否是手机号码
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        if (isEmpty(phoneNumber)) {
            return false;
        }
        if (phoneNumber.length() > 0 && phoneNumber.length() <= 11)
            return true;
        return false;
    }

    /**
     * 判断是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(@Nullable CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }

    private static DecimalFormat df = new DecimalFormat("######0.00");

    /**
     * double只保留两位小数
     *
     * @param str
     * @return
     */
    public static String formatDouble(double str) {
        return df.format(str);
    }

    /**
     * html转义符转化为string
     *
     * @param str
     * @return
     */
    public static String htmlCheck(String str) {
        if (isEmpty(str)) {
            return "";
        } else {
            return Html.fromHtml(str).toString();
        }
    }
}
