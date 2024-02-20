package com.wangcl.utils;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wcl
 */
public class IdCardUtils {

    /**
     * 正则表达式：验证港澳居民通行证 H/M + 10位或8位数字
     */
    public static final String REGEX_HK_CARD = "^[HMhm]{1}([0-9]{10}|[0-9]{8})$";

    /**
     * 正则表达式：验证台湾居民通行证 新版8位或1位英文+7位数字
     */
    public static final String REGEX_TW_CARD = "^(\\d{8}|^[a-zA-Z]{1}(\\d{7}))$";

    /**
     * 身份证
     */
    public static final String ID_CARD = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

    /**
     * 校验港澳通行证
     *
     * @param HMNo 港澳通行证号
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isHMCard(String HMNo) {
        //校验非空
        if (StringUtils.isBlank(HMNo)) {
            return false;
        }
        return HMNo.matches(REGEX_HK_CARD);
    }

    /**
     * 身份证
     *
     * @param idCard 校验内容
     * @return 符合正则表达式返回true，否则返回false
     */
    public static boolean isIdCard(String idCard) {
        boolean flag = false;
        if (null != idCard && !"".equals(idCard.trim()) && idCard.trim().length() == 18) {
            Pattern pattern = Pattern.compile(ID_CARD);
            Matcher matcher = pattern.matcher(idCard.trim());
            flag = matcher.matches();
        }
        return flag;
    }

    /**
     * 校验台湾通行证
     * 规则 新版8位或18位数字,旧版10位数字 + 英文字母
     *
     * @param TWNo 台湾通行证号
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isTWCard(String TWNo) {
        //校验非空
        if (StringUtils.isBlank(TWNo)) {
            return false;
        }
        return TWNo.matches(REGEX_TW_CARD);
    }

    public static void main(String[] args) {
//        System.out.println(isTWCard("12345678"));
//        System.out.println(isTWCard("g1234568"));
//        System.out.println(isTWCard("g12345618"));
        System.out.println(isIdCard("M15451451"));
    }
}
