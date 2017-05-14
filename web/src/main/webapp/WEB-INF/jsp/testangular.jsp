<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/beginHead.jsp" %>
<title>测试angular</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<div data-ng-controller="testCtrl" data-ng-init="init()">
    <ul>
        <li data-ng-repeat="item in model.list" data-ng-bind="item.name" ng-repeat-finish="model.myAfterRender(param)">
        </li>
    </ul>

    <div test-url>
        <span data-ng-bind="model.name"></span>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<script src="${ctx}/js/angular/angular.js"></script>
<script src="${ctx}/js/angular/angular_main.js"></script>
<script src="${ctx}/js/angular/directives/ng_repeat_finish.js"></script>
<script src="${ctx}/js/angular/directives/test_templateurl.js"></script>
<script>
    app.controller('testCtrl', ['$scope', '$http',
        function ($scope, $http) {
            $scope.model = {};
            $scope.param = '123';

            $scope.init = function () {
                $scope.model.list = [];
                $scope.model.list.push({id: 1, name: '第一条数据'});
                $scope.model.list.push({id: 2, name: '第二条数据'});
                $scope.model.list.push({id: 3, name: '第三条数据'});
                $scope.model.name = "父级";
//                $scope.model.testGet();
//                $scope.model.testPost();
            };

            $scope.model.myAfterRender = function (param) {
                console.log('myAfterRender:' + param);
            };

            $scope.model.testGet = function () {
                $http.get('${ctx}/test/testajax.do', {params: {a: 1}}).success(function (resp) {
                    console.log('success');
                }).error(function (data, status, headers, config) {
                    console.log('error');
                });
            };

            $scope.model.testPost = function () {
                $http.post('${ctx}/test/testajax.do', {a: 11}, {params: {b: 2}}).success(function (resp) {
                    console.log('success');
                }).error(function (data, status, headers, config) {
                    console.log('error');
                });
            };
        }]);
</script>
<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>