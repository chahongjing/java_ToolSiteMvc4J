/**
 * Created by chahongjing on 2017/7/17.
 */
app.directive('scopeIso', function ($compile) {
    return {
        restrict: 'A',
        scope: {
            selfDoubleParam: '=',
            selfSingleParam: '@',
            selfFuncParam: '&'
        },
        template: 'double<input type="text" data-ng-model="selfDoubleParam" /><br>single<input type="text" data-ng-model="selfSingleParam" />' +
        '<button data-ng-click="callP()">调用父方法</button>',
        link: function ($scope, element, attrs) {
            $scope.testVar = 'scopeIso';
            $scope.param = 'scopeIso';

            $scope.callP = function() {
                if($scope.selfFuncParam) {
                    $scope.selfFuncParam();
                }
            }
        }
    }
});