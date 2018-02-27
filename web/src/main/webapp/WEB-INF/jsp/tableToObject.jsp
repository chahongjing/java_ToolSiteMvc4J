<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/beginHead.jsp" %>
<%-- 页头，添加title, mate信息, link样式, script脚本(建议在script节中添加) --%>
<title>数据库表转类</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div data-ng-controller="testCtrl" data-ng-init="init()">
    连接地址<input type="text" data-ng-model="model.url" /><br>
    样例：
    <ul>
        <li>oracle：jdbc:oracle:thin:@127.0.0.1:1521:orcl</li>
        <li>mysql：jdbc:mysql://localhost/toolsitemvc4j</li>
        <li>sqlserver：jdbc:sqlserver://PC201404190064\\MSSQL; DatabaseName=ToolSiteMvc4J</li>
    </ul>
    <br>
    用户名<input type="text" data-ng-model="model.user" /><br>
    密码<input type="text" data-ng-model="model.password" /><br>
    表名<input type="text" data-ng-model="model.tableName" /><br>
    <button data-ng-click="model.getTableInfo()">测试</button>

    <textarea data-ng-model="model.result" style="display:block;width:50%;margin:auto;min-height:400px;"></textarea>
</div>

<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>
<script src="${ctx}/js/angular/angular.js"></script>
<script src="${ctx}/js/angular/angular_main.js"></script>
<script src="${ctx}/js/angular/commonService.js"></script>
<script>
    app.controller('testCtrl', ['$scope', '$http', 'commonService',
        function ($scope, $http, commonSrv) {
            $scope.model = {};

            $scope.init = function () {
                $scope.model.url = 'jdbc:oracle:thin:@127.0.0.1:1521:orcl';
                $scope.model.user = 'zjy';
                $scope.model.password = '1024';
            };

            $scope.model.getTableInfo = function () {
                var param = {
                    type: 'oracle',
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
<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>