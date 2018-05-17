/**
 * Created by jyzeng on 2018/5/2.
 */
'use strict';
app.directive('paginationDir', ['$compile', '$templateCache', '$sce', 'commonService',
    function ($compile, $templateCache, $sce, commonSrv) {
        return {
            restrict: 'A',
            scope: {
                pagerInfo: "=",
                afterClick: "&"
            },
            link: function ($scope, element, attrs) {
                $scope.model = {};
                // endregion

                $scope.trustAsHtml = $sce.trustAsHtml;

                // region 基本属性
                // endregion

                // 刷新指令
                $scope.$watch('pagerInfo', function (pagerInfo) {
                    // 无数据则返回
                    if (!pagerInfo) {
                        return;
                    }
                    var list = handData(pagerInfo);
                    var html = $templateCache.get('pagination.html');
                    element.html(html);
                    $compile(element.contents())($scope);
                    $scope.model.list = list;
                });

                $scope.goPage = function(item) {
                    if(item.isDisabled) return;
                    $scope.afterClick && $scope.afterClick({page: item.value});
                }

                function handData(pagerInfo) {
                    var list = [], start = pagerInfo.pageNum - 2, end = pagerInfo.pageNum + 2, pre = pagerInfo.pageNum - 1, next = pagerInfo.pageNum + 1;
                    if(start < 1) start = 1;
                    if(end > pagerInfo.pages) end = pagerInfo.pages;
                    if(pre < 1) pre = 1;
                    if(next > pagerInfo.pages) next = pagerInfo.pages;
                    var obj;
                    obj = {name:'上一页', value: pre, isDisabled: false};
                    if(obj.value == pagerInfo.pageNum) {
                        obj.isDisabled = true;
                    }
                    list.push(obj);
                    for(var i = start; i < end + 1; i++) {
                        obj = {name: i, value: i, isDisabled: false};
                        if(pagerInfo.pageNum == i) {
                            obj.isCurrent = true;
                            obj.isDisabled = true;
                        }
                        list.push(obj);
                    }
                    obj = {name:'下一页', value: next, isDisabled: false};
                    if(obj.value == pagerInfo.pageNum) {
                        obj.isDisabled = true;
                    }
                    list.push(obj);
                    return list;
                }
            }
        }
    }]
);