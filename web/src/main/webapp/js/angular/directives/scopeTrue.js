/**
 * Created by chahongjing on 2017/7/17.
 */
/**
 * Created by Administrator on 2017/5/12.
 */
app.directive('scopeTrue', function ($compile) {
    return {
        restrict: 'A',
        scope: true,
        template: 'scopeTrue',
        link: function ($scope, element, attrs) {
            $scope.testVar = 'scopeTrue';
            $scope.param2 = 'scopeTrue';
        }
    }
});