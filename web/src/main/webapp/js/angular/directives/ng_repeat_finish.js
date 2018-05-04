/**
 * Created by chahongjing on 2017/5/11.
 */
//自定义指令ngRepeatFinish: 当ng-repeat渲染完成之后执行
'use strict';
app.directive('ngRepeatFinish', function ($compile) {
    return {
        restrict: 'A',
        scope: {
            ngRepeatFinish: '&'
        },
        link: function ($scope, element, attrs) {
            if ($scope.$parent.$last === true) {
                $scope.ngRepeatFinish && $scope.ngRepeatFinish({param: 456});
            }
        }
    }
});