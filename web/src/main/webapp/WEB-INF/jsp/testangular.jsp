<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/beginHead.jsp" %>
<%-- 页头，添加title, mate信息, link样式, script脚本(建议在script节中添加) --%>
<title>测试angular</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div data-ng-controller="testCtrl" data-ng-init="init()">
    <ul>
        <li data-ng-repeat="item in model.list" data-ng-bind="item.name" ng-repeat-finish="model.myAfterRender(param)">
        </li>
    </ul>

    <div data-ng-include="includeUrl" data-ng-controller="includePageCtrl" onload="init()"></div>
    <span data-ng-bind-template="{{param}}[abc]{{paramb}}"></span>
    <span data-ng-bind="paramb"></span>
    <div test-url>
        <span data-ng-bind="model.name"></span>
    </div>

    <hr>
    <div id="testTemplate">

    </div>
</div>

<script type="text/ng-template" id="my.html">
    <h4>lovestory</h4>
    <p>这是script标签获取模板文件的方式</p>
    <a href="{{url}}">标签启用templateCache方式</a>
</script>

<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>
<script src="${ctx}/js/angular/angular.js"></script>
<script src="${ctx}/js/angular/angular_main.js"></script>
<script src="${ctx}/js/angular/directives/ng_repeat_finish.js"></script>
<script src="${ctx}/js/angular/directives/test_templateurl.js"></script>
<script src="${ctx}/js/angular/directives/testDir.js"></script>
<script src="${ctx}/js/angular/templates/includePageCtrl.js"></script>
<script>
    app.controller('testCtrl', ['$scope', '$http', '$timeout', '$compile', '$templateCache',
        function ($scope, $http, $timeout, $compile, $templateCache) {
            $scope.model = {};
            $scope.param = '123';
            $scope.paramb = '<div>abc</div>';

            $scope.init = function () {
                $scope.model.list = [];
                $scope.model.list.push({id: 1, name: '第一条数据'});
                $scope.model.list.push({id: 2, name: '第二条数据'});
                $scope.model.list.push({id: 3, name: '第三条数据'});
                $scope.model.name = "父级";
//                $scope.model.testGet();
//                $scope.model.testPost();

                $scope.includeUrl = '${ctx}/js/angular/templates/includePage.html';

                $timeout(function() {
                    $scope.$broadcast('callChildFuncId', {a: '参数'});
                });

                var template = $templateCache.get('my.html');
                $scope.url = 'http://www.baidu.com';
                angular.element('#testTemplate').append($compile(template)($scope));
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

            $scope.parentFunc = function() {
                console.log('这是父页面方法,通过$parent调用父方法!');
            }
            var m = $scope.$on('callParentFuncId', function($event, param) {
                console.log('这是父页面方法,通过emit+on调用父方法!' + JSON.stringify(param));
            });
        }]);
</script>
<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>