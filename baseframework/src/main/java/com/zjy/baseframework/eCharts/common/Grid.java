package com.zjy.baseframework.eCharts.common;

/**
 * Created by chahongjing on 2017/2/28.
 */

/**
 * 网格
 */
public class Grid
{
    /**
     * 左边距
     */
    private String left;

    /**
     * 右边距
     */
    private String right;
    /**
     * 上边距
     */
    private String top;
    /**
     * 下边距
     */
    private String bottom;

    /**
     * 是否包含标签
     */
    private Boolean containLabel;

    /**
     * 构造函数
     */
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
