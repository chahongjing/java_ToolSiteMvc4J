package com.zjy.baseframework.eCharts.common;

import com.zjy.baseframework.eCharts.enums.LabelXLocation;
import com.zjy.baseframework.eCharts.enums.LabelYLocation;

/**
 * 标题
 */
public class ChartTitle
{
    /**
     * 是否显示
     */
    private Boolean show;

    /**
     * 主标题
     */
    private String text;
    /**
     * 副标题
     */
    private String subtext;

    /**
     * 标题链接
     */
    private String link;

    /**
     * 打开位置
     */
    private String target;

    /**
     * x轴位置(可以为left ,center, right)
     */
    private String x;

    /**
     * y轴位置(可以为top, middle, bottom)
     */
    private String y;

    /**
     * 构造函数
     */
    public ChartTitle()
    { this(null, null);}

    /**
     * 构造函数
     * @param title 标题
     */
    public ChartTitle(String title)
    { this(title, null);}

    /**
     * 构造函数
     * @param title 标题
     * @param subTitle 副标题
     */
    public ChartTitle(String title, String subTitle)
    { this(title, subTitle, LabelXLocation.Center, LabelYLocation.Bottom);}

    /**
     * 构造函数
     * @param title 标题
     * @param x x轴信息
     * @param y y轴信息
     */
    public ChartTitle(String title, LabelXLocation x, LabelYLocation y)
    { this(title, null, x, y);}

    /**
     * 构造函数
     * @param title 标题
     * @param subTitle 副标题
     * @param x x轴信息
     * @param y y轴信息
     */
    public ChartTitle(String title, String subTitle, LabelXLocation x, LabelYLocation y)
    {
        show = true;
        text = title;
        subtext = subTitle;
        this.x = x.toString().toLowerCase();
        this.y = y.toString().toLowerCase();
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
