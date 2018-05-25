<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><sitemesh:write property='title'/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${ctx}/favicon.ico" rel="shortcut icon" type="image/x-icon"/>
    <link href="${ctx}/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="${ctx}/bootstrap/css/font-awesome.css" rel="stylesheet"/>
    <link href="${ctx}/bootstrap/css/common.css" rel="stylesheet"/>
    <sitemesh:write property='head'/>
</head>
<body data-ng-app="myApp" data-ng-controller="mainCtrl" data-ng-init="init()">
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
                    姓名：${user.userName}
                </span>
                </li>
            </ul>
        </div>
    </div>
    <div class="body">
        <div class="menu">
            <div class="slide-menu">
                <ul class="first-menu">
                    <li data-ng-repeat="item in model.menu" data-ng-class="{'selected': item.isSelected}"
                        data-ng-click="clickFirstMenu(item)" ng-repeat-finish="afterRender()">
                        <div>
                            <i class="fa {{item.icon}}"></i>
                            <span data-ng-bind="item.name"></span>
                            <b class="fa" data-ng-class="{'fa-angle-down':item.isSelected,'fa-angle-right':!item.isSelected}"></b>
                        </div>
                        <ul class="sub-menu" data-ng-class="{'show':item.isSelected}" data-ng-style="{'height':getMenuHeight(item)}">
                            <li data-ng-repeat="sub in item.list" data-ng-class="{'selected': sub.isSelected}"
                                data-ng-click="clickSecondMenu(item, sub, $event)">
                                <a href="javascript:void(0)">
                                    <i class="fa {{sub.icon}}"></i>
                                    <span data-ng-bind="sub.name"></span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <div class="right-main">
            <div class="bread"></div>
            <div class="right-content">
                <h2>测试</h2>
                <sitemesh:write property='body'/>
            </div>
            <div class="footer"></div>
        </div>
    </div>
</div>
<div data-ng-include="'${ctx}/js/angular/templates/commonPart.html'"></div>
<div loading-mask text="model.loadingText" is-show="model.isShowLoading" reload="model.loadingReload"></div>
<script src="${ctx}/js/jquery-3.3.1.js" type="text/javascript"></script>
<script type="text/javascript">var ctx = '<%= request.getContextPath() %>';</script>
<script src="${ctx}/js/Utility.js" type="text/javascript"></script>
<script src="${ctx}/js/angular/angular.js"></script>
<script src="${ctx}/js/angular/angular_main.js"></script>
<script src="${ctx}/js/angular/commonService.js"></script>
<script src="${ctx}/js/angular/directives/ng_repeat_finish.js"></script>
<script src="/ToolSiteMvc4J/js/angular/directives/loadingmask.js"></script>
<sitemesh:write property='jsSection'/>
</body>
</html>
