package com.zjy.baseframework.eCharts.common;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 网格
/// </summary>
public class Grid
{
    /// <summary>
    /// 左边距
    /// </summary>
    public String left;

    /// <summary>
    /// 右边距
    /// </summary>
    public String right;
    /// <summary>
    /// 上边距
    /// </summary>
    public String top;
    /// <summary>
    /// 下边距
    /// </summary>
    public String bottom;

    /// <summary>
    /// 是否包含标签
    /// </summary>
    public Boolean containLabel;

    /// <summary>
    /// 构造函数
    /// </summary>
    public Grid()
    {
        containLabel = true;
        left = "3%";
        right = "15%";
        top = "10%";
        bottom = "10%";
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    public Boolean getContainLabel() {
        return containLabel;
    }

    public void setContainLabel(Boolean containLabel) {
        this.containLabel = containLabel;
    }
}
