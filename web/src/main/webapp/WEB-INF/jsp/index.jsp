<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><% out.println("<div>首页</div>"); %></title>
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
<fmt:message key="username">
    <fmt:param value="第一个值"/>
    <fmt:param value="第二个值"/>
</fmt:message>
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
        // region angular
        first = {name: '其它', children:[]};
        list.push(first);
        second = {name:  '其它', href: '/learn/angulardemo', iconClass:''};
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
