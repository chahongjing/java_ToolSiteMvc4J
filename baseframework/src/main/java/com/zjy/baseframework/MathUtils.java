package com.zjy.baseframework;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class MathUtils {
    // 默认除法运算精度
    private static final Integer DEF_DIV_SCALE = 0;

    /**
     * 提供精确的加法运算。
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static <T extends Number> double add(T value1, T value2) {
        Objects.requireNonNull(value1);
        Objects.requireNonNull(value2);
        BigDecimal b1 = new BigDecimal(value1.doubleValue());
        BigDecimal b2 = new BigDecimal(value2.doubleValue());
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static <T extends Number> double sub(T value1, T value2) {
        Objects.requireNonNull(value1);
        Objects.requireNonNull(value2);
        BigDecimal b1 = new BigDecimal(value1.doubleValue());
        BigDecimal b2 = new BigDecimal(value2.doubleValue());
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static <T extends Number> double mul(T value1, T value2) {
        Objects.requireNonNull(value1);
        Objects.requireNonNull(value2);
        BigDecimal b1 = new BigDecimal(value1.doubleValue());
        BigDecimal b2 = new BigDecimal(value2.doubleValue());
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时， 精确到小数点以后10位，以后的数字四舍五入。
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return 两个参数的商
     */
    public static <T extends Number> double divide(T dividend, T divisor) {
        return divide(dividend, divisor, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @param scale    表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static <T extends Number> double divide(T dividend, T divisor, int scale) {
        Objects.requireNonNull(dividend);
        Objects.requireNonNull(divisor);
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(dividend.doubleValue());
        BigDecimal b2 = new BigDecimal(divisor.doubleValue());
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 提供指定数值的（精确）小数位四舍五入处理。
     *
     * @param value 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static <T extends Number> double round(T value, int scale) {
        Objects.requireNonNull(value);
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(value.doubleValue());
        b.setScale(scale, RoundingMode.HALF_UP);
        return b.doubleValue();
    }
}
