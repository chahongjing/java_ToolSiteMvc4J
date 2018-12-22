/**
 * Created by chahongjing on 2017/5/11.
 */
'use strict'
var app = angular.module('myApp', [])
    .config(['$httpProvider', function ($httpProvider) {
            // 默认post处理为form表单提交
            $httpProvider.defaults.headers.common["X-Requested-With"] = "XMLHttpRequest";
            $httpProvider.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
            $httpProvider.defaults.transformRequest = [function (data) {
                return angular.isObject(data) && String(data) !== '[object File]' ? $.param(data) : data;
            }];
			$httpProvider.defaults.transformResponse = [function(data, headers){
			    if(data && !(data instanceof Blob)) {
                    try{
                        return $.parseJSON(data)
                    } catch(e) {}
                }
				return data;
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
                        } else if (response.status === 404) {
                            var jReturn = {Status: 'ERROR'};
                            var startIndex = response.data.indexOf("<title>");
                            if (startIndex > 0) {
                                var endIndex = response.data.indexOf("</title>");
                                jReturn.Message = response.data.substring(startIndex + 7, endIndex);
                                startIndex = response.data.indexOf("<body>");
                                endIndex = response.data.indexOf("</body>");
                                var els = $('<div>' + response.data.substring(startIndex + 6, endIndex) + '</div>').children();
                                if (els && els.length > 0) {
                                    var message = [];
                                    for (var i = 0; i < els.length; i++) {
                                        if (els[i].innerText && $.trim(els[i].innerText)) {
                                            message.push(els[i].innerText);
                                        }
                                    }
                                    jReturn.DetailMessage = message.join('\r\n');
                                }
                            } else {
                                jReturn.Message = "返回数据失败！";
                            }
                            //return $q.resolve({data:jReturn});
                            return $q.reject({data: jReturn});
                        } else if(response.status === 500) {
                            if(response.data instanceof Blob) {
                                Utility.readBlobAsText(response.data, function(data) {
                                    var res = JSON.parse(data);
                                    alert(res.message);
                                });
                            }
                        }
                        return $q.reject(response);
                        //return $q.resolve(response);
                    }
                };
            });
        }]
    );
app.controller('mainCtrl', ['$rootScope', '$scope', '$timeout', 'commonService',
    function ($rootScope, $scope, $timeout, commonSrv) {
        $scope.model = {loadingReload: 0};

        $scope.init = function () {
            $scope.model.loadingReload++;
            getMenu();
        }
        // 退出登录
        $scope.logout = function () {
            commonSrv.get('/user/logout').success(function (resp) {
                if (resp.status == Constant.AjaxStatus.OK) {
                    sessionStorage.clear();
                    window.location = commonSrv.getContext();
                } else {
                    alert(resp.msg);
                }
            }).error(function () {
                alert("注销失败！");
            });
        }

        $rootScope.showLoading = $scope.showLoading = function (title) {
            $scope.model.loadingReload++;
            if (title) {
                $scope.model.loadingText = 'title';
            }
            $scope.model.isShowLoading = true;
        }

        $rootScope.hideLoading = $scope.hideLoading = function () {
            $scope.model.loadingText = '';
            $scope.model.isShowLoading = false;
        }

        // 返回
        $scope.goBack = function () {
            history.go(-1);
        }

        function getMenu() {
            var list = [];
            list.push({id:1,name: 'A',isSelected: true, icon: 'fa-cog', list:[{pId:3,name:'B', icon: 'fa-cog',isSelected: true},{pId:4,name:'C', icon: 'fa-cog'}]});
            list.push({id:2,name: 'D', icon: 'fa-cog', list:[{pId:5,name:'E', icon: 'fa-cog'},{pId:6,name:'F', icon: 'fa-cog'}]});
            $scope.model.menu = list;
        }
        $scope.clickFirstMenu = function(item) {
            if(item.isSelected) {
                item.isSelected = false;
                return;
            }
            for(var i = 0; i < $scope.model.menu.length; i++) {
                var obj = $scope.model.menu[i];
                if(item == obj) {
                    item.isSelected = !item.isSelected;
                } else {
                    obj.isSelected = false;
                }
            }
        }
        $scope.clickSecondMenu = function(item, sub, $event) {
            $event.stopPropagation();
            if(sub.isSelected) return;
            for(var i = 0; i < $scope.model.menu.length; i++) {
                var obj = $scope.model.menu[i];
                for(var j = 0; j < obj.list.length; j++) {
                    var subObj = obj.list[j];
                    if(item == subObj) continue;
                    subObj.isSelected = false;
                }
            }

            sub.isSelected = true;
        }

        $scope.getMenuHeight = function(item) {
            return item.isSelected ? item.list.length * 36 : 0;
        }
        $scope.afterRender = function() {
            $timeout(function() {
                $('.sub-menu').css('transition', 'height ease 0.2s');
            }, 200);
        }
    }]);