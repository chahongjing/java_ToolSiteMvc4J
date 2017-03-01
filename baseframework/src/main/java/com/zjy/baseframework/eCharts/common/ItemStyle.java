package com.zjy.baseframework.eCharts.common;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 指针,标识颜色
/// </summary>
public class ItemStyle
{
    /// <summary>
    /// 常规
    /// </summary>
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
