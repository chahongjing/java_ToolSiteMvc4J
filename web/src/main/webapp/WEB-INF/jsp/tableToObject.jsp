<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>数据库表转类</title>
</head>
<body>
<div data-ng-controller="testCtrl" data-ng-init="init()">
    连接地址<input type="text" data-ng-model="model.url"/><br>
    样例：
    <ul>
        <c:forEach var="item" items="${dbUrlList}">
            <li>${item}</li>
        </c:forEach>
    </ul>
    <br>
    用户名<input type="text" data-ng-model="model.user"/><br>
    密码<input type="text" data-ng-model="model.password"/><br>
    表名<input type="text" data-ng-model="model.tableName"/><br>
    <button data-ng-click="model.getTableInfo()">测试</button>

    <textarea data-ng-model="model.result" style="display:block;width:50%;margin:auto;min-height:400px;"></textarea>
</div>
<jsSection>
    <script src="${ctx}/js/angular/angular.js"></script>
    <script src="${ctx}/js/angular/angular_main.js"></script>
    <script src="${ctx}/js/angular/commonService.js"></script>
    <script>
        app.controller('testCtrl', ['$scope', '$http', 'commonService',
            function ($scope, $http, commonSrv) {
                $scope.model = {};

                $scope.init = function () {
                    $scope.model.url = '${dbUrl}';
                    $scope.model.user = '${dbUser}';
                    $scope.model.password = '${dbPassword}';
                };

                $scope.model.getTableInfo = function () {
                    if (!$scope.model.url) {
                        alert("请输入url地址！");
                        return;
                    }
                    var type = "oracle";
                    if (/oracle/i.test($scope.model.url)) {
                        type = "oracle";
                    } else if (/mysql/i.test($scope.model.url)) {
                        type = "mysql";
                    } else if (/sqlserver/i.test($scope.model.url)) {
                        type = "sqlserver";
                    }
                    var param = {
                        type: type,
                        url: $scope.model.url,
                        user: $scope.model.user,
                        password: $scope.model.password,
                        tableName: $scope.model.tableName
                    };
                    $http.get('${ctx}/tool/getTableInfo.do', {params: param}).success(function (resp) {
                        console.log('success');
                        $scope.model.result = resp.value;
                    }).error(function (data, status, headers, config) {
                        console.log('error');
                    });
                };
            }]);
    </script>
</jsSection>
</body>
</html>