/**
 * Created by chahongjing on 2017/7/13.
 */
app.directive('testDir', function ($compile) {
    return {
        restrict: 'A',
        template: '<div>old:{{oldValue}}--new:{{newValue}}</div>',
        scope: {
            abcname: '@'
        },
        link: function ($scope, $compile, element, attrs) {
            $scope.$watch('abcname', function(newValue, oldValue) {
                console.log('new:' + newValue + ' old:' + oldValue);
                $scope.oldValue = oldValue;
                $scope.newValue = newValue;
            })
        }
    }
});