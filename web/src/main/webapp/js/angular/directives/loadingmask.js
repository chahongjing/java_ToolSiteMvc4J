/**
 * Created by jyzeng on 2018/5/2.
 */
'use strict';
app.directive('loadingMask', ['$compile', '$templateCache', '$sce', 'commonService',
    function ($compile, $templateCache, $sce, commonSrv) {
        return {
            restrict: 'A',
            scope: {
                text: '=',
                isShow: '=',
                reload: '='
            },
            link: function ($scope, element, attrs) {
                $scope.model = {isLoadHtml: false};
                // endregion

                $scope.trustAsHtml = $sce.trustAsHtml;

                // region 基本属性
                // endregion

                // 刷新指令
                $scope.$watch('reload', function (reload) {
                    if(!reload) return;
                    loadHtml();
                });
                $scope.$watch('isShow', function (isShow) {
                    if(isShow != true) return;
                    if($scope.model.isLoadHtml == false) {
                        loadHtml();
                    }
                });

                $scope.getMessage = function() {
                    return $scope.trustAsHtml($scope.text || '数据处理中，请等待...');
                }

                function loadHtml() {
                    var html = $templateCache.get('loadingmask.html');
                    if(html) {
                        $scope.model.isLoadHtml = true;
                    }
                    element.html(html);
                    element.find('img').attr('src', ctx + '/bootstrap/images/loading.gif');
                    $compile(element.contents())($scope);
                }
            }
        }
    }]
);