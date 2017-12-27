<%--
  Created by IntelliJ IDEA.
  User: chahongjing
  Date: 2017/7/17
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>angular 样例</title>
    <style>
        .red{color:red;}
        .borderred{border:1px solid red;}
    </style>
    <script type="text/javascript" src="/ToolSiteMvc4J/js/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="/ToolSiteMvc4J/js/Utility.js"></script>
    <script type="text/javascript">var ctx = '/ToolSiteMvc4J';</script>

    <script src="/ToolSiteMvc4J/js/angular/angular.js"></script>
    <script src="/ToolSiteMvc4J/js/angular/angular_main.js"></script>
    <script src="/ToolSiteMvc4J/js/angular/directives/ng_repeat_finish.js"></script>
    <script src="/ToolSiteMvc4J/js/angular/directives/test_templateurl.js"></script>
    <script src="/ToolSiteMvc4J/js/angular/directives/testDir.js"></script>
    <script src="/ToolSiteMvc4J/js/angular/templates/includePageCtrl.js"></script>
    <script src="/ToolSiteMvc4J/js/angular/directives/scopeTrue.js"></script>
    <script src="/ToolSiteMvc4J/js/angular/directives/scopeFalse.js"></script>
    <script src="/ToolSiteMvc4J/js/angular/directives/scopeIso.js"></script>
    <script src="/ToolSiteMvc4J/js/angular/directives/dirCompile.js"></script>
</head>
<body data-ng-app="myApp">
<div data-ng-controller="testCtrl" data-ng-init="init()">
    <hr>
    <h2>repeat</h2>
    param：<span data-ng-bind="param"></span><br>
    param1：<span data-ng-bind="param1"></span><br>
    param2：<span data-ng-bind="param2"></span><br>
    param3：<span data-ng-bind="param3"></span><br>
    template:<span data-ng-bind-template="{{param}}这是中间的数据{{paramB}}"></span><br>
    <ul>
        <li data-ng-repeat="item in model.list" data-ng-bind="item.name"
            ng-repeat-finish="model.myAfterRender(param)">
        </li>
    </ul>

    <hr>
    <h2>include</h2>
    <div data-ng-include="includeUrl" data-ng-controller="includePageCtrl" onload="init()"></div>


    <hr>
    <h2>指令中的指令</h2>
    <div test-url>
        <span data-ng-bind="model.name"></span>
    </div>


    <hr>
    <h2>调用子域方法</h2>
    <button data-ng-click="callChildFumc()">调用子域方法</button>

    <hr>
    <h2>指令的域scope</h2>
    double<input type="text" data-ng-model="model.doublebind" /><br>
    single<input type="text" data-ng-model="model.singlebind" />
    <div scope-false>

    </div>
    <div scope-true>

    </div>
    <div scope-iso self-double-param="model.doublebind" self-single-param="{{model.singlebind}}" self-func-param="parentFunc()">

    </div>

    <hr>
    <h2>promise</h2>
    <button data-ng-click="testPromise()">测试promise</button>
    <button data-ng-click="testAjax()">测试ajax</button>

    <hr>
    <h2>compile</h2>
    <div dir-compile watch-var="{{myChangeVar}}"></div>
    <button data-ng-click="changeVar()">改变变量的值</button>
</div>
<script>
    app.controller('testCtrl', ['$scope', '$http', '$timeout', '$q',
        function ($scope, $http, $timeout, $q) {
            $scope.model = {};
            $scope.param = 123;
            $scope.param1 = 123;
            $scope.param2 = 123;
            $scope.param3 = 123;
            $scope.paramB = 1111;

            $scope.init = function () {
                $scope.model.list = [];
                $scope.model.list.push({id: 1, name: '第一条数据'});
                $scope.model.list.push({id: 2, name: '第二条数据<div class="red">这里没有显示div</div>'});
                $scope.model.list.push({id: 3, name: '第三条数据'});

                $scope.includeUrl = ctx + '/js/angular/templates/includePage.html';
            };

            $scope.model.myAfterRender = function (param) {
                console.log('myAfterRender:' + param);
            };

            $scope.parentFunc = function() {
                console.log('这是父页面方法,通过$parent调用父方法!');
            }
            var m = $scope.$on('callParentFuncId', function($event, param) {
                console.log('这是父页面方法,通过emit+on调用父方法!' + JSON.stringify(param));
            });

            $scope.callChildFumc = function() {
                $scope.$broadcast('callChildFuncId', {a: '参数'});
            }

            $scope.testPromise = function() {
                var promise;
                if(!$scope.backData) {
                    promise = $http.get(ctx + '/test/testajax.do', {params: {a: 1}}).then(function(resp) {
                        if(resp.data.status == "OK") {
                            $scope.backData = resp.data.value;
                            return $scope.backData;
                        } else {
                            return {status: false};
                        }
                    });
                } else {
                    var deferred = $q.defer();
                    promise = deferred.promise.then(function() {
                        return {status: true, value: $scope.backData};
                    });
                }

                promise.then(function (resp) {
                    console.log('success');
                });
            }

            $scope.changeVar = function() {
                $scope.myChangeVar = Math.random();
            }

            $scope.testAjax = function() {
                $http.get(ctx + '/test/testajax11.do', {params: {a: 1}}).success(function(resp) {
                    console.log('success:' + resp);
                }).error(function(resp) {
                    console.log('error:' + resp);
                });
            }
        }]);
</script>
</body>
</html>
