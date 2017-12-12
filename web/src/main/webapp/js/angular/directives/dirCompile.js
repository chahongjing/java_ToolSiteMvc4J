/**
 * Created by Administrator on 2017/7/18.
 */
app.directive('dirCompile', function ($compile) {
    return {
        restrict: 'A',
        scope: {
            watchVar: '@'
        },
        link: function ($scope, element, attrs) {
            $scope.testVar = 'scopeTrue';
            $scope.param = 'scopeTrue';
            $scope.$watch('watchVar', function (sourcedata) {
                var html = '';
                html += '<div data-ng-bind="watchVar + \'普通变量\'"></div>'
                element.html(html);
                $compile(element.contents())($scope);
            });
        }
    }
});