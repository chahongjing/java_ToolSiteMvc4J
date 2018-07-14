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
<body>
<div class="main" id="myApp">
    <apphead></apphead>
    <div class="body">
        <appmenu></appmenu>
        <div class="right-main">
            <div class="bread"></div>
            <div class="right-content">
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
<script type="text/template" id="appHead">
    <div class="head">
        <div class="logo">
            <div class="logoimg fl"><a href="${ctx}" title="首页"></a></div>
            <span class="fl title pl10"></span>
        </div>
        <div class="info">
            <ul>
                <li>
                    <a class="fr logout licontent" href="javascript:void(0)" @click="logout()">
                        <i class="fa fa-power-off"></i>&nbsp;注销</a>
                </li>
                <li>
                    <a class="fr logout licontent" href="javascript:void(0)" @click="goBack()">
                        <i class="fa fa-reply"></i>&nbsp;返回</a>
                </li>
                <li>
                <span class="licontent">
                    姓名：${user.userName}
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
</script>
<script type="text/template" id="appMenu">
        <div class="menu">
    <div class="slide-menu">
    <ul class="first-menu">
    <li v-for="item in list" v-bind:class="{'selected': item.isSelected}" :title="JSON.stringify(item)"
    @click="clickFirstMenu(item)" ng-repeat-finish="afterRender()">
    <div>
    <i :class="'fa ' + item.data.icon"></i>
    <span v-text="item.name"></span>
    <b :class="{'fa fa-angle-down':item.isSelected,'fa fa-angle-right':!item.isSelected}"></b>
    </div>
    <ul class="sub-menu show" :class="{'sub-menu show':item.isSelected}" :style="{'height':getMenuHeight(item)}">
    <li v-for="sub in item.children" :class="{'selected': sub.isSelected}"
    @click="clickSecondMenu(item, sub, $event)">
    <a href="javascript:void(0)">
    <i :class="'fa ' + sub.data.icon"></i>
    <span v-text="sub.name"></span>
    </a>
    </li>
    </ul>
    </li>
    </ul>
    </div>
        </div>
 </script>
<script src="${ctx}/js/jquery-3.3.1.js" type="text/javascript"></script>
<script type="text/javascript">var ctx = '<%= request.getContextPath() %>';</script>
<script src="${ctx}/js/Utility.js" type="text/javascript"></script>
<script src="${ctx}/js/vue/vue.js"></script>
<script src="${ctx}/js/vue/vueService.js"></script>
<script>
    Vue.component('apphead', {
        template: '#appHead',
        methods: {
            // 退出登录
            logout: function () {
                window.Utility.get('/userinfo/logout.do').done(function (resp) {
                    if (resp.status == Constant.AjaxStatus.OK) {
                        window.location = commonSrv.getContext();
                    } else {
                        alert(resp.msg);
                    }
                }).fail(function () {
                    alert("注销失败！");
                });
            },
            showLoading: function (title) {
                $scope.model.loadingReload++;
                if (title) {
                    $scope.model.loadingText = 'title';
                }
                $scope.model.isShowLoading = true;
            },
            hideLoading: function () {
                $scope.model.loadingText = '';
                $scope.model.isShowLoading = false;
            },
            // 返回
            goBack: function () {
                history.go(-1);
            }
        }
    });
        Vue.component('appmenu', {
            data: function () {
                return {list: []};
                },
        template: '#appMenu',
        methods: {
        getMenuHeight: function(item) {
        return (item.isSelected ? item.children.length * 36 : 0) + 'px';
        },
        afterRender: function() {

        },

        clickFirstMenu: function(item) {
        if(item.isSelected) {
        item.isSelected = false;
        return;
        }
        for(var i = 0; i < this.list.length; i++) {
        var obj = this.list[i];
        if(item == obj) {
        item.isSelected = !item.isSelected;
        } else {
        obj.isSelected = false;
        }
        }
        },
        clickSecondMenu: function(item, sub, $event) {
        $event.stopPropagation();
        if(sub.isSelected) return;
        for(var i = 0; i < this.list.length; i++) {
        var obj = this.list[i];
        for(var j = 0; j < obj.children.length; j++) {
        var subObj = obj.children[j];
        if(item == subObj) continue;
        subObj.isSelected = false;
        }
        }

        sub.isSelected = true;
        }
        },
        computed: {

        },
        mounted: function () {
                var me = this;
        window.Utility.get('/test/getMenu.do').done(function (resp) {
        if (resp.status == Constant.AjaxStatus.OK) {
            for(var i = 0; i < resp.value.length; i++) {
        resp.value[i].isSelected = false;
        }
        var parents = resp.value.filter(function(item) {return item.pId == 0;});
            for(var i = 0; i < parents.length; i++) {
                parents[i].children = resp.value.filter(function(item) {return item.pId == parents[i].id;});
        }
            me.list = parents;

        setTimeout(function() {
        $('.sub-menu').css('transition', 'height ease 0.2s');
        }, 200);
        } else {
        alert(resp.msg);
        }
        }).fail(function () {
        alert("注销失败！");
        });
        }
        });
</script>
<sitemesh:write property='jsSection'/>
        <script>
        var vm = new Vue({
        el: '#myApp',
        data: data,
        methods: {

        }
        });
        </script>
</body>
</html>
