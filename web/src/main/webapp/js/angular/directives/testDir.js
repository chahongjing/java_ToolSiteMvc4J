/**
 * Created by chahongjing on 2017/7/13.
 */
app.directive('testDir', function ($compile) {
    return {
        restrict: 'A',
        template: '<div>testdiv</div>',
        scope: {
            abcname: '@'
        },
        link: function ($scope, element, attrs) {
            $scope.$watch('abcname', function(newValue, oldValue) {
                console.log('new:' + newValue + ' old:' + oldValue);
            })
        }
    }
});