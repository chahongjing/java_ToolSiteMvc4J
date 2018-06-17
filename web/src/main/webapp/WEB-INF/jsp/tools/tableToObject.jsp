<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>数据库表转类</title>
    <style>
        .connList .key{display:inline-block;width:100px;border:1px solid #444;padding:5px 10px;border-right:none;}
        .connList .value{display:inline-block;border:1px solid #444;padding:5px 10px;}
        .w100{width:100px;}
        .inline-block{display:inline-block;}
    </style>
</head>
<body>
<div data-ng-controller="testCtrl" data-ng-init="init()">
    连接地址<input type="text" style="width:400px;" data-ng-model="model.url"/><br>
    样例：
    <ul class="connList">
        <c:forEach var="item" items="${dbUrlMap}">
            <li><span class="key">${item.key}</span><span class="value">${item.value}</span></li>
        </c:forEach>
    </ul>
    <br>
    <span class="form-label w100 inline-block">用户名</span><input type="text" data-ng-model="model.user"/><br>
    <span class="form-label w100 inline-block">密码</span><input type="text" data-ng-model="model.password"/><br>
    <span class="form-label w100 inline-block">表名</span><input type="text" data-ng-model="model.tableName"/><br>
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
                    } else if (/sqlite/i.test($scope.model.url)) {
                        type = "sqlite";
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