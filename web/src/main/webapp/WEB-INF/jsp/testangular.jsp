<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/commonCss.jsp" %>
    <title>测试angular</title>
</head>
<body data-ng-app="myApp">
<div data-ng-controller="testCtrl" data-ng-init="init()">
    <ul>
        <li data-ng-repeat="item in model.list" data-ng-bind="item.name"></li>
    </ul>
</div>
</body>
</html>
<%@ include file="/commonJs.jsp" %>
<script src="${ctx}/js/angular.js"></script>
<script>
    var app = angular.module('myApp', [])
            .config(['$httpProvider', function ($httpProvider) {
                // 默认post处理为form表单提交
                $httpProvider.defaults.headers.common["x-requested-with"] = "XMLHttpRequest";
                $httpProvider.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
                $httpProvider.defaults.transformRequest = [function(data) {
                    return angular.isObject(data) && String(data) !== '[object File]' ? $.param(data) : data;
                }];
                $httpProvider.interceptors.push(function($rootScope, $q) {
                    return {
                        'request': function(config) {
                            return config || $q.when(config);
                        },
                        'requestError': function(rejection) {
                            return rejection;
                        },
                        'response': function(response) {
                            return  response || $q.when(response);
                        },
                        'responseError': function(response) {
                            if (response.status === 401) {
                                var deferred = $q.defer(),
                                        req = {
                                            config: response.config,
                                            deferred: deferred
                                        };
                                $rootScope.requests401.push(req);
                                $rootScope.$broadcast('event:loginRequired');
                                return deferred.promise;
                            }
                            return $q.reject(response);
                            //return $q.resolve(response);
                        }
                    };
                });
            }]);

    app.controller('testCtrl', ['$scope', '$http',
        function ($scope, $http) {
            $scope.model = {};
            $scope.init = function () {
                $scope.model.list = [];
                $scope.model.list.push({id: 1, name: '第一条数据'});
                $scope.model.list.push({id: 2, name: '第二条数据'});
                $scope.model.list.push({id: 3, name: '第三条数据'});

                $http.get('${ctx}/test/testajax.do', {params: {a: 1}}).success(function (resp) {
                    console.log('success');
                }).error(function(data, status, headers, config) {
                    console.log('error');
                });

                $http.post('${ctx}/test/testajax.do', {a: 11}, {params: {b: 2}}).success(function (resp) {
                    console.log('success');
                }).error(function(data, status, headers, config) {
                    console.log('error');
                });
            }
        }]);
</script>