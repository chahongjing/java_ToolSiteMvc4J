package com.zjy.baseframework.eCharts.common;

/**
 * 指针,标识颜色
 */
public class ItemStyle
{
    /**
     * 常规
     */
    private ItemStyleNormal normal;
    private Emphasis emphasis;

    public ItemStyleNormal getNormal() {
        return normal;
    }

    public void setNormal(ItemStyleNormal normal) {
        this.normal = normal;
    }

    public Emphasis getEmphasis() {
        return emphasis;
    }

    public void setEmphasis(Emphasis emphasis) {
        this.emphasis = emphasis;
    }
}
