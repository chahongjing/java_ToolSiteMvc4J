package com.zjy.baseframework.eCharts.common;

/**
 * 轴标信息
 */
public class AxisLabel {
    /**
     * 格式化器
     */
    private String formatter;

    /**
     * 旋转角度
     */
    private int rotate;

    /**
     * 每个刻度间隔
     */
    private int interval;

    /**
     * 构造函数
     */
    public AxisLabel() {
        this(null);
    }


    /**
     * 构造函数
     *
     * @param formatter 格式化器
     */
    public AxisLabel(String formatter) {
        this.formatter = formatter;
        interval = 0;
        rotate = 0;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
