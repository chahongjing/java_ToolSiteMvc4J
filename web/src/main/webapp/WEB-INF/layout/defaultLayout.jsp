<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title<sitemesh:write property='title' /></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="${ctx}/favicon.ico" type="image/x-icon" />
    <link href="${ctx}/bootstrap/css/bootstrap.css" rel="stylesheet" />
    <link href="${ctx}/bootstrap/css/font-awesome.css" rel="stylesheet" />
    <link href="${ctx}/bootstrap/css/main.css" rel="stylesheet" />
    <sitemesh:write property='head'/>
</head>
<body data-ng-app="myApp">
<div class="main">
    <div class="head">
        <div class="logo">
            <div class="logoimg fl"><a href="${ctx}" title="首页"></a></div>
            <span class="fl title pl10"></span>
        </div>
        <div class="info">
            <ul>
                <li>
                    <a class="fr logout licontent" href="javascript:void(0)" data-ng-click="logout()">
                        <i class="fa fa-power-off"></i>&nbsp;注销</a>
                </li>
                <li>
                    <a class="fr logout licontent" href="javascript:void(0)" data-ng-click="goBack()">
                        <i class="fa fa-reply"></i>&nbsp;返回</a>
                </li>
                <li>
                <span class="licontent">
                    姓名：
                </span>
                </li>
                <li class="menu" data-ng-hide="">
                    <div><i class="fa fa-list"></i></div>
                    <ul>
                        <%--<li><a href="${ctx}/teacher/teacherlist" data-ng-show="${userType} == ${yongHuType}">教师管理</a></li>--%>
                        <%--<li><a href="${ctx}/student/studentlist" data-ng-show="${userType} == ${yongHuType}">学生管理</a></li>--%>
                        <%--<li><a href="${ctx}/paper/paperlist" data-ng-show="${userType} == ${yongHuType}">试卷管理</a></li>--%>
                        <%--<li><a href="${ctx}/paper/studentpaperlist"--%>
                        <%--data-ng-show="${userType} == ${teacherType} || ${userType} == ${studentType}">考生作答</a></li>--%>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div class="body">
        <div class="menu"></div>
        <div class="right-content">
            <div class="right-content-main">
                <h2>测试</h2>
                <sitemesh:write property='body'/>
                <div class="loadingmask">
                    <div class="info">
                        <img src="${ctx}/bootstrap/images/loading.gif"/>
                        <p class="mt10">数据处理中，请等待...</p>
                    </div>
                </div>
            </div>
            <div class="footer"></div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${ctx}/js/Utility.js"></script>
<script type="text/javascript">var ctx = '<%= request.getContextPath() %>';</script>
<sitemesh:write property='jsSection'/>
</body>
</html>
