/**
 * Created by Administrator on 2017/5/12.
 */
app.directive('testUrl', function ($compile) {
    return {
        restrict: 'A',
        //replace: true, //只对元素有效
        transclude: true,
        scope: {
        },
        templateUrl: ctx + '/js/angular/directives/test_templateurl.html',
        link: function ($scope, element, attrs) {
            $scope.model = {};
            $scope.model.testdata = "<div>测试数据</div>"
            $scope.model.name = "指令";
        }
    }
});