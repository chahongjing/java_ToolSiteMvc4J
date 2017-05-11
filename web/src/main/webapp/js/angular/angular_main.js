/**
 * Created by chahongjing on 2017/5/11.
 */
var app = angular.module('myApp', [])
    .config(['$httpProvider', function ($httpProvider) {
            // 默认post处理为form表单提交
            $httpProvider.defaults.headers.common["x-requested-with"] = "XMLHttpRequest";
            $httpProvider.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
            $httpProvider.defaults.transformRequest = [function (data) {
                return angular.isObject(data) && String(data) !== '[object File]' ? $.param(data) : data;
            }];
            $httpProvider.interceptors.push(function ($rootScope, $q) {
                return {
                    'request': function (config) {
                        return config || $q.when(config);
                    },
                    'requestError': function (rejection) {
                        return rejection;
                    },
                    'response': function (response) {
                        return response || $q.when(response);
                    },
                    'responseError': function (response) {
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
        }]
    );