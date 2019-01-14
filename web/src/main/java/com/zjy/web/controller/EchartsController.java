package com.zjy.web.controller;

import com.alibaba.fastjson.JSON;
import com.zjy.baseframework.BaseResult;
import com.zjy.baseframework.NameValuePair;
import com.zjy.baseframework.eCharts.common.*;
import com.zjy.baseframework.eCharts.dashBoard.DashBoard;
import com.zjy.baseframework.eCharts.dashBoard.DashBoardSeries;
import com.zjy.baseframework.eCharts.dashBoard.DashBoardSeriesData;
import com.zjy.baseframework.eCharts.dashBoard.DashBoardSeriesDetail;
import com.zjy.baseframework.eCharts.enums.*;
import com.zjy.baseframework.eCharts.histogram.Histogram;
import com.zjy.baseframework.eCharts.histogram.HistogramSeries;
import com.zjy.baseframework.eCharts.histogram.HistogramSeriesData;
import com.zjy.baseframework.eCharts.lineChart.LineChart;
import com.zjy.baseframework.eCharts.lineChart.LineChartSeries;
import com.zjy.baseframework.eCharts.lineChart.LineChartSeriesData;
import com.zjy.baseframework.eCharts.normalDistribution.NormalDistribution;
import com.zjy.baseframework.eCharts.pie.Pie;
import com.zjy.baseframework.eCharts.pie.PieSeries;
import com.zjy.baseframework.eCharts.pie.PieSeriesData;
import com.zjy.baseframework.eCharts.radar.IndicatorData;
import com.zjy.baseframework.eCharts.radar.Radar;
import com.zjy.baseframework.eCharts.radar.RadarSeries;
import com.zjy.baseframework.eCharts.radar.RadarSeriesData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by chahongjing on 2017/2/28.
 */
@Controller
@RequestMapping("/echarts")
public class EchartsController {

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("echarts");

