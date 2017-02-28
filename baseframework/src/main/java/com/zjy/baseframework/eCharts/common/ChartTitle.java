package com.zjy.baseframework.eCharts.common;

import com.zjy.baseframework.eCharts.enums.LabelXLocation;
import com.zjy.baseframework.eCharts.enums.LabelYLocation;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 标题
/// </summary>
public class ChartTitle
{
    /// <summary>
    /// 是否显示
    /// </summary>
    public Boolean show;

    /// <summary>
    /// 主标题
    /// </summary>
    public String text;
    /// <summary>
    /// 副标题
    /// </summary>
    public String subtext;

    /// <summary>
    /// 标题链接
    /// </summary>
    public String link;

    /// <summary>
    /// 打开位置
    /// </summary>
    public String target;

    /// <summary>
    /// x轴位置(可以为left ,center, right)
    /// </summary>
    public String x;

    /// <summary>
    /// y轴位置(可以为top, middle, bottom)
    /// </summary>
    public String y;

    /// <summary>
    /// 构造函数
    /// </summary>
    public ChartTitle()
    { this(null, null);}
    /// <summary>
    /// 构造函数
    /// </summary>
    /// <param name="title">标题</param>
    public ChartTitle(String title)
    { this(title, null);}
    /// <summary>
    /// /// 构造函数
    /// </summary>
    /// <param name="title">标题</param>
    /// <param name="subTitle">副标题</param>
    public ChartTitle(String title, String subTitle)
    { this(title, subTitle, LabelXLocation.Center, LabelYLocation.Bottom);}
    /// <summary>
    /// /// 构造函数
    /// </summary>
    /// <param name="title">标题</param>
    /// <param name="x">x轴信息</param>
    /// <param name="y">y轴信息</param>
    public ChartTitle(String title, LabelXLocation x, LabelYLocation y)
    { this(title, null, x, y);}
    /// <summary>
    /// /// 构造函数
    /// </summary>
    /// <param name="title">标题</param>
    /// <param name="subTitle">副标题</param>
    /// <param name="x">x轴信息</param>
    /// <param name="y">y轴信息</param>
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
