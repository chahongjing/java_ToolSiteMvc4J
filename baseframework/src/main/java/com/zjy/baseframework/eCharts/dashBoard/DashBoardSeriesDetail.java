package com.zjy.baseframework.eCharts.dashBoard;

/**
 * Created by chahongjing on 2017/2/28.
 */
/// <summary>
/// 详细信息
/// </summary>
public class DashBoardSeriesDetail
{
    /// <summary>
    /// 格式化器
    /// </summary>
    private String formatter;
    /// <summary>
    /// 是否显示
    /// </summary>
    private Boolean show ;

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }
}