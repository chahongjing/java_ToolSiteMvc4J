<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><% out.println("<div>首页</div>"); %></title>
    <style>
    </style>
</head>
<body>
<div>
    <div class="col-sm-6" v-for="item in list">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="clearfix">
                    <a href="" class="pull-left thumb-md avatar b-3x m-r">
                    </a>
                    <div class="clear">
                        <div class="h3 m-t-xs m-b-xs">
                            {{item.name}}
                            <i class="fa fa-circle text-success pull-right text-xs m-t-sm"></i>
                        </div>
                        <small class="text-muted" v-text="item.subTitle"></small>
                    </div>
                </div>
            </div>
            <div class="list-group no-radius alt">
                <a class="list-group-item" href="javascript:void(0)"
                   @click="goPage(sub)" v-for="sub in item.children">
                    <i class="fa fa-comment fa-fw text-muted {{sub.iconClass}}"></i>
                    {{sub.name}}
                </a>
            </div>
        </div>
    </div>
</div>
<jsSection>
    <script>
        // app.controller('indexCtrl', ['$rootScope', '$scope', '$http', '$timeout', '$q',
        //     function ($rootScope, $scope, $http, $timeout, $q) {
        //         $scope.model = {};
        //
        //         $scope.showLoading = function () {
        //             $rootScope.showLoading();
        //             $timeout(function () {
        //                 $rootScope.hideLoading();
        //             }, 2000);
        //         }
        //     }]);

        var list = [];
        // region java
        var first = {name: 'java', children:[]};
        list.push(first);
        second = {name:  '下载', href: '/learn/download', iconClass:''};
        first.children.push(second);
        second = {name:  'servlet学习', href: '/learn/servletLearn', iconClass:''};
        first.children.push(second);
        second = {name:  'jsp学习', href: '/learn/jspLearn?arr=1&arr=2&userName=曾军毅', iconClass:''};
        first.children.push(second);
        second = {name:  'el学习', href: '/learn/elLearn', iconClass:''};
        first.children.push(second);
        second = {name:  'spring aop学习', href: '/learn/springAopLearn/123', iconClass:''};
        first.children.push(second);
        second = {name:  'spring Bean学习', href: '/learn/springBeanLearn/123', iconClass:''};
        first.children.push(second);
        second = {name:  'springMVC学习', href: '/learn/springMVCLearn/123', iconClass:''};
        first.children.push(second);
        second = {name:  'cookie学习', href: '/learn/cookieLearn', iconClass:''};
        first.children.push(second);
        second = {name:  'session学习', href: '/learn/sessionLearn', iconClass:''};
        first.children.push(second);
        second = {name:  'java后台学习', href: '/learn/javaLearn', iconClass:''};
        first.children.push(second);
        second = {name:  'jvm学习', href: '/learn/jvmLearn', iconClass:''};
        first.children.push(second);
        second = {name:  'filter学习', href: '/learn/filterLearn', iconClass:''};
        first.children.push(second);
        second = {name:  '事务学习', href: '/learn/springTransactionLearn', iconClass:''};
        first.children.push(second);
        second = {name:  'nio学习', href: '/learn/nioLearn', iconClass:''};
        first.children.push(second);
        second = {name:  'shiro学习', href: '/learn/shiroLearn', iconClass:''};
        first.children.push(second);
        second = {name:  '反射学习', href: '/learn/reflectLearn', iconClass:''};
        first.children.push(second);
        second = {name:  '加密学习', href: '/learn/encryptLearn', iconClass:''};
        first.children.push(second);
        // endregion

        // region 服务器
        first = {name: '服务器', children:[]};
        list.push(first);
        second = {name:  'tomcat学习', href: '/learn/tomcatLearn', iconClass:''};
        first.children.push(second);
        // endregion

        // region maven
        first = {name: 'maven', children:[]};
        list.push(first);
        second = {name:  'maven学习', href: '/learn/mavenLearn', iconClass:''};
        first.children.push(second);
        // endregion

        // region echarts
        first = {name: 'echarts', children:[]};
        list.push(first);
        second = {name:  'echarts图表', href: '/echarts/index', iconClass:''};
        first.children.push(second);
        // endregion

        // region angular
        first = {name: 'angular', children:[]};
        list.push(first);
        second = {name:  'angular demo', href: '/learn/angulardemo', iconClass:''};
        first.children.push(second);
        second = {name:  'angular相关', href: '/learn/testangular', iconClass:''};
        first.children.push(second);
        // endregion

        // region vue
        first = {name: 'vue', children:[]};
        list.push(first);
        second = {name:  'vue demo', href: '/learn/vueDemo', iconClass:''};
        first.children.push(second);
        second = {name:  'vue learn', href: '/learn/vueLearn', iconClass:''};
        first.children.push(second);
        // endregion

        // region ueditor
        first = {name: 'ueditor', children:[]};
        list.push(first);
        second = {name:  'ueditor', href: '/learn/ueditorLearn', iconClass:''};
        first.children.push(second);
        // endregion

        // region js
        first = {name: 'js', children:[]};
        list.push(first);
        second = {name:  'js学习', href: '/learn/jsLearn', iconClass:''};
        first.children.push(second);
        // endregion

        // region 前端
        first = {name: '前端', children:[]};
        list.push(first);
        second = {name:  'css', href: '/learn/cssTest', iconClass:''};
        first.children.push(second);
        // endregion

        // region 其它
        first = {name: '其它', children:[]};
        list.push(first);
        second = {name:  'git学习', href: '/learn/gitLearn', iconClass:''};
        first.children.push(second);
        second = {name:  'oracle表转类', href: '/tool/tableToObject', iconClass:''};
        first.children.push(second);
        second = {name:  'sql生成器', href: '/tool/sqlGenerate', iconClass:''};
        first.children.push(second);
        second = {name:  '其它', href: '/learn/otherLearn', iconClass:''};
        first.children.push(second);
        // endregion

        vueData = {list: list};

        vueMethods = {
            goPage: goPage
        };

        vueMounted = function (){
        };

        function goPage(item) {
            window.location = ctx + item.href;
        }
    </script>
</jsSection>
</body>
</html>
