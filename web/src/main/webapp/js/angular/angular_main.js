/**
 * Created by chahongjing on 2017/5/11.
 */
'use strict'
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
                        } else if(response.status === 404) {
                            var jReturn = {Status: 'ERROR'};
                            var startIndex = response.data.indexOf("<title>");
                            if (startIndex > 0) {
                                var endIndex = response.data.indexOf("</title>");
                                jReturn.Message = response.data.substring(startIndex + 7, endIndex);
                                startIndex = response.data.indexOf("<body>");
                                endIndex = response.data.indexOf("</body>");
                                var els = $('<div>' + response.data.substring(startIndex + 6, endIndex) + '</div>').children();
                                if(els && els.length > 0) {
                                    var message = [];
                                    for(var i = 0; i < els.length; i++) {
                                        if(els[i].innerText && $.trim(els[i].innerText)) {
                                            message.push(els[i].innerText);
                                        }
                                    }
                                    jReturn.DetailMessage = message.join('\r\n');
                                }
                            } else {
                                jReturn.Message = "返回数据失败！";
                            }
                            //return $q.resolve({data:jReturn});
                            return $q.reject({data:jReturn});
                        }
                        return $q.reject(response);
                        //return $q.resolve(response);
                    }
                };
            });
        }]
    );