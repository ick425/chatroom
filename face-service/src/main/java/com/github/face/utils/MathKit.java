package com.github.face.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * 计算工具类
 *
 * @author wo
 */
public class MathKit {

    /**
     * 格式化保留所有整数和两位小数，空的位数补0
     */
    private static final DecimalFormat DF_2 = new DecimalFormat("0.00");
    /**
     * 格式化保留所有整数和两位小数，空的位数补0，百分比形式
     */
    private static final DecimalFormat DF_2_RATIO = new DecimalFormat("0.00%");

    /**
     * 除法计算，截取保留两位小数，不做进位处理
     * <p> 被除数和除数任何一个为0，结果返回0 </p>
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 商，保留两位小数，BigDecimal类型
     * @throws NumberFormatException 入参格式错误
     */
    public static BigDecimal divScale2(String v1, String v2) {
        return divScale(v1, v2, 2);
    }

    /**
     * 除法计算，截取保留两位小数，不做进位处理
     * <p> 被除数和除数任何一个为0，结果返回0 </p>
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 商，保留两位小数，BigDecimal类型
     * @throws NumberFormatException 入参格式错误
     */
    public static BigDecimal divScale2(Number v1, Number v2) {
        return divScale(v1, v2, 2);
    }


    public static BigDecimal divScale(String v1, String v2, Integer scale) {
        DecimalFormat format = new DecimalFormat();
        Number n1;
        try {
            n1 = format.parse(v1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Number n2;
        try {
            n2 = format.parse(v2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return divScale(n1, n2, scale);
    }

    /**
     * 除法计算，截取保留指定位数小数，不做进位处理
     * <p> 被除数和除数任何一个为0，结果返回0 </p>
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 保留几位小数
     * @return 商，BigDecimal类型
     * @throws NumberFormatException 入参格式错误
     */
    public static BigDecimal divScale(Number v1, Number v2, Integer scale) {
        if (v1.intValue() == 0 || v2.intValue() == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.divide(b2, scale, RoundingMode.DOWN);
    }

    /**
     * 除法计算，截取保留两位小数，不做进位处理
     * <p> 被除数和除数任何一个为0，结果返回0 </p>
     * <p> 结果一定是两位小数，例如：0.00, 2.66, 3.20 ,200.00 </p>
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 商，保留两位小数，字符串类型
     * @throws NumberFormatException 入参格式错误
     */
    public static String divScale2Str(String v1, String v2) {
        BigDecimal div = divScale2(v1, v2);
        if (BigDecimal.ZERO.compareTo(div) == 0) {
            return "0.00";
        }
        return DF_2.format(div);
    }

    /**
     * 除法计算，截取保留两位小数，不做进位处理，返回百分比形式
     * <p> 被除数和除数任何一个为0，结果返回0 </p>
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 商，保留两位小数，字符串类型
     * @throws NumberFormatException 入参格式错误
     */
    public static String divScale2Ratio(String v1, String v2) {
        BigDecimal div = divScale(v1, v2, 4);
        if (BigDecimal.ZERO.compareTo(div) == 0) {
            return "0.00%";
        }
        return DF_2_RATIO.format(div);
    }

    /**
     * 除法计算，截取保留两位小数，不做进位处理，返回百分比形式
     * <p> 被除数和除数任何一个为0，结果返回0 </p>
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 商，保留两位小数，字符串类型
     * @throws NumberFormatException 入参格式错误
     */
    public static String divScale2Ratio(Number v1, Number v2) {
        BigDecimal div = divScale(String.valueOf(v1), String.valueOf(v2), 4);
        if (BigDecimal.ZERO.compareTo(div) == 0) {
            return "0.00%";
        }
        return DF_2_RATIO.format(div);
    }

    /**
     * 乘法计算，截取保留指定位数，不做进位处理
     *
     * @param scale  保留小数位数
     * @param values 乘数
     * @return 乘积
     */
    public static BigDecimal multiply(Integer scale, Number... values) {
        if (values == null || values.length == 0) {
            return BigDecimal.ZERO.setScale(scale, RoundingMode.DOWN);
        }
        Number value = values[0];
        if (value == null) {
            return BigDecimal.ZERO.setScale(scale, RoundingMode.DOWN);
        }
        BigDecimal result = new BigDecimal(value.toString());
        for (int i = 1; i < values.length; i++) {
            value = values[i];
            if (value == null) {
                return BigDecimal.ZERO.setScale(scale, RoundingMode.DOWN);
            }
            BigDecimal decimal = new BigDecimal(value.toString());
            result = result.multiply(decimal);
        }
        return result.setScale(scale, RoundingMode.DOWN);
    }

    public static String multiplyToStr(Integer scale, Number... values) {
        return multiply(scale, values).toPlainString();
    }

    public static void main(String[] args) {

//        System.out.println(multiplyToStr(4, 0.36666, 5));
//        System.out.println(multiplyToStr(4, 0.5, 5));
//        System.out.println(multiplyToStr(4, 0, 5));
        System.out.println(multiplyToStr(4, 0, 0));
//        System.out.println(multiplyToStr(4, 1, 5));

//        BigDecimal decimal = divScale("2058", "312360", 4);
//        System.out.println(decimal);
//        System.out.println(DF_2_RATIO.format(decimal));
//        System.out.println();
//
//        BigDecimal bigDecimal = BigDecimal.valueOf(000.036);
//        System.out.println(bigDecimal);
//        System.out.println(DF_2_RATIO.format(bigDecimal));
//        System.out.println();
//
//        BigDecimal decimal1 = BigDecimal.valueOf(000.036);
//        System.out.println(decimal1);
//        System.out.println();
//
//        System.out.println(divScale2Str("1", "2"));
    }

}
