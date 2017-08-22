/**
 * Created by Administrator on 2017/5/12.
 */
app.directive('testUrl', function ($compile) {
    return {
        restrict: 'A',
        //replace: true, //只对元素有效
        scope: {
            doubleBind: '=',// 双向绑定
            singleBind: '@',// 单向绑定
            functionCall: '&' // 方法
        },
        transclude: true,
        templateUrl: ctx + '/js/angular/directives/test_templateurl.html',
        link: function ($scope, element, attrs) {
            $scope.model = {};
            $scope.model.testdata = "<div>测试数据</div>";
            $scope.model.name = "指令";

            $scope.testclick = function() {
                console.log('parent func');
            }
        }
    }
});