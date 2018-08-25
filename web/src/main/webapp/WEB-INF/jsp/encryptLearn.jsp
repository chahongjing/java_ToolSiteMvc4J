<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>tomcat学习</title>
</head>
<body>
<div data-ng-controller="testCtrl" data-ng-init="init()">
    <textarea data-ng-model="model.source"></textarea>
    <button data-ng-click="base64Encrypt()">base64加密</button>
    <button data-ng-click="base64Decrypt()">base64解密</button>
    <textarea data-ng-model="model.target"></textarea>
</div>
<jsSection>
    <%-- js脚本 --%>
    <script src="${ctx}/js/angular/angular.js"></script>
    <script src="${ctx}/js/angular/angular_main.js"></script>
    <script src="${ctx}/js/angular/commonService.js"></script>
    <script>
        app.controller('testCtrl', ['$scope', '$http', 'commonService',
            function ($scope, $http, commonSrv) {
                $scope.model = {};

                $scope.init = function () {
                };

                $scope.base64Encrypt = function () {
                    $http.get('${ctx}/learn/base64Encrypt', {params: {source: $scope.model.source}}).success(function (resp) {
                        console.log(resp);
                        $scope.model.target = resp.value || '';
                    }).error(function (data, status, headers, config) {
                        console.log('error');
                    });
                }

                $scope.base64Decrypt = function () {
                    $http.get('${ctx}/learn/base64Decrypt', {params: {source: $scope.model.source}}).success(function (resp) {
                        console.log(resp);
                        $scope.model.target = resp.value || '';
                    }).error(function (data, status, headers, config) {
                        console.log('error');
                    });
                }
            }]);
    </script>
</jsSection>
</body>
</html>