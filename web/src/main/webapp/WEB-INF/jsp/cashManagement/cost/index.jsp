<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>花销列表</title>
</head>
<body>
<div data-ng-controller="costCtrl" data-ng-init="init()">


    <ul>
        <li data-ng-repeat="item in model.list" data-ng-bind="item.name" ng-repeat-finish="model.myAfterRender(param)">
        </li>
    </ul>

    <span data-ng-bind-template="{{param}}[abc]{{paramb}}"></span>
    <span data-ng-bind="paramb"></span>
    <div test-url>
        <span data-ng-bind="model.name"></span>
    </div>
</div>
<jsSection>
    <script src="${ctx}/js/angular/angular.js"></script>
    <script src="${ctx}/js/angular/angular_main.js"></script>
    <script src="${ctx}/js/angular/directives/ng_repeat_finish.js"></script>
    <script src="${ctx}/js/angular/commonService.js"></script>
    <script>
        app.controller('costCtrl', ['$scope', 'commonService',
            function ($scope, commonSvc) {
                $scope.model = {};

                $scope.init = function () {
                    commonSvc.get('${ctx}/cost/getList.do').success(function (resp) {
                        console.log('success');
                    }).error(function (data, status, headers, config) {
                        console.log('error');
                    });
                    $scope.model.list = [];
                    $scope.model.list.push({id: 1, name: '第一条数据'});
                    $scope.model.list.push({id: 2, name: '第二条数据'});
                    $scope.model.list.push({id: 3, name: '第三条数据'});
                };
            }]);
    </script>
</jsSection>
</body>
</html>