/**
 * Created by chahongjing on 2017/7/12.
 */
app.controller('includePageCtrl', ['$scope', '$http',
    function ($scope, $http) {
        $scope.model = {};

        $scope.init = function () {
            $scope.$parent.parentFunc();
            var m = $scope.$emit('callParentFuncId', {a: 'param'});
        };

        $scope.$on('callChildFuncId', function($event, param) {
            console.log('children page！broadcast+on call!' + JSON.stringify(param));
        });

        $scope.testclick = function() {
            console.log('test');
        }
    }]);