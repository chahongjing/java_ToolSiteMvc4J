/**
 * Created by chahongjing on 2017/7/17.
 */
app.directive('scopeFalse', function ($compile) {
    return {
        restrict: 'A',
        scope: false,
        template: 'scopeFalse',
        link: function ($scope, element, attrs) {
            $scope.testVar = 'scopeFalse';
            $scope.param1 = 'scopeFalse';
        }
    }
});