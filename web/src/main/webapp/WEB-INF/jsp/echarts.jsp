<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
</head>
<body>
<div class="row">
    <div class="col-sm-6">
        <!-- 折线 -->
        <div id="dashBoard" style="width: 100%; height: 400px;"></div>
    </div>
    <div class="col-sm-6">
        <!-- 折线 -->
        <div id="lineChart" style="width: 100%; height: 400px;"></div>
    </div>
</div>
<div class="row">
    <div class="col-sm-6">
        <!-- 柱状图 -->
        <div id="histogram" style="width: 100%; height: 400px;"></div>
    </div>
    <div class="col-sm-6">
        <!-- 雷达图 -->
        <div id="radar" style="width: 100%; height: 400px;"></div>
    </div>
</div>
<div class="row">
    <div class="col-sm-6">
        <!-- 饼图 -->
        <div id="pie" style="width: 100%; height: 400px;"></div>
    </div>
    <div class="col-sm-6">
    </div>
</div>
<jsSection>
    <script src="${ctx}/js/echarts.min.js"></script>
    <script>
        // 准备数据
        var optionDashBoard = ${dashBoard};
        var optionLineChart = ${lineChart};
        var optionHistogram = ${histogram};
        var optionRadar = ${radar};
        var optionPie = ${pie};

        // dom结构
        var dashBoard = echarts.init(document.getElementById('dashBoard'));
        var lineChart = echarts.init(document.getElementById('lineChart'));
        var histogram = echarts.init(document.getElementById('histogram'));
        var radar = echarts.init(document.getElementById('radar'));
        var pie = echarts.init(document.getElementById('pie'));

        // 页面加载
        $(function () {
            initialDashBoard();
            initialLineChart();
            initialHistogram();
            initialRadar();
            initialPie();
        });

        function initialDashBoard() {
            // 基于准备好的dom，初始化仪表盘实例
            dashBoard.setOption(optionDashBoard, true);
        }
        function initialLineChart() {
            // 基于准备好的dom，初始化折线图实例
            lineChart.setOption(optionLineChart, true);
        }
        function initialHistogram() {
            // 基于准备好的dom，初始化柱状图实例
            histogram.setOption(optionHistogram, true);
            //histogram.setOption(option, true);
        }
        function initialRadar() {
            // 基于准备好的dom，初始化雷达实例
            radar.setOption(optionRadar, true);
        }
        function initialPie() {
            // 基于准备好的dom，初始化饼图实例
            pie.setOption(optionPie, true);
        }

        // 图表自适应大小
        window.onresize = function () {
            dashBoard.resize();
            lineChart.resize();
            histogram.resize();
            radar.resize();
            pie.resize();
        }
    </script>
</jsSection>
</body>
</html>