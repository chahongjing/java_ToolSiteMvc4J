/**
 * Created by chahongjing on 2017/5/11.
 */
//自定义指令ngRepeatFinish: 当ng-repeat渲染完成之后执行
app.directive('ngRepeatFinish', function ($compile) {
    return {
        restrict: 'A',
        scope: {
            afterRender: '&'
        },
        link: function ($scope, element, attrs) {
            if ($scope.$parent.$last === true) {
                $scope.afterRender && $scope.afterRender({param: 456});
            }
        }
    }
});