package com.zjy.baseframework.eCharts.common;

/**
 * Created by Administrator on 2017/2/28.
 */
/// <summary>
/// 轴标信息
/// </summary>
public class AxisLabel {
    /// <summary>
    /// 格式化器
    /// </summary>
    public String formatter;

    /// <summary>
    /// 旋转角度
    /// </summary>
    public int rotate;

    /// <summary>
    /// 每个刻度间隔
    /// </summary>
    public int interval;

    /// <summary>
    /// 构造函数
    /// </summary>
    public AxisLabel() {
        this(null);
    }

    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="formatter">格式化器</param>
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