        Map<String, Object> chartsOption = getChartsOptionData();
        for (Map.Entry<String, Object> entry : chartsOption.entrySet()) {
            mv.addObject(entry.getKey(), entry.getValue());
        }
        return mv;
    }

    @RequestMapping("/getChartsOption")
    @ResponseBody
    public BaseResult<Map<String, Object>> getChartsOption() {
        Map<String, Object> chartsOptionData = getChartsOptionData();
        return BaseResult.ok(chartsOptionData);
    }

    private Map<String, Object> getChartsOptionData() {
        Map<String, Object> result = new HashMap<>();
        Map<String, Float> list = new HashMap<>();
        list.put("一季度", 0.3f);
        list.put("二季度", 0.8f);
        list.put("三季度", 0.5f);
        list.put("四季度", 0.95f);

        //region 仪表盘
        // 仪表盘, 标题
        String xueXi = "学习轨迹图";
        String chengZhang = "成长轨迹图";
        String title = "学生的排名情况";
        String title1 = "学习仪表盘";
        DashBoard db = new DashBoard(new ChartTitle(title1, title));
        String formatter = "{value}%";
        // 鼠标hover提示
        db.setTooltip(new ChartToolTip("{b}<br/>排名 : {c}%"));
        // 仪表盘数据, x轴名称, 仪表盘名称, 值
        List<DashBoardSeriesData> ll = new ArrayList<>();
        for (Map.Entry<String, Float> entry : list.entrySet()) {
            DashBoardSeriesData d = new DashBoardSeriesData(String.valueOf(Math.round(entry.getValue() * 100)), entry.getKey());
            d.setItemStyle(new ItemStyle());
            d.getItemStyle().setNormal(new ItemStyleNormal());
            d.getItemStyle().getNormal().setColor(DashBoard.Colors.get(ll.size()));
            ll.add(d);
        }

        DashBoardSeries dbs = new DashBoardSeries(title1, ll);
        // 显示值, 是否显示, 值格式化器
        dbs.setDetail(new DashBoardSeriesDetail());
        dbs.getDetail().setShow(false);
        dbs.getDetail().setFormatter(formatter);
        // 刻度格式化器
        dbs.setAxisLabel(new AxisLabel(formatter));
        db.getSeries().add(dbs);
        result.put("dashBoard", JSON.toJSONString(db));
        //endregion

        //region 折线图
        // 折线图, 标题
        LineChart lc = new LineChart(new ChartTitle(chengZhang, title));
        List<LegendData> legendL = new ArrayList<>();
        legendL.add(new LegendData(xueXi));
        legendL.add(new LegendData(chengZhang));
        lc.setLegend(new Legend(legendL));
        // 鼠标hover提示
        lc.setTooltip(new ChartToolTip("{b}<br/>{a0}排名 : {c0}%<br/>{a1}排名 : {c1}%", TriggerType.Axis));
        // x轴相关信息
        lc.setXAxis(new ArrayList<>());
        lc.getXAxis().add(new XAxis("考试编号", new ArrayList<>(list.keySet())));
        lc.getXAxis().get(0).setAxisLabel(new AxisLabel());
        lc.getXAxis().get(0).getAxisLabel().setRotate(8);
        // y轴相关信息
        lc.setYAxis(new ArrayList<>());
        lc.getYAxis().add(new YAxis("排名百分比", 10f, 10f, 100f));
        lc.getYAxis().get(0).setAxisLabel(new AxisLabel(formatter));

        List<LineChartSeriesData> li1 = new ArrayList<>();
        List<LineChartSeriesData> li2 = new ArrayList<>();
        int flag = 0;
        for (Map.Entry<String, Float> entry : list.entrySet()) {
            LineChartSeriesData lcsd = new LineChartSeriesData(String.valueOf(Math.round(entry.getValue() * 100)));
            lcsd.setSymbolSize(14);
            if (flag < 3) {
                ItemStyle itemStyle = new ItemStyle();
                ItemStyleNormal itemStyleNormal = new ItemStyleNormal();
                itemStyle.setNormal(itemStyleNormal);
                if(flag %2 == 0) {
                    itemStyleNormal.setColor("#ff9800");
                } else {
                    itemStyleNormal.setColor("green");
                }
                lcsd.setItemStyle(itemStyle);
            }
            flag++;
            li1.add(lcsd);
            LineChartSeriesData lcsd2 = new LineChartSeriesData(String.valueOf(Math.round(entry.getValue() * 100)));
            li2.add(lcsd2);
        }
        // 折线图的值
        LineChartSeries line1 = new LineChartSeries(xueXi, li1);
        ItemStyle itemStyle = new ItemStyle();
        line1.setItemStyle(itemStyle);
        ItemStyleNormal itemStyleNormal = new ItemStyleNormal();
        itemStyle.setNormal(itemStyleNormal);
        LineStyle lineStyle = new LineStyle();
        itemStyleNormal.setLineStyle(lineStyle);
        lineStyle.setColor("#2A3CC9");
        lc.getSeries().add(line1);
        Collections.reverse(li2);
        // 折线图的值
        lc.getSeries().add(new LineChartSeries(chengZhang, li2));
        result.put("lineChart", JSON.toJSONString(lc));
        //endregion

        //region 柱状图
        // 柱状图, 标题
        Histogram his = new Histogram(new ChartTitle(xueXi, title));
        List<LegendData> legendH = new ArrayList<>();
        legendH.add(new LegendData(xueXi));
        legendH.add(new LegendData(chengZhang));
        his.setLegend(new Legend(legendH));
        // 鼠标hover提示
        his.setTooltip(new ChartToolTip("{b} <br/>{a0}排名 : {c0}%<br/>{a1}排名 : {c1}%", TriggerType.Axis));
        his.getTooltip().setAxisPointer(new AxisPointer(LineType.Shadow));
        // x轴相关信息, 数据值
        his.setXAxis(new ArrayList<>());
        his.getXAxis().add(new XAxis("考试编号", AxisValueType.Category, new ArrayList<>(list.keySet())));
        his.getXAxis().get(0).setBoundaryGap(true);
        his.getXAxis().get(0).setAxisTick(new AxisTick(true));
        // y轴相关信息
        his.setYAxis(new ArrayList<>());
        his.getYAxis().add(new YAxis("排名百分比", AxisValueType.Value, 10f, 10f, 100f));
        his.getYAxis().get(0).setAxisLabel(new AxisLabel(formatter));

        List<HistogramSeriesData> hsd1 = new ArrayList<>();
        List<HistogramSeriesData> hsd2 = new ArrayList<>();
        for (Map.Entry<String, Float> entry : list.entrySet()) {
            HistogramSeriesData lcsd = new HistogramSeriesData(String.valueOf(Math.round(entry.getValue() * 100)));
            hsd1.add(lcsd);
            HistogramSeriesData lcsd2 = new HistogramSeriesData(String.valueOf(Math.round(entry.getValue() * 100)));
            hsd2.add(lcsd2);
        }
        // 柱状图的值
        his.getSeries().add(new HistogramSeries(xueXi, hsd1));
        Collections.reverse(li2);
        // 柱状图的值
        his.getSeries().add(new HistogramSeries(chengZhang, hsd2));

        result.put("histogram", JSON.toJSONString(his));
        //endregion

        //region 雷达图
        // 雷达图, 标题, 副标题, 鼠标提示为item
        Radar radar = new Radar(new ChartTitle(xueXi, title));
        List<LegendData> legend = new ArrayList<>();
        legend.add(new LegendData("预算分配"));

        legend.add(new LegendData("实际开销"));
        // 每项标题
        radar.setLegend(new Legend(legend));
        radar.getLegend().setOrient(OrientType.Vertical.toString().toLowerCase());
        radar.getLegend().setLeft(LabelXLocation.Left.toString().toLowerCase());

        // 维度
        radar.getRadar().getIndicator().add(new IndicatorData("销售"));
        radar.getRadar().getIndicator().add(new IndicatorData("管理"));
        radar.getRadar().getIndicator().add(new IndicatorData("信息技术"));
        radar.getRadar().getIndicator().add(new IndicatorData("客服"));
        radar.getRadar().getIndicator().add(new IndicatorData("研发"));
        radar.getRadar().getIndicator().add(new IndicatorData("市场"));

        // 数据
        List<RadarSeriesData> radarData = new ArrayList<>();
        Random random = new Random(1000);
        legend.forEach(item -> {
            List<Float> l = new ArrayList<>();
            IntStream.range(1, 7).forEach(innerItem -> l.add(random.nextFloat() * 100));
            RadarSeriesData rsd = new RadarSeriesData(l, item.getName());
            radarData.add(rsd);
        });

        radar.getSeries().add(new RadarSeries(xueXi, radarData));
        result.put("radar", JSON.toJSONString(radar));
        //endregion

        //region 饼图
        // 饼图, 标题, 副标题, 鼠标提示为item
        Pie pie = new Pie(new ChartTitle(xueXi, title));
        // 鼠标hover提示
        pie.setTooltip(new ChartToolTip("{a} <br/>{b} : {c} ({d}%)"));
        // 每项标题
        List<LegendData> legendP = new ArrayList<>();
        legendP.add(new LegendData("直接访问"));
        legendP.add(new LegendData("邮件营销"));
        legendP.add(new LegendData("联盟广告"));
        legendP.add(new LegendData("视频广告"));
        legendP.add(new LegendData("搜索引擎"));

        pie.setLegend(new Legend(legendP));
        pie.getLegend().setOrient(OrientType.Vertical.toString().toLowerCase());
        pie.getLegend().setLeft(LabelXLocation.Left.toString().toLowerCase());

        // 数据
        List<PieSeriesData> pieData = new ArrayList<>();
        legendP.forEach(item -> {
            PieSeriesData rsd = new PieSeriesData(random.nextFloat() * 100, item.getName());
            pieData.add(rsd);
        });
        PieSeries series = new PieSeries(xueXi, pieData);
        series.setRadius("70%");
        series.setCenter(new ArrayList<>());
        series.getCenter().add("50%");
        series.getCenter().add("45%");
        series.setItemStyle(new ItemStyle());
        series.getItemStyle().setEmphasis(new Emphasis());
        series.getItemStyle().getEmphasis().setShadowBlur(10);
        series.getItemStyle().getEmphasis().setShadowColor("rgba(0, 0, 0, 0.5)");
        series.getItemStyle().getEmphasis().setShadowOffsetX(0);
        pie.getSeries().add(series);
        result.put("pie", JSON.toJSONString(pie));
        //endregion

        // region 正态分布
        NormalDistribution nd = new NormalDistribution(new ChartTitle("a", "b"));
        List<String> color = new ArrayList<>();
        color.add("#6087CF");
        nd.setColor(color);

        lc.setTooltip(new ChartToolTip(TriggerType.Item));
        // x轴相关信息, 数据值
        nd.setXAxis(new ArrayList<>());
        List<String> temp = Arrays.asList("[0,10]", "(10,20]", "(20,30]", "(30,40]", "(40,50]", "(50,60]", "(60,70]", "(70,80]", "(80,90]", "(90,100]");
        nd.getXAxis().add(new XAxis(StringUtils.EMPTY, AxisValueType.Category, temp));
        nd.getXAxis().get(0).setBoundaryGap(true);
        nd.getXAxis().get(0).setShow(true);
        nd.getXAxis().get(0).setAxisTick(new AxisTick(true));

        XAxis xAxis = new XAxis(StringUtils.EMPTY, AxisValueType.Value, new ArrayList<>());
        xAxis.setShow(false);
        SplitLine sl = new SplitLine();
        LineStyle ls = new LineStyle();
        ls.setType("dashed");
        sl.setLineStyle(ls);
        xAxis.setSplitLine(sl);
        xAxis.setMin(0f);
        xAxis.setMax(100f);
        xAxis.setInterval(10f);
        nd.getXAxis().add(xAxis);

        // y轴相关信息
        nd.setYAxis(new ArrayList<>());
        YAxis yAxis = new YAxis("人数", AxisValueType.Value, 1f, 2f, 20f);
        yAxis.setMin(0f);
        yAxis.setMinInterval(1f);
        nd.getYAxis().add(yAxis);
        yAxis = new YAxis("正态分布", AxisValueType.Value, null, null, null);
        sl = new SplitLine();
        ls = new LineStyle();
        ls.setType("dashed");
        sl.setLineStyle(ls);
        yAxis.setSplitLine(sl);
        nd.getYAxis().add(yAxis);
        nd.getYAxis().get(0).setAxisLabel(new AxisLabel(formatter));

        hsd1 = new ArrayList<>();
        List<String> tt = Arrays.asList("0", "1", "1", "0", "11", "4", "3", "1", "1", "0");
        HistogramSeriesData hsd;
        for (String s : tt) {
            hsd = new HistogramSeriesData(s);
            hsd1.add(hsd);
        }
        nd.getSeries().add(new HistogramSeries("新名称", hsd1));

        li1 = new ArrayList<>();
        LineChartSeriesData lcsd;
        List<NameValuePair<Integer, Double>> ndsd = new ArrayList<>();
        ndsd.add(new NameValuePair<>(20,0.031598805961163566));
        ndsd.add(new NameValuePair<>(29, 0.10908348502912833));
        ndsd.add(new NameValuePair<>(41, 0.30001130522517055));
        ndsd.add(new NameValuePair<>(42, 0.31579663312669004));
        ndsd.add(new NameValuePair<>(44, 0.34460888602692685));
        ndsd.add(new NameValuePair<>(45, 0.3572527440577784));
        ndsd.add(new NameValuePair<>(45, 0.3572527440577784));
        ndsd.add(new NameValuePair<>(46, 0.36848324918372943));
        ndsd.add(new NameValuePair<>(47, 0.3781403330778938));
        ndsd.add(new NameValuePair<>(48, 0.38608357867285736));
        ndsd.add(new NameValuePair<>(49, 0.3921956146701));
        ndsd.add(new NameValuePair<>(50, 0.3963850003307883));
        ndsd.add(new NameValuePair<>(50, 0.3963850003307883));
        ndsd.add(new NameValuePair<>(51, 0.3985885014804987));
        ndsd.add(new NameValuePair<>(53, 0.39693473096110676));
        ndsd.add(new NameValuePair<>(56, 0.37971579948649725));
        ndsd.add(new NameValuePair<>(60, 0.3333351771416598));
        ndsd.add(new NameValuePair<>(63, 0.2865994148782126));
        ndsd.add(new NameValuePair<>(67, 0.2182246931301749));
        ndsd.add(new NameValuePair<>(69, 0.1847041443497608));
        ndsd.add(new NameValuePair<>(77, 0.07735540294824718));
        ndsd.add(new NameValuePair<>(83, 0.0325319675819848));
        for (NameValuePair<Integer, Double> nvp : ndsd) {
            lcsd = new LineChartSeriesData(Arrays.asList(nvp.getName(), nvp.getValue()));
            li1.add(lcsd);
        }
        // 折线图的值
        line1 = new LineChartSeries("第二个", li1);
        line1.setShowSymbol(false);
        line1.setSmooth(true);
        line1.setxAxisIndex(1f);
        line1.setyAxisIndex(1f);
        ItemStyle iStyle = new ItemStyle();
        ItemStyleNormal isn = new ItemStyleNormal();
        isn.setColor("#CA4E4B");
        iStyle.setNormal(isn);
        line1.setLineStyle(iStyle);
        nd.getSeries().add(line1);
        result.put("normalDistribution", JSON.toJSONString(nd));
        // endregion

        return result;
    }
}
